package net.pitan76.mcpitanlib.api.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {
    public static Logger getLogger(String name) {
        return LogManager.getLogger(name);
    }

    public static Logger getLogger(Class<?> clazz) {
        return LogManager.getLogger(clazz);
    }

    public static Logger getLogger() {
        return LogManager.getLogger();
    }

    public static void info(Logger logger, String message) {
        logger.info(message);
    }

    public static void warn(Logger logger, String message) {
        logger.warn(message);
    }

    public static void error(Logger logger, String message) {
        logger.error(message);
    }

    public static void debug(Logger logger, String message) {
        logger.debug(message);
    }

    public static void trace(Logger logger, String message) {
        logger.trace(message);
    }
}
