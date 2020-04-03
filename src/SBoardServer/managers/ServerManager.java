package SBoardServer.managers;

import SBoardServer.SBoardServer;

public class ServerManager {

    private SBoardServer server;

    public ServerManager(SBoardServer server) {
        this.server = server;
    }

    public boolean isExistUser(String name) {
        return server.getStorageManager().isExistUser(name);
    }

    public boolean doUserAuth(String user, String password) {
        return server.getStorageManager().userAuth(user, password);
    }

    public boolean isExistCompany(String email) {
        return server.getStorageManager().isExistCompany(email);
    }

    public boolean doCompanyAuth(String email, String password) {
        return server.getStorageManager().companyAuth(email, password);
    }

    public boolean isExistEmployee(String email) {
        return server.getStorageManager().isExistEmployee(email);
    }

    public boolean doEmployeeAuth(String email, String password) {
        return server.getStorageManager().companyAuth(email, password);
    }
}
