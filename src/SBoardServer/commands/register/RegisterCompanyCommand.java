package SBoardServer.commands.register;

import SBoardServer.SBoardServer;
import SBoardServer.commands.AbstractCommand;
import SBoardServer.commands.CommandException;
import SBoardServer.helpers.LoggerHelper;
import SBoardServer.utils.ServerUtils;

public class RegisterCompanyCommand extends AbstractCommand {
    public RegisterCompanyCommand(SBoardServer server, int minArgs) {
        super(server, minArgs);
    }

    @Override
    public String getUsage() {
        return "<email> <password>";
    }

    @Override
    public String getDesc() {
        return "Company registration";
    }

    @Override
    public void perform(String... args) throws CommandException {
        verifyArgs(args, getMinArgs());
        if(ServerUtils.isExistCompanyWithMail(args[0])) {
            LoggerHelper.warning("Company with " + args[0] + " email already exist");
            return;
        }
        String md5Pass = ServerUtils.getMD5(args[1]);
        SBoardServer.instance.getStorageManager().createCompany("", "", "", args[0], md5Pass, "", 0, System.currentTimeMillis());
        LoggerHelper.info("Company successful registered");
    }
}
