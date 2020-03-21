package SBoardServer.commands.specialcommands;

import SBoardServer.SBoardServer;
import SBoardServer.commands.AbstractCommand;
import SBoardServer.commands.CommandException;
import SBoardServer.helpers.LoggerHelper;

public class StopCommand extends AbstractCommand {
    public StopCommand(SBoardServer server, int minArgs) {
        super(server, minArgs);
    }

    @Override
    public String getUsage() {
        return "";
    }

    @Override
    public String getDesc() {
        return "Stop server";
    }

    @Override
    public void perform(String... args) throws CommandException {
        LoggerHelper.info("Stopping server...");
        System.exit(0);
    }
}
