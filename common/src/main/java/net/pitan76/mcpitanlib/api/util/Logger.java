package net.pitan76.mcpitanlib.api.util;

public class Logger {
    private final org.apache.logging.log4j.Logger logger;

    public Logger(String modid) {
        this.logger = LoggerUtil.getLogger(modid);
    }

    public org.apache.logging.log4j.Logger getLogger() {
        return logger;
    }

    public void info(String message) {
        LoggerUtil.info(logger, message);
    }

    public void warn(String message) {
        LoggerUtil.warn(logger, message);
    }

    public void error(String message) {
        LoggerUtil.error(logger, message);
    }

    public void debug(String message) {
        LoggerUtil.debug(logger, message);
    }

    public void trace(String message) {
        LoggerUtil.trace(logger, message);
    }

    // ----

    public void error(Exception e) {
        error(e.getMessage());
    }

    public void initializeMessage() {
        info("Initializing...");
    }

    public void initializedMessage() {
        info("Initialized");
    }
}
