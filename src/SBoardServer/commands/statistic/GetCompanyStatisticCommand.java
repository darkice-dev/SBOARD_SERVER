package SBoardServer.commands.statistic;

import SBoardServer.SBoardServer;
import SBoardServer.commands.AbstractCommand;
import SBoardServer.commands.CommandException;
import SBoardServer.domain.Company;
import SBoardServer.helpers.LoggerHelper;

public class GetCompanyStatisticCommand extends AbstractCommand {
    public GetCompanyStatisticCommand(SBoardServer server, int minArgs) {
        super(server, minArgs);
    }

    @Override
    public String getUsage() {
        return "<company id>";
    }

    @Override
    public String getDesc() {
        return "get company rating";
    }

    @Override
    public void perform(String... args) throws CommandException {
        Company company = getServer().getStorageManager().getCompanyFromId(Integer.parseInt(args[0]));
        if(company == null) {
            LoggerHelper.error("Компания с таким ID не найдена");
        }
        LoggerHelper.info("Рейтинг компании " + company.getName() +": " + company.getRate());
    }
}
