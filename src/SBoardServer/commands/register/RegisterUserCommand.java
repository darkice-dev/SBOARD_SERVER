package SBoardServer.commands.register;

import SBoardServer.SBoardServer;
import SBoardServer.commands.AbstractCommand;
import SBoardServer.commands.CommandException;
import SBoardServer.helpers.LoggerHelper;
import SBoardServer.managers.StorageManager;
import SBoardServer.utils.ServerUtils;

public class RegisterUserCommand extends AbstractCommand {

    public RegisterUserCommand(SBoardServer server, int args) {
        super(server, args);
    }

    @Override
    public String getUsage() {
        return "<login> <password> <email>";
    }

    @Override
    public String getDesc() {
        return "User registration";
    }

    @Override
    public void perform(String... args) throws CommandException {
        verifyArgs(args, getMinArgs());
        if(ServerUtils.isExistUserWithLogin(args[0])) {
            LoggerHelper.warning("User with " + args[0] + " login already exist");
            return;
        }
        if(ServerUtils.isExistUserWithMail(args[2])) {
            LoggerHelper.warning("User with " + args[2] + " email already exist");
            return;
        }
        String md5Pass = ServerUtils.getMD5(args[1]);
        SBoardServer.instance.getStorageManager().createUser(args[0], md5Pass, "", "", "", args[2], "", "", System.currentTimeMillis());
        LoggerHelper.info("User successful registered");
    }
}
