package SBoardServer.commands.statistic;

import SBoardServer.SBoardServer;
import SBoardServer.commands.AbstractCommand;
import SBoardServer.commands.CommandException;
import SBoardServer.domain.Company;
import SBoardServer.domain.Employee;
import SBoardServer.helpers.LoggerHelper;

public class GetEmployeeStatisticCommand extends AbstractCommand {
    public GetEmployeeStatisticCommand(SBoardServer server, int minArgs) {
        super(server, minArgs);
    }

    @Override
    public String getUsage() {
        return "employee id";
    }

    @Override
    public String getDesc() {
        return "getting employee rating";
    }

    @Override
    public void perform(String... args) throws CommandException {
        Employee employee = getServer().getStorageManager().getEmployeeFromId(Integer.parseInt(args[0]));
        if(employee == null) {
            LoggerHelper.error("Компания с таким ID не найдена");
        }
        LoggerHelper.info("Рейтинг сотрудника " + employee.getName() +": " + employee.getRate());
    }
}
