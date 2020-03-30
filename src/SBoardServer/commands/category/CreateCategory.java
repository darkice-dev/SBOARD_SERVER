package SBoardServer.commands.category;

import SBoardServer.SBoardServer;
import SBoardServer.commands.AbstractCommand;
import SBoardServer.commands.CommandException;
import SBoardServer.domain.Category;
import SBoardServer.helpers.LoggerHelper;

public class CreateCategory extends AbstractCommand {
    public CreateCategory(SBoardServer server, int minArgs) {
        super(server, minArgs);
    }

    @Override
    public String getUsage() {
        return "<name>";
    }

    @Override
    public String getDesc() {
        return "create category";
    }

    @Override
    public void perform(String... args) throws CommandException {
        getServer().getStorageManager().createCategory(args[0]);
        LoggerHelper.info(args[0] + "successful created");
    }
}
