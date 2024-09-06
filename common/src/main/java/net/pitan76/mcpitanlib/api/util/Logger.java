package net.pitan76.mcpitanlib.api.util;

public class Logger {
    public String name = "";

    public boolean usingPrefix = true;

    private final org.apache.logging.log4j.Logger logger;

    public Logger(String name) {
        this.name = name;
        this.logger = LoggerUtil.getLogger(name);
    }

    public Logger() {
        this.logger = LoggerUtil.getLogger();
    }

    public Logger(Class<?> clazz) {
        this.logger = LoggerUtil.getLogger(clazz);
    }

    public Logger(String name, boolean usePrefix) {
        this(name);
        usePrefix(usePrefix);
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
        info(prefix() + "Initializing...");
    }

    public void initializedMessage() {
        info(prefix() + "Initialized");
    }

    public String prefix() {
        if (name.isEmpty() || !isUsingPrefix())
            return "";

        return "[" + name + "] ";
    }

    public void usePrefix(boolean use) {
        usingPrefix = use;
    }

    public boolean isUsingPrefix() {
        return usingPrefix;
    }
}
