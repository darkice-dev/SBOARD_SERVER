package SBoardServer.utils;

import SBoardServer.SBoardServer;

import java.io.*;
import java.nio.file.Path;

public class ConfigUtils {

    private File file;

    public ConfigUtils(String name, Path path) {
        file = new File(path.toString() , name + ".json");
        if(!file.exists()) {
            InputStream is = SBoardServer.class.getResourceAsStream("/files/" + name + ".json");
            try {
                FileOutputStream fos = new FileOutputStream(file);
                byte[] bytes = new byte[Byte.MAX_VALUE];
                int n;
                while ((n = is.read(bytes)) != -1) {
                    fos.write(bytes, 0, n);
                    fos.flush();
                }
                fos.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public File getFile() {
        return file;
    }
}
