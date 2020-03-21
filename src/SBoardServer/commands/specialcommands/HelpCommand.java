package SBoardServer.commands.specialcommands;

import SBoardServer.SBoardServer;
import SBoardServer.commands.AbstractCommand;
import SBoardServer.commands.CommandException;
import SBoardServer.helpers.LoggerHelper;

import java.util.Map;

public class HelpCommand extends AbstractCommand {
    public HelpCommand(SBoardServer server, int minArgs) {
        super(server, minArgs);
    }

    @Override
    public String getUsage() {
        return "";
    }

    @Override
    public String getDesc() {
        return "Print commands usage";
    }

    @Override
    public void perform(String... args) throws CommandException {
        for(Map.Entry<String, AbstractCommand> map : getServer().getCommandHandler().getCommands().entrySet()) {
            LoggerHelper.info(map.getKey() + " " + map.getValue().getUsage() + " - " + map.getValue().getDesc());
        }
    }
}
