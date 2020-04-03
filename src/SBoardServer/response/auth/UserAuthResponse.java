package SBoardServer.response.auth;

import SBoardServer.SBoardServer;
import SBoardServer.response.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class UserAuthResponse extends Response {

    public UserAuthResponse(SBoardServer server, DataOutputStream dos, DataInputStream dis) {
        super(server, dos, dis);
    }

    @Override
    public void reply() throws Throwable {
        String login = dis.readUTF();
        String pass = dis.readUTF();
        if(!server.getServerManager().isExistUser(login)) {
            requestError("Пользователя с таким именем не существует!");
            return;
        }
        if(!server.getServerManager().doUserAuth(login, pass)) {
            requestError("Неверный пароль!");
            return;
        }
    }
}
