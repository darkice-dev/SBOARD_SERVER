package SBoardServer.response;

import SBoardServer.SBoardServer;
import me.Sa1ZeR_.core.request.RequestException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public abstract class Response {

    protected DataOutputStream dos;
    protected DataInputStream dis;
    protected SBoardServer server;

    public Response(SBoardServer server, DataOutputStream dos, DataInputStream dis){
        this.server = server;
        this.dos = dos;
        this.dis = dis;
    }

    public abstract void reply() throws Throwable;

    public void requestError(String msg) throws RequestException {
        try {
            dos.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new RequestException(msg);
    }
}
