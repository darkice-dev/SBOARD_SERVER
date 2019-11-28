package SBoardServer.utils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;

public class ServerConfig extends ConfigUtils{

    public String host;
    public String user;
    public String password;
    public String database;

    public ServerConfig(String name, Path path) {
        super(name, path);
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader(getFile().toString()));
            host = (String) jsonObject.get("host");
            user = (String) jsonObject.get("user");
            password = (String) jsonObject.get("password");
            database = (String) jsonObject.get("database");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
