package SBoardServer.commands.category;

import SBoardServer.SBoardServer;
import SBoardServer.commands.AbstractCommand;
import SBoardServer.commands.CommandException;
import SBoardServer.domain.Category;
import SBoardServer.helpers.LoggerHelper;

import java.util.Set;

public class CategoriesListCommand extends AbstractCommand {
    public CategoriesListCommand(SBoardServer server, int minArgs) {
        super(server, minArgs);
    }

    @Override
    public String getUsage() {
        return "";
    }

    @Override
    public String getDesc() {
        return "list of categories";
    }

    @Override
    public void perform(String... args) throws CommandException {
        Set<Category> categories = getServer().getStorageManager().getCategories();
        for(Category c : categories) {
            LoggerHelper.info("[id: " + c.getId() + ", name: " + c.getName()+ "]");
        }
    }
}
