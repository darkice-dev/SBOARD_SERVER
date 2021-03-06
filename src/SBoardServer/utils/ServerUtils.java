package SBoardServer.utils;

import SBoardServer.SBoardServer;
import SBoardServer.domain.Company;
import SBoardServer.domain.Employee;
import SBoardServer.domain.User;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ServerUtils {

    public static String getMD5(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        messageDigest.update(str.getBytes());
        byte[] bytes = messageDigest.digest();
        StringBuilder sb = new StringBuilder();
        for(Byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static boolean isExistUserWithLogin(String login) {
        User user = SBoardServer.instance.getStorageManager().getUserFromLogin(login);
        if(user != null) {
            return true;
        }
        return false;
    }

    public static boolean isExistUserWithMail(String mail) {
        User user = SBoardServer.instance.getStorageManager().getUserFromMail(mail);
        if(user != null) {
            return true;
        }
        return false;
    }

    public static boolean isExistCompanyWithMail(String mail) {
        Company company = SBoardServer.instance.getStorageManager().getCompanyFromMail(mail);
        if(company != null) {
            return true;
        }
        return false;
    }

    public static boolean isExistEmployeeWithMail(String mail) {
        Employee employee = SBoardServer.instance.getStorageManager().getEmployeeFromMail(mail);
        if(employee != null) {
            return true;
        }
        return false;
    }

    public static Company getCompany(int id) {
        Company company = SBoardServer.instance.getStorageManager().getCompanyFromId(id);
        if(company != null) {
            return company;
        }
        return null;
    }
}
