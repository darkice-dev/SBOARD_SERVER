package me.Sa1ZeR_.core.request.auth;

import me.Sa1ZeR_.core.request.Request;
import me.Sa1ZeR_.core.request.RequestException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;

public class UserAuthRequest extends Request {

    private String login;
    private String pass;

    public UserAuthRequest(int id, InetAddress address, int port, String login, String pass) {
        super(id, address, port);
        this.login = login;
        this.pass = pass;
    }

    @Override
    public void requestDo(DataOutputStream dos, DataInputStream dis) throws IOException, RequestException {
        dos.writeInt(id);
        dos.writeUTF(login);
        dos.writeUTF(pass);
        readError(dis);
    }
}
