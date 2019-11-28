package SBoardServer.managers;

import SBoardServer.SBoardServer;

import java.sql.Connection;
import java.sql.SQLException;

public class StorageManager {

    private Connection connection;

    public StorageManager() {
        try {
            connection = SBoardServer.instance.getMySQL().getConnection();
        } catch (SQLException e) {
            System.exit(-1);
            //todo logging;
        }
    }

    public void prepareDB() {
        //todo
    }
}
