package SBoardServer.commands.register;

import SBoardServer.SBoardServer;
import SBoardServer.commands.AbstractCommand;
import SBoardServer.commands.CommandException;
import SBoardServer.helpers.LoggerHelper;
import SBoardServer.managers.StorageManager;
import SBoardServer.utils.ServerUtils;

public class RegisterCommand extends AbstractCommand {

    public RegisterCommand(SBoardServer server) {
        super(server);
    }

    @Override
    public String getUsage() {
        return "<type> <login> <password> <email>";
    }

    @Override
    public void perform(String... args) throws CommandException {
        verifyArgs(args, 4);
        switch (args[0]) {
            case "user":
                if(ServerUtils.isExistUserWithMail(args[1])) {
                    LoggerHelper.warning("User whith " + args[1] + " login already exist");
                    return;
                }
                if(ServerUtils.isExistUserWithMail(args[3])) {
                    LoggerHelper.warning("User whith " + args[3] + " email already exist");
                    return;
                }
                String md5Pass = ServerUtils.getMD5(args[2]);
                SBoardServer.instance.getStorageManager().createUser(args[1], md5Pass, "", "", "", args[3], "", "", System.currentTimeMillis());
                LoggerHelper.info("User successful registered");
        }
    }
}
