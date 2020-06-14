package me.Sa1ZeR_.core.testing;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    public static InetAddress host;
    public static int port = Integer.parseInt("777");
    public static void main(String[] args) {
        try {
            host = InetAddress.getByName("127.0.0.1");

            //System.out.println(id);
            Client client = new Client(host, port);
            //Запускаем логику клиента
            client.start();

        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
