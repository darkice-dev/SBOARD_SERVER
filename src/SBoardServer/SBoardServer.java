package SBoardServer;


import SBoardServer.helpers.IOHelper;
import SBoardServer.managers.StorageManager;
import SBoardServer.utils.MySQL;
import SBoardServer.utils.ServerConfig;

public class SBoardServer {

    public static SBoardServer instance;

    private ServerConfig serverConfig;
    private MySQL mySQL;
    private StorageManager storageManager;

    public SBoardServer() {
        instance = this;
        serverConfig = new ServerConfig("config", IOHelper.WORKING_DIR);
        mySQL = new MySQL();
        storageManager = new StorageManager();
    }

    public static void main(String[] args) {
        new SBoardServer();
    }

    public ServerConfig getServerConfig() {
        return serverConfig;
    }

    public MySQL getMySQL() {
        return mySQL;
    }
}
