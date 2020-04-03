package me.Sa1ZeR_.core.request.auth;

import me.Sa1ZeR_.core.request.Request;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;

public class CompanyAuthRequest extends Request {

    private String email;
    private String pass;

    public CompanyAuthRequest(int id, InetAddress address, int port, String email, String pass) {
        super(id,address, port);
        this.email = email;
        this.pass = pass;
    }

    @Override
    public void requestDo(DataOutputStream dos, DataInputStream dis) throws IOException {
        dos.writeInt(id);
        dos.writeUTF(email);
        dos.writeUTF(pass);
    }
}
