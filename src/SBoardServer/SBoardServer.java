package SBoardServer;


import SBoardServer.helpers.IOHelper;
import SBoardServer.utils.ServerConfig;

public class SBoardServer {

    private ServerConfig serverConfig;

    public SBoardServer() {
        serverConfig = new ServerConfig("config", IOHelper.WORKING_DIR);
    }

    public static void main(String[] args) {
    }

}
