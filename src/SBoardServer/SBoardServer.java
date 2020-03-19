package SBoardServer;


import SBoardServer.commands.CommandHandler;
import SBoardServer.helpers.IOHelper;
import SBoardServer.helpers.LoggerHelper;
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
        LoggerHelper loggerHelper = new LoggerHelper();
        CommandHandler commandHandler = new CommandHandler(this);
        commandHandler.start();
        loggerHelper.info("qq");
        LoggerHelper.info("adwwad");
        LoggerHelper.info("q");
        LoggerHelper.error("ERROR");
        LoggerHelper.error("ERROR X2");
        LoggerHelper.warning("warning");
        LoggerHelper.warning("warning x2");
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

    public StorageManager getStorageManager() {
        return storageManager;
    }
}
