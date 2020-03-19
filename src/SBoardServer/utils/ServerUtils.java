package SBoardServer.utils;

import SBoardServer.SBoardServer;
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
        System.out.println(sb.toString());
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
}
