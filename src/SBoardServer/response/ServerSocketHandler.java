package SBoardServer.response;

import SBoardServer.SBoardServer;
import SBoardServer.helpers.LoggerHelper;
import SBoardServer.utils.ServerConfig;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketHandler extends Thread {

    private SBoardServer server;
    private int port;
    public ServerSocketHandler(SBoardServer server) {
        this.server = server;
        port = server.getServerConfig().serverPort;
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            LoggerHelper.info("Server socket thread successfully started");

            while (serverSocket.isBound()) {
                Socket socket = serverSocket.accept();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
