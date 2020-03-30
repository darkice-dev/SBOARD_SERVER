package SBoardServer.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.text.ParseException;

public class ServerConfig extends ConfigUtils{

    public String host;
    public String user;
    public String password;
    public String database;
    public int serverPort;

    public ServerConfig(String name, Path path) {
        super(name, path);
        JsonParser parser = new JsonParser();
        try {
            JsonObject jsonObject = (JsonObject) parser.parse(new FileReader(getFile().toString()));
            serverPort = jsonObject.get("server-port").getAsInt();
            host = jsonObject.get("host").getAsString();
            user = jsonObject.get("user").getAsString();
            password = jsonObject.get("password").getAsString();
            database = jsonObject.get("database").getAsString();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
