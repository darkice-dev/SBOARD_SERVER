package SBoardServer.helpers;

import java.nio.file.Path;
import java.nio.file.Paths;

public class IOHelper {
    static {
        WORKING_DIR = Paths.get(System.getProperty("user.dir"));
    }
    public static final Path WORKING_DIR;
}
