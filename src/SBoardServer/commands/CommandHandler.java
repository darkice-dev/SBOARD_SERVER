package SBoardServer.commands;

import SBoardServer.SBoardServer;
import SBoardServer.commands.register.RegisterCompanyCommand;
import SBoardServer.commands.register.RegisterEmployee;
import SBoardServer.commands.register.RegisterUserCommand;
import SBoardServer.commands.specialcommands.HelpCommand;
import SBoardServer.commands.specialcommands.StopCommand;
import SBoardServer.helpers.LoggerHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class CommandHandler extends Thread {

    private SBoardServer server;
    private HashMap<String, AbstractCommand> commands = new HashMap<>();

    public CommandHandler(SBoardServer server) {
        this.server = server;
        register("reguser", new RegisterUserCommand(server, 3));
        register("regcompany", new RegisterCompanyCommand(server, 2));
        register("regemployee", new RegisterEmployee(server, 3));
        register("help", new HelpCommand(server, 0));
        register("stop", new StopCommand(server, 0));
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
        String line;
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

    public HashMap<String, AbstractCommand> getCommands() {
        return commands;
    }
}
