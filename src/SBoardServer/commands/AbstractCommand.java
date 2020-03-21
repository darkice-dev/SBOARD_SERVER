package SBoardServer.commands;

import SBoardServer.SBoardServer;

public abstract class AbstractCommand {

    private String name;
    private SBoardServer server;
    private int minArgs;

    public AbstractCommand(SBoardServer server, int minArgs) {
        this.minArgs = minArgs;
        this.server = server;
    }

    public int getMinArgs() {
        return minArgs;
    }

    public SBoardServer getServer() {
        return server;
    }

    public abstract String getUsage();

    public abstract String getDesc();

    public abstract void perform(String... args) throws CommandException;

    public void verifyArgs(String[] args, int min) throws CommandException {
        if(args.length < min) {
            throw new CommandException("Command usage: " + getUsage());
        }
    }

}
