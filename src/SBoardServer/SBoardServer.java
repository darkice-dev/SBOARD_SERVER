package SBoardServer;


import SBoardServer.helpers.IOHelper;
import SBoardServer.utils.MySQL;
import SBoardServer.utils.ServerConfig;

public class SBoardServer {

    public static SBoardServer instance;

    private ServerConfig serverConfig;
    private MySQL mySQL;

    public SBoardServer() {
        instance = this;
        serverConfig = new ServerConfig("config", IOHelper.WORKING_DIR);
    }

    public static void main(String[] args) {

    }

    public ServerConfig getServerConfig() {
        return serverConfig;
    }

    public MySQL getMySQL() {
        return mySQL;
    }
}
