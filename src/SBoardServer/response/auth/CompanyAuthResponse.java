package SBoardServer.response.auth;

import SBoardServer.SBoardServer;
import SBoardServer.response.Response;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public class CompanyAuthResponse extends Response {
    public CompanyAuthResponse(SBoardServer server, DataOutputStream dos, DataInputStream dis) {
        super(server, dos, dis);
    }

    @Override
    public void reply() throws Throwable {
        String login = dis.readUTF();
        String pass = dis.readUTF();
        if(!server.getServerManager().isExistCompany(login)) {
            requestError("Компании с таким email'ом не существует!");
            return;
        }
        if(!server.getServerManager().doUserAuth(login, pass)) {
            requestError("Неверный пароль!");
            return;
        }
    }
}
