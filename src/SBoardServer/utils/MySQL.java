package SBoardServer.utils;

import SBoardServer.SBoardServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MySQL {

    private String host;
    private String user;
    private String password;
    private String database;
    private String url;
    private ExecutorService executor;

    public MySQL() {
        try { Class.forName("com.mysql.jdbc.Driver").newInstance();
        host = SBoardServer.instance.getServerConfig().host;
        user = SBoardServer.instance.getServerConfig().user;
        password = SBoardServer.instance.getServerConfig().password;
        database = SBoardServer.instance.getServerConfig().database;
        url = "jdbc:mysql://" + host + "/" + database;
        } catch (InstantiationException|IllegalAccessException|ClassNotFoundException e) {
            e.printStackTrace();
        }
        executor = Executors.newSingleThreadExecutor();
    }

    public Connection getConnection() throws SQLException {
        Properties properties = new Properties();
        properties.setProperty("user", user);
        properties.setProperty("password", password);
        properties.setProperty("useUnicode", "true");
        properties.setProperty("connectTimeout", "1000");
        properties.setProperty("socketTimeout", "1000");
        properties.setProperty("autoReconnect", "true");
        properties.setProperty("failOverReadOnly", "false");
        properties.setProperty("maxReconnects", "10");
        properties.setProperty("characterEncoding", "utf-8");
        return DriverManager.getConnection(url,properties);
    }

    public ExecutorService getExecutor() {
        return executor;
    }
}
