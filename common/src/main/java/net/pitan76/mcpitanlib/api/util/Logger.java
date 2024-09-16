package net.pitan76.mcpitanlib.api.util;

import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.util.client.ClientUtil;

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

    public void infoIfDev(String message) {
        if (!PlatformUtil.isDevelopmentEnvironment()) return;
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

    /**
     * Log message
     * @param message log message
     * @param isDebug if true, log as debug
     */
    public void log(String message, boolean isDebug) {
        if (isDebug) {
            debug(message);
            return;
        }
        info(message);
    }

    public void log_onClientTick(int tick, String message, boolean isDebug) {
        if (!PlatformUtil.isClient()) return;
        long time = ClientUtil.getRenderTime();
        if (time % tick != 0) return;

        log(message, isDebug);
    }

    public void log_onWorldTick(int tick, String message, World world, boolean isDebug) {
        long time = WorldUtil.getTime(world);
        if (time % tick != 0) return;

        log(message, isDebug);
    }

    public void log_onSystemTick(int tick, String message, boolean isDebug) {
        long millis = System.currentTimeMillis();
        long time = millis / 1000 * 60;

        if (time % tick != 0) return;

        log(message, isDebug);
    }

    public void log_onClientTick(int tick, String message) {
        log_onClientTick(tick, message, false);
    }

    public void log_onWorldTick(int tick, String message, World world) {
        log_onWorldTick(tick, message, world, false);
    }

    public void log_onSystemTick(int tick, String message) {
        log_onSystemTick(tick, message, false);
    }

    /**
     * Log message every second on ClientTick
     * @param message log message
     * @param isDebug if true, log as debug
     */
    public void log_onClientTick(String message, boolean isDebug) {
        log_onClientTick(60, message, isDebug);
    }

    /**
     * Log message every second on WorldTick
     * @param message log message
     * @param world world
     * @param isDebug if true, log as debug
     */
    public void log_onWorldTick(String message, World world, boolean isDebug) {
        log_onWorldTick(60, message, world, isDebug);
    }

    /**
     * Log message every second on SystemTick
     * @param message log message
     * @param isDebug if true, log as debug
     */
    public void log_onSystemTick(String message, boolean isDebug) {
        log_onSystemTick(60, message, isDebug);
    }

    /**
     * Log message every second on ClientTick
     * @param message log message
     */
    public void log_onClientTick(String message) {
        log_onClientTick(message, false);
    }

    /**
     * Log message every second on WorldTick
     * @param message log message
     * @param world world
     */
    public void log_onWorldTick(String message, World world) {
        log_onWorldTick(message, world, false);
    }

    /**
     * Log message every second on SystemTick
     * @param message log message
     */
    public void log_onSystemTick(String message) {
        log_onSystemTick(message, false);
    }
}
