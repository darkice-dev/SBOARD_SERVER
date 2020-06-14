package SBoardServer.commands.services;

import SBoardServer.SBoardServer;
import SBoardServer.commands.AbstractCommand;
import SBoardServer.commands.CommandException;
import SBoardServer.domain.Service;
import SBoardServer.domain.User;
import SBoardServer.helpers.LoggerHelper;

import java.util.Set;

public class GetUserServiceCommand extends AbstractCommand {
    public GetUserServiceCommand(SBoardServer server, int minArgs) {
        super(server, minArgs);
    }

    @Override
    public String getUsage() {
        return "<user email>";
    }

    @Override
    public String getDesc() {
        return "get user's services";
    }

    @Override
    public void perform(String... args) throws CommandException {
        String emailUser = args[0];
        User user = getServer().getStorageManager().getUserFromMail(emailUser);
        if(user == null) {
            LoggerHelper.info("Пользователь с такой почтой не найден");
            return;
        }
        Set<Service> set = getServer().getStorageManager().getServiceByUserId(user.getId());
        if(set == null || set.size() == 0) {
            LoggerHelper.info("Данный пользователь никуда не записан");
        }
        for(Service service : set) {
            LoggerHelper.info("Услуга: " + service.getName() + " | Стоимость" + service.getPrice());
        }
    }
}
