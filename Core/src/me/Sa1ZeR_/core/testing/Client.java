package me.Sa1ZeR_.core.testing;


import me.Sa1ZeR_.core.request.Request;
import me.Sa1ZeR_.core.request.RequestException;
import me.Sa1ZeR_.core.request.auth.UserAuthRequest;

import java.net.InetAddress;

public class Client {
    private final InetAddress host;
    private final int port;

    public Client(InetAddress host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {
        UserAuthRequest urr = new UserAuthRequest(Request.Type.USERAUTH.getId(), host, port, "amsher", "123456");
        //UserRegisterRequest urr = new UserRegisterRequest(Request.Type.USERREGISTER.getId(), "Saa1adZeR_", "amadsaher", "123456");
        try {
            urr.request();
        } catch (RequestException e) {
            System.out.println(e.getMessage());
            return;
        }

        //Запуск логики приложения
        this.logicStart();
    }

    public void logicStart() {
        System.out.println("Я отработал");
    }
}
