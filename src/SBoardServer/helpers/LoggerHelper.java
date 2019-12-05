package SBoardServer.helpers;

import SBoardServer.SBoardServer;
import SBoardServer.utils.Debug;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class LoggerHelper {

    static Logger logger;
    static Debug debug;
    static {
        logger = Logger.getLogger(SBoardServer.class);
        debug = new Debug("server", IOHelper.WORKING_DIR);
    }

    public static void info(String message) {
        logger.log(Level.INFO, message);
        debug.info(message);
    }

    public static void error(String message) {
        logger.log(Level.ERROR, message);
        debug.error(message);
    }

    public static void warning(String message) {
        logger.log(Level.WARN, message);
        debug.warning(message);
    }

    public static void debug(String message) {
        logger.log(Level.DEBUG, message);
        debug.debug(message);
    }
}
