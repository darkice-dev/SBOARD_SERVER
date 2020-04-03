package me.Sa1ZeR_.core.request;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public abstract class Request/* implements IMessage*/ {

    protected int id;
    protected InetAddress address;
    protected int port;


    public Request(int id, InetAddress address, int port) {
        this.id = id;
        this.address = address;
        this.port = port;
    }

    public abstract void requestDo(DataOutputStream dos, DataInputStream dis) throws IOException, RequestException;


    public void readError(DataInputStream dis) throws RequestException {
        String msg = null;
        try {
            msg = dis.readUTF();
        } catch (IOException e) {
            ;
        }
        if(msg != null && !msg.isEmpty()) {
            requestError(msg);
        }
    }

    public void requestError(String msg) throws RequestException {
        throw new RequestException(msg);
    }

    public void request() throws RequestException {
        try(Socket socket = new Socket(address, port)) {
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            requestDo(dos, dis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum Type {
        USERREGISTER(0), //register requests
        USERAUTH(1), EMPLOYEEAUTH(2), COMPANYAUTH(3); // userAuth requests

        private int id;
        private Type(int i) {
            id = i;
        }

        public int getId() {
            return id;
        }

        public static Type getById(int id) {
            for(Type t : values()) {
                if(t.getId() == id) {
                    return t;
                }
            }
            return null;
        }
    }
}
