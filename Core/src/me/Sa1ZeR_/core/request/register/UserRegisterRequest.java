package core.request.register;

import me.Sa1ZeR_.core.request.Request;
import me.Sa1ZeR_.core.request.RequestException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;

public class UserRegisterRequest extends Request {

    private String name;
    private String sName;
    private String patronymic;
    private String login;
    private String email;
    private String phone;
    private String pass;

    public UserRegisterRequest(int id, InetAddress address, int port, String login, String email, String name, String sName, String patronymic, String phone, String pass) {
        super(id, address, port);
        this.login = login;
        this.email = email;
        this.name = name;
        this.sName = sName;
        this.patronymic = patronymic;
        this.phone = phone;
        this.pass = pass;
    }

    @Override
    public void requestDo(DataOutputStream dos, DataInputStream dis) throws IOException, RequestException {
        dos.writeInt(id);
        dos.writeUTF(login);
        dos.writeUTF(email);
        dos.writeUTF(name);
        dos.writeUTF(sName);
        dos.writeUTF(patronymic);
        dos.writeUTF(phone);
        dos.writeUTF(pass);
        readError(dis);
    }
}
