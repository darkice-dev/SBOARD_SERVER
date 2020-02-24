package SBoardServer.managers;

import SBoardServer.SBoardServer;
import SBoardServer.helpers.LoggerHelper;
import SBoardServer.utils.MySQL;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StorageManager {

    private MySQL mySQL;

    public StorageManager() {
        mySQL = SBoardServer.instance.getMySQL();
        prepareDB();
    }

    private void prepareDB() {
        try {
            LoggerHelper.info("Preparing DataBase ....");
            Statement statement = mySQL.getConnection().createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS `company` (`id` INT NOT NULL AUTO_INCREMENT," +
                    " `name` VARCHAR(128) NOT NULL," +
                    " `desc` VARCHAR(256) NOT NULL," +
                    " `address` VARCHAR(128) NOT NULL," +
                    " `email` VARCHAR(32) NOT NULL," +
                    " `phone` VARCHAR(16) NOT NULL," +
                    " `rate` FLOAT NOT NULL," +
                    " PRIMARY KEY (`id`))");

            statement.execute("CREATE TABLE IF NOT EXISTS `employee` (" +
                    " `id` INT NOT NULL AUTO_INCREMENT," +
                    " `name` VARCHAR(16) NOT NULL," +
                    " `sname` VARCHAR(16) NOT NULL," +
                    " `patronymic` VARCHAR(16) NOT NULL," +
                    " `email` VARCHAR(32) NOT NULL," +
                    " `phone` VARCHAR(16) NOT NULL," +
                    " `education` VARCHAR(32) NOT NULL," +
                    " `activities` VARCHAR(128) NOT NULL," +
                    " `rate` FLOAT NOT NULL," +
                    " `company_id` INT NOT NULL," +
                    " PRIMARY KEY (`id`)," +
                    " INDEX `fk_employee_company_idx` (`company_id` ASC) VISIBLE," +
                    " CONSTRAINT `fk_employee_company`" +
                    " FOREIGN KEY (`company_id`)" +
                    " REFERENCES `sboard_db`.`company` (`id`)" +
                    " ON DELETE NO ACTION" +
                    " ON UPDATE NO ACTION)");

            statement.execute("CREATE TABLE IF NOT EXISTS `services` (" +
                    "  `id` INT NOT NULL AUTO_INCREMENT," +
                    "  `name` VARCHAR(32) NOT NULL," +
                    "  `price` DOUBLE NOT NULL," +
                    "  `employee_id` INT NOT NULL," +
                    "  `categories_id` INT NOT NULL," +
                    "  PRIMARY KEY (`id`)," +
                    "  INDEX `fk_services_employee1_idx` (`employee_id` ASC) VISIBLE," +
                    "  INDEX `fk_services_categories1_idx` (`categories_id` ASC) VISIBLE," +
                    "  CONSTRAINT `fk_services_employee1`" +
                    "    FOREIGN KEY (`employee_id`)" +
                    "    REFERENCES `sboard_db`.`employee` (`id`)" +
                    "    ON DELETE NO ACTION" +
                    "    ON UPDATE NO ACTION," +
                    "  CONSTRAINT `fk_services_categories1`" +
                    "    FOREIGN KEY (`categories_id`)" +
                    "    REFERENCES `sboard_db`.`categories` (`id`)" +
                    "    ON DELETE NO ACTION" +
                    "    ON UPDATE NO ACTION)");

            statement.execute("CREATE TABLE IF NOT EXISTS `categories` (" +
                    "  `id` INT NOT NULL AUTO_INCREMENT," +
                    "  `name` VARCHAR(32) NOT NULL," +
                    "  PRIMARY KEY (`id`))");

            statement.execute("CREATE TABLE IF NOT EXISTS `reviews` (" +
                    "  `id` INT NOT NULL AUTO_INCREMENT," +
                    "  `message` TEXT NOT NULL," +
                    "  `rate` FLOAT NOT NULL," +
                    "  `services_id` INT NOT NULL," +
                    "  `users_id` INT NOT NULL," +
                    "  PRIMARY KEY (`id`)," +
                    "  INDEX `fk_reviews_services1_idx` (`services_id` ASC) VISIBLE," +
                    "  INDEX `fk_reviews_users1_idx` (`users_id` ASC) VISIBLE," +
                    "  CONSTRAINT `fk_reviews_services1`" +
                    "    FOREIGN KEY (`services_id`)" +
                    "    REFERENCES `sboard_db`.`services` (`id`)" +
                    "    ON DELETE NO ACTION" +
                    "    ON UPDATE NO ACTION," +
                    "  CONSTRAINT `fk_reviews_users1`" +
                    "    FOREIGN KEY (`users_id`)" +
                    "    REFERENCES `sboard_db`.`users` (`id`)" +
                    "    ON DELETE NO ACTION" +
                    "    ON UPDATE NO ACTION)");

            statement.execute("CREATE TABLE IF NOT EXISTS `users` (" +
                    "  `id` INT NOT NULL AUTO_INCREMENT," +
                    "  `name` VARCHAR(16) NOT NULL," +
                    "  `sname` VARCHAR(16) NOT NULL," +
                    "  `patronymic` VARCHAR(16) NOT NULL," +
                    "  `email` VARCHAR(32) NOT NULL," +
                    "  `phone` VARCHAR(16) NOT NULL," +
                    "  `address` VARCHAR(64) NOT NULL," +
                    "  PRIMARY KEY (`id`))");

            statement.execute("CREATE TABLE IF NOT EXISTS `timetable` (" +
                    "  `id` INT NOT NULL AUTO_INCREMENT," +
                    "  `time_start` DATETIME NOT NULL," +
                    "  `time_end` DATETIME NOT NULL," +
                    "  `services_id` INT NOT NULL," +
                    "  `users_id` INT NULL," +
                    "  PRIMARY KEY (`id`)," +
                    "  INDEX `fk_timetable_services1_idx` (`services_id` ASC) VISIBLE," +
                    "  INDEX `fk_timetable_users1_idx` (`users_id` ASC) VISIBLE," +
                    "  CONSTRAINT `fk_timetable_services1`" +
                    "    FOREIGN KEY (`services_id`)" +
                    "    REFERENCES `sboard_db`.`services` (`id`)" +
                    "    ON DELETE NO ACTION" +
                    "    ON UPDATE NO ACTION," +
                    "  CONSTRAINT `fk_timetable_users1`" +
                    "    FOREIGN KEY (`users_id`)" +
                    "    REFERENCES `sboard_db`.`users` (`id`)" +
                    "    ON DELETE NO ACTION" +
                    "    ON UPDATE NO ACTION)");

            statement.execute("CREATE TABLE IF NOT EXISTS `sboard_db`.`orders` (" +
                    "  `id` INT NOT NULL AUTO_INCREMENT," +
                    "  `time_start` DATETIME NOT NULL," +
                    "  `time_end` DATETIME NOT NULL," +
                    "  `users_id` INT NOT NULL," +
                    "  `services_id` INT NOT NULL," +
                    "  PRIMARY KEY (`id`)," +
                    "  INDEX `fk_orders_users1_idx` (`users_id` ASC) VISIBLE," +
                    "  INDEX `fk_orders_services1_idx` (`services_id` ASC) VISIBLE," +
                    "  CONSTRAINT `fk_orders_users1`" +
                    "    FOREIGN KEY (`users_id`)" +
                    "    REFERENCES `sboard_db`.`users` (`id`)" +
                    "    ON DELETE NO ACTION" +
                    "    ON UPDATE NO ACTION," +
                    "  CONSTRAINT `fk_orders_services1`" +
                    "    FOREIGN KEY (`services_id`)" +
                    "    REFERENCES `sboard_db`.`services` (`id`)" +
                    "    ON DELETE NO ACTION" +
                    "    ON UPDATE NO ACTION)");

            statement.execute("CREATE TABLE IF NOT EXISTS `sboard_db`.`archive` (" +
                    "  `id` INT NOT NULL AUTO_INCREMENT," +
                    "  `time_start` DATETIME NOT NULL," +
                    "  `time_end` DATETIME NOT NULL," +
                    "  `users_id` INT NOT NULL," +
                    "  `services_id` INT NOT NULL," +
                    "  PRIMARY KEY (`id`)," +
                    "  INDEX `fk_archive_users1_idx` (`users_id` ASC) VISIBLE," +
                    "  INDEX `fk_archive_services1_idx` (`services_id` ASC) VISIBLE," +
                    "  CONSTRAINT `fk_archive_users1`" +
                    "    FOREIGN KEY (`users_id`)" +
                    "    REFERENCES `sboard_db`.`users` (`id`)" +
                    "    ON DELETE NO ACTION" +
                    "    ON UPDATE NO ACTION," +
                    "  CONSTRAINT `fk_archive_services1`" +
                    "    FOREIGN KEY (`services_id`)" +
                    "    REFERENCES `sboard_db`.`services` (`id`)" +
                    "    ON DELETE NO ACTION" +
                    "    ON UPDATE NO ACTION)");

            LoggerHelper.info("Database preparation completed successfully");
        } catch (SQLException e) {
            LoggerHelper.error("Error while preparing DB... \n" + e);
            e.printStackTrace();
            System.exit(0);
        }
    }

    private void close(Statement statement) {
        mySQL.getExecutor().submit(new Runnable() {
            @Override
            public void run() {
                if(statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        LoggerHelper.error("SQL Error. Statement can't be closed. \n" + e);
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
