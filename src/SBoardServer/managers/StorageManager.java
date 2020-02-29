package SBoardServer.managers;

import SBoardServer.SBoardServer;
import SBoardServer.domain.*;
import SBoardServer.helpers.LoggerHelper;
import SBoardServer.utils.MySQL;

import java.sql.*;
import java.util.HashMap;

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
            statement.execute("CREATE TABLE IF NOT EXISTS `companies` (`id` INT NOT NULL AUTO_INCREMENT," +
                    " `name` VARCHAR(128) NOT NULL," +
                    " `desc` VARCHAR(256) NOT NULL," +
                    " `address` VARCHAR(128) NOT NULL," +
                    " `email` VARCHAR(32) NOT NULL," +
                    " `phone` VARCHAR(16) NOT NULL," +
                    " `rate` FLOAT NOT NULL," +
                    " `created_time` DATETIME NOT NULL," +
                    " PRIMARY KEY (`id`))");

            statement.execute("CREATE TABLE IF NOT EXISTS `employees` (" +
                    " `id` INT NOT NULL AUTO_INCREMENT," +
                    " `name` VARCHAR(16) NOT NULL," +
                    " `sname` VARCHAR(16) NOT NULL," +
                    " `patronymic` VARCHAR(16) NOT NULL," +
                    " `email` VARCHAR(32) NOT NULL," +
                    " `phone` VARCHAR(16) NOT NULL," +
                    " `education` VARCHAR(32) NOT NULL," +
                    " `activities` VARCHAR(128) NOT NULL," +
                    " `rate` FLOAT NOT NULL," +
                    " `created_time` DATETIME NOT NULL," +
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
                    " `created_time` DATETIME NOT NULL," +
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

    public void createCompany(int id, String name, String desc, String address, String email, String phone, float rate, long createdTime) {
        mySQL.getExecutor().submit(new Runnable() {
            @Override
            public void run() {
                PreparedStatement statement = null;
                try {
                    statement = mySQL.getConnection().prepareStatement("INSERT INTO companies (id, name, desc, address, email, phone, rate, created_time) VALUES " +
                            "(?, ?, ?, ?, ?, ?, ?, ?)");
                    statement.setInt(1, id);
                    statement.setString(2, name);
                    statement.setString(3, desc);
                    statement.setString(4, address);
                    statement.setString(5, email);
                    statement.setString(6, phone);
                    statement.setFloat(7, rate);
                    statement.setLong(8, createdTime);
                    statement.execute();
                } catch (SQLException e) {
                    LoggerHelper.error("Error while creating company: \n" + e);
                    e.printStackTrace();
                } finally {
                    close(statement);
                }
            }
        });
    }

    public Company getCompanyFromId(int id) {
        PreparedStatement statement = null;
        Company company = null;
        try {
            statement = mySQL.getConnection().prepareStatement("SELECT * FROM companies WHERE id=?");
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if(set.next()) {
                int cId = set.getInt("id");
                String name = set.getString("name");
                String desc = set.getString("desc");
                String address = set.getString("address");
                String email = set.getString("email");
                String phone = set.getString("phone");
                float rate = set.getFloat("rate");
                long createdTime = set.getLong("created_time");
                company = new Company(cId, name, desc, address, email, phone, rate, createdTime);
            }
        } catch (SQLException e) {
            LoggerHelper.error("Error while getting companies \n" + e);
            e.printStackTrace();
        }
        finally {
            close(statement);
        }
        return company;
    }

    public HashMap<String, Company> getCompanies() {
        HashMap<String, Company> companies = new HashMap<>();
        PreparedStatement statement = null;
        try {
            statement = mySQL.getConnection().prepareStatement("SELECT * FROM companies");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                int cId = set.getInt("id");
                String name = set.getString("name");
                String desc = set.getString("desc");
                String address = set.getString("address");
                String email = set.getString("email");
                String phone = set.getString("phone");
                float rate = set.getFloat("rate");
                long createdTime = set.getLong("created_time");
                Company company = new Company(cId, name, desc, address, email, phone, rate, createdTime);
            }
        } catch (SQLException e) {
            LoggerHelper.error("Error while getting companies \n" + e);
            e.printStackTrace();
        } finally {
            close(statement);
        }
        return companies;
    }

    public void createCategory(int id, String name) {
        mySQL.getExecutor().submit(new Runnable() {
            @Override
            public void run() {
                PreparedStatement statement = null;
                try {
                    statement = mySQL.getConnection().prepareStatement("INSERT INTO categories (id, name) VALUES (?, ?)");
                    statement.setInt(1, id);
                    statement.setString(2, name);
                    statement.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    close(statement);
                }
            }
        });
    }

    public Category getCategoryFromId(int id) {
        PreparedStatement statement = null;
        Category category = null;
        try {
            statement = mySQL.getConnection().prepareStatement("SELECT * FROM categories WHERE id=?");
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if(set.next()) {
                int cId = set.getInt("id");
                String name = set.getString("name");
                category = new Category(cId, name);
            }
        } catch (SQLException e) {
            LoggerHelper.error("Error while getting category \n" + e);
            e.printStackTrace();
        }
        finally {
            close(statement);
        }
        return category;
    }

    public HashMap<String, Category> getCategories() {
        HashMap<String, Category> categories = new HashMap<>();
        PreparedStatement statement = null;
        try {
            statement = mySQL.getConnection().prepareStatement("SELECT * FROM categories");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                int id = set.getInt("id");
                String name = set.getString("name");
                Category category = new Category(id, name);
                categories.put(name, category);
            }
        } catch (SQLException e) {
            LoggerHelper.error("Error while getting category \n" + e);
            e.printStackTrace();
        } finally {
            close(statement);
        }
        return categories;
    }

    public void createUser(int id, String name, String sName, String patronymic, String email, String phone, String address, long createdTime) {
        mySQL.getExecutor().submit(new Runnable() {
            @Override
            public void run() {
                PreparedStatement statement = null;
                try {
                    statement = mySQL.getConnection().prepareStatement("INSERT INTO users (id, name, sname, patronymic, email, phone, address, created_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                    statement.setInt(1, id);
                    statement.setString(2, name);
                    statement.setString(3, sName);
                    statement.setString(4, patronymic);
                    statement.setString(5, email);
                    statement.setString(6, phone);
                    statement.setString(7, address);
                    statement.setLong(8, createdTime);
                    statement.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    close(statement);
                }
            }
        });
    }

    public User getUserFromId(int id) {
        PreparedStatement statement = null;
        User user = null;
        try {
            statement = mySQL.getConnection().prepareStatement("SELECT * FROM users WHERE id=?");
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if(set.next()) {
                int uId = set.getInt("id");
                String name = set.getString("name");
                String sName = set.getString("sname");
                String patronymic = set.getString("patronymic");
                String email = set.getString("email");
                String phone = set.getString("phone");
                String address = set.getString("address");
                long createdTime = set.getLong("created_time");
                user = new User(uId, name, sName, patronymic, email, phone, address, createdTime);
            }
        } catch (SQLException e) {
            LoggerHelper.error("Error while getting user (id =" + id +") \n" + e);
            e.printStackTrace();
        }
        finally {
            close(statement);
        }
        return user;
    }

    public HashMap<String, User> getUsers() {
        HashMap<String, User> users = new HashMap<>();
        PreparedStatement statement = null;
        try {
            statement = mySQL.getConnection().prepareStatement("SELECT * FROM users");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                int uId = set.getInt("id");
                String name = set.getString("name");
                String sName = set.getString("sname");
                String patronymic = set.getString("patronymic");
                String email = set.getString("email");
                String phone = set.getString("phone");
                String address = set.getString("address");
                long createdTime = set.getLong("created_time");
                User user = new User(uId, name, sName, patronymic, email, phone, address, createdTime);
                users.put(name, user);
            }
        } catch (SQLException e) {
            LoggerHelper.error("Error while getting users \n" + e);
            e.printStackTrace();
        } finally {
            close(statement);
        }
        return users;
    }

    public void createEmployee(int id, String name, String sName, String patronymic, String email, String phone, String address, String education, String activities, float rate, long createdTime, int companyId) {
        mySQL.getExecutor().submit(new Runnable() {
            @Override
            public void run() {
                PreparedStatement statement = null;
                try {
                    statement = mySQL.getConnection().prepareStatement("INSERT INTO employees (id, name, sname, patronymic, email, phone, address, education, activities, rate, created_time, company_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    statement.setInt(1, id);
                    statement.setString(2, name);
                    statement.setString(3, sName);
                    statement.setString(4, patronymic);
                    statement.setString(5, email);
                    statement.setString(6, phone);
                    statement.setString(7, address);
                    statement.setString(8, education);
                    statement.setString(9, activities);
                    statement.setFloat(10, rate);
                    statement.setLong(11, createdTime);
                    statement.setInt(12, companyId);
                    statement.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    close(statement);
                }
            }
        });
    }

    public Employee getEmployeeFromId(int id) {
        PreparedStatement statement = null;
        Employee employee = null;
        try {
            statement = mySQL.getConnection().prepareStatement("SELECT * FROM employees WHERE id=?");
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if(set.next()) {
                int uId = set.getInt("id");
                String name = set.getString("name");
                String sName = set.getString("sname");
                String patronymic = set.getString("patronymic");
                String email = set.getString("email");
                String phone = set.getString("phone");
                String address = set.getString("address");
                String education = set.getString("education");
                String activities = set.getString("activities");
                float rate = set.getFloat("rate");
                long createdTime = set.getLong("created_time");
                int companyId = set.getInt("company_id");
                employee = new Employee(uId, name, sName, patronymic, email, phone, address, education, activities, rate, createdTime, companyId);
            }
        } catch (SQLException e) {
            LoggerHelper.error("Error while getting employee (id =" + id +") \n" + e);
            e.printStackTrace();
        }
        finally {
            close(statement);
        }
        return employee;
    }

    public HashMap<String, Employee> getEmployees() {
        HashMap<String, Employee> employees = new HashMap<>();
        PreparedStatement statement = null;
        try {
            statement = mySQL.getConnection().prepareStatement("SELECT * FROM employees");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                int uId = set.getInt("id");
                String name = set.getString("name");
                String sName = set.getString("sname");
                String patronymic = set.getString("patronymic");
                String email = set.getString("email");
                String phone = set.getString("phone");
                String address = set.getString("address");
                String education = set.getString("education");
                String activities = set.getString("activities");
                float rate = set.getFloat("rate");
                long createdTime = set.getLong("created_time");
                int companyId = set.getInt("company_id");
                Employee employee = new Employee(uId, name, sName, patronymic, email, phone, address, education, activities, rate, createdTime, companyId);
                employees.put(name, employee);
            }
        } catch (SQLException e) {
            LoggerHelper.error("Error while getting employees \n" + e);
            e.printStackTrace();
        } finally {
            close(statement);
        }
        return employees;
    }

    public void createService(int id, String name, double price, int employeeId, int categories_id) {
        mySQL.getExecutor().submit(new Runnable() {
            @Override
            public void run() {
                PreparedStatement statement = null;
                try {
                    statement = mySQL.getConnection().prepareStatement("INSERT INTO services (id, name, price, employee_id, categories_id) VALUES (?, ?, ?, ?, ?)");
                    statement.setInt(1, id);
                    statement.setString(2, name);
                    statement.setDouble(3, price);
                    statement.setInt(4, employeeId);
                    statement.setInt(5, categories_id);
                    statement.execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    close(statement);
                }
            }
        });
    }

    public Service getServiceFromId(int id) {
        PreparedStatement statement = null;
        Service service = null;
        try {
            statement = mySQL.getConnection().prepareStatement("SELECT * FROM services WHERE id=?");
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if(set.next()) {
                int sId = set.getInt("id");
                String name = set.getString("name");
                double price = set.getDouble("price");
                int employeeId = set.getInt("employee_id");
                int categoryId = set.getInt("categories_id");
                service = new Service(sId, name, price, employeeId, categoryId);
            }
        } catch (SQLException e) {
            LoggerHelper.error("Error while getting service \n" + e);
            e.printStackTrace();
        }
        finally {
            close(statement);
        }
        return service;
    }

    public HashMap<String, Service> getServices() {
        HashMap<String, Service> services = new HashMap<>();
        PreparedStatement statement = null;
        try {
            statement = mySQL.getConnection().prepareStatement("SELECT * FROM services");
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                int sId = set.getInt("id");
                String name = set.getString("name");
                double price = set.getDouble("price");
                int employeeId = set.getInt("employee_id");
                int categoryId = set.getInt("categories_id");
                Service service = new Service(sId, name, price, employeeId, categoryId);
                services.put(name, service);
            }
        } catch (SQLException e) {
            LoggerHelper.error("Error while getting services \n" + e);
            e.printStackTrace();
        } finally {
            close(statement);
        }
        return services;
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
