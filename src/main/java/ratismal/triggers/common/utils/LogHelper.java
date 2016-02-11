package ratismal.triggers.common.utils;


import org.apache.logging.log4j.Logger;
import ratismal.triggers.TriggersMod;
import ratismal.triggers.common.handler.ConfigHandler;

/**
 * Created by Ratismal on 2016-02-04.
 */

public class LogHelper {

    private static Logger log = TriggersMod.logger;

    public static void trace(String message) {
        log.trace(processMessage(message));
    }

    public static void debug(String message) {
        log.debug(processMessage(message));
    }

    public static void info(String message) {
        log.info(processMessage(message));
    }

    public static void debugInfo(String message) {
        if (ConfigHandler.isDebugMessages()) {
            log.info(processMessage(message));
        }
    }

    public static void warn(String message) {
        log.warn(processMessage(message));
    }

    public static void error(String message) {
        log.error(processMessage(message));
    }

    public static void fatal(String message) {
        log.fatal(processMessage(message));
    }

    private static String processMessage(String message) {
        return "[Triggers] " + message;
    }


}
