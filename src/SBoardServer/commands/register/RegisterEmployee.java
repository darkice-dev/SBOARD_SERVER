package SBoardServer.commands.register;

import SBoardServer.SBoardServer;
import SBoardServer.commands.AbstractCommand;
import SBoardServer.commands.CommandException;
import SBoardServer.domain.Company;
import SBoardServer.helpers.LoggerHelper;
import SBoardServer.utils.ServerUtils;

public class RegisterEmployee extends AbstractCommand {
    public RegisterEmployee(SBoardServer server, int minArgs) {
        super(server, minArgs);
    }

    @Override
    public String getUsage() {
        return "<email> <password> <companyID>";
    }

    @Override
    public String getDesc() {
        return "Employee registration";
    }

    @Override
    public void perform(String... args) throws CommandException {
        verifyArgs(args, getMinArgs());
        if(ServerUtils.isExistEmployeeWithMail(args[0])) {
            LoggerHelper.warning("Employee with " + args[0] + " email already exist");
            return;
        }
        String md5Pass = ServerUtils.getMD5(args[1]);
        Company company = ServerUtils.getCompany(Integer.valueOf(args[2]));
        if(company == null) {
            LoggerHelper.warning("Unknown company id: " + args[2]);
            return;
        }
        SBoardServer.instance.getStorageManager().createEmployee("", "", "", args[0], md5Pass, "", "", "", "", 0F, System.currentTimeMillis(), company.getId());
        LoggerHelper.info("Employee successful registered");
    }
}
