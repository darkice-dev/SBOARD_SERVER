package me.Sa1ZeR_.core.request.register;

import me.Sa1ZeR_.core.request.Request;
import me.Sa1ZeR_.core.request.RequestException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;

public class UserRegisterRequest extends Request {

    private String login;
    private String email;
    private String pass;

    public UserRegisterRequest(int id, InetAddress address, int port, String login, String email, String pass) {
        super(id, address, port);
        this.login = login;
        this.email = email;
        this.pass = pass;
    }

    @Override
    public void requestDo(DataOutputStream dos, DataInputStream dis) throws IOException, RequestException {
        dos.writeInt(id);
        dos.writeUTF(login);
        dos.writeUTF(email);
        dos.writeUTF(pass);
        readError(dis);
    }
}
