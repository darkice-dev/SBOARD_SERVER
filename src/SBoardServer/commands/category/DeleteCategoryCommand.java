package SBoardServer.commands.category;

import SBoardServer.SBoardServer;
import SBoardServer.commands.AbstractCommand;
import SBoardServer.commands.CommandException;
import SBoardServer.helpers.LoggerHelper;

import java.util.IllegalFormatException;

public class DeleteCategoryCommand extends AbstractCommand {
    public DeleteCategoryCommand(SBoardServer server, int minArgs) {
        super(server, minArgs);
    }

    @Override
    public String getUsage() {
        return "<id/name>";
    }

    @Override
    public String getDesc() {
        return "delete category";
    }

    @Override
    public void perform(String... args) throws CommandException {
        try {
            int name = Integer.parseInt(args[0]);
            getServer().getStorageManager().deleteCategory(name);
        } catch (Exception ex) {
            String name = args[0];
            getServer().getStorageManager().deleteCategory(name);
            LoggerHelper.info(args[0] + " successful deleted");
        }
    }
}
