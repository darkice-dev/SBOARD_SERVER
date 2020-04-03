package SBoardServer;


import SBoardServer.commands.CommandHandler;
import SBoardServer.helpers.IOHelper;
import SBoardServer.helpers.LoggerHelper;
import SBoardServer.managers.ServerManager;
import SBoardServer.managers.StorageManager;
import SBoardServer.response.ServerSocketHandler;
import SBoardServer.utils.MySQL;
import SBoardServer.utils.ServerConfig;

public class SBoardServer {

    public static SBoardServer instance;

    private ServerConfig serverConfig;
    private MySQL mySQL;
    private StorageManager storageManager;
    private ServerManager serverManager;
    private CommandHandler commandHandler;
    private ServerSocketHandler serverSocketHandler;

    public SBoardServer() {
        instance = this;
        serverConfig = new ServerConfig("config", IOHelper.WORKING_DIR);
        mySQL = new MySQL();
        storageManager = new StorageManager();
        serverManager = new ServerManager(this);
        commandHandler = new CommandHandler(this);
        commandHandler.start();
        serverSocketHandler = new ServerSocketHandler(this);
        serverSocketHandler.start();
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

    public CommandHandler getCommandHandler() {
        return commandHandler;
    }

    public ServerManager getServerManager() {
        return serverManager;
    }
}
