package SBoardServer.commands;

import SBoardServer.SBoardServer;
import SBoardServer.commands.register.RegisterCommand;
import SBoardServer.helpers.LoggerHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class CommandHandler extends Thread {

    private SBoardServer server;
    private HashMap<String, AbstractCommand> commands = new HashMap<>();

    public CommandHandler(SBoardServer server) {
        register("reg", new RegisterCommand(server));
    }

    public void register(String id, AbstractCommand command) {
        if(commands.containsKey(id)) {
            LoggerHelper.warning("Command has been already registered: " + id);
        } else {
            commands.put(id, command);
        }
    }

    @Override
    public void run() {
        while(!Thread.interrupted()) {
            read();
        }
    }

    public void read() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = null;
        try {
            line = br.readLine();
        } catch (IOException e) {
            LoggerHelper.error("can't read line: " +  e);
            return;
        }
        String[] args = line.split(" ");
        AbstractCommand command = commands.get(args[0]);
        if(command != null) {
            try {
                command.perform(Arrays.copyOfRange(args,1,args.length));
            } catch (CommandException e) {
                LoggerHelper.error(e.getMessage());
            }
        } else try {
            throw new CommandException(String.format("Unknown command: '%s'", args[0]));
        } catch (CommandException e) {
            LoggerHelper.error(e.getMessage());
        }
    }
}
