package SBoardServer.response.register;

import SBoardServer.SBoardServer;
import SBoardServer.helpers.LoggerHelper;
import SBoardServer.response.Response;
import SBoardServer.utils.ServerUtils;
import me.Sa1ZeR_.core.request.RequestException;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class UserRegisterResponse extends Response {
    public UserRegisterResponse(SBoardServer server, DataOutputStream dos, DataInputStream dis) {
        super(server, dos, dis);
    }

    @Override
    public void reply() throws Throwable {
        String login = dis.readUTF();
        String email = dis.readUTF();
        String pass = dis.readUTF();
        if(ServerUtils.isExistUserWithLogin(login)) {
            requestError("User with " + login + " login already exist");
            return;
        }
        if(ServerUtils.isExistUserWithMail(email)) {
            requestError("User with " + email + " email already exist");
            return;
        }

        SBoardServer.instance.getStorageManager().createUser(login, pass, "", "", "", email, "", "", System.currentTimeMillis());
        LoggerHelper.info("User successful registered");
    }
}
