package SBoardServer.commands;

import SBoardServer.SBoardServer;

public abstract class AbstractCommand {

    private String name;
    private SBoardServer server;

    public AbstractCommand(SBoardServer server) {
        this.server = server;
    }

    public abstract String getUsage();

    public abstract void perform(String... args) throws CommandException;

    public void verifyArgs(String[] args, int min) throws CommandException {
        if(args.length < min) {
            throw new CommandException("Command usage: " + getUsage());
        }
    }

}
