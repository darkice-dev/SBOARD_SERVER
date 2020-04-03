package SBoardServer.response;

import SBoardServer.SBoardServer;
import SBoardServer.helpers.LoggerHelper;
import SBoardServer.response.auth.UserAuthResponse;
import SBoardServer.response.register.UserRegisterResponse;
import me.Sa1ZeR_.core.request.Request;

import java.io.*;
import java.net.Socket;

public class ClientSession extends Thread {

    private final Socket socket;
    private DataInputStream dos;
    DataOutputStream dis;

    public ClientSession(Socket socket) throws IOException {
        this.socket = socket;
        dos = new DataInputStream(socket.getInputStream());
        dis = new DataOutputStream(socket.getOutputStream());

    }


    @Override
    public void run() {
        try {
            Request.Type type = Request.Type.getById(dos.readInt());

            Response response = null;
            switch (type) {
                case USERREGISTER:
                    response = new UserRegisterResponse(SBoardServer.instance, dis, dos);
                    break;
                case USERAUTH:
                    response = new UserAuthResponse(SBoardServer.instance, dis, dos);
                    break;
                case EMPLOYEEAUTH:
                    break;
                case COMPANYAUTH:
                    break;
                default:
                    throw new AssertionError("Unsupported request type: " + type.name());
            }
            try {
                response.reply();
            } catch (Throwable throwable) {
                LoggerHelper.error(throwable.getMessage());
            }
            //выход
            this.socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
