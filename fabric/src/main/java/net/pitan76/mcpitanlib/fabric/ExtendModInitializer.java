package net.pitan76.mcpitanlib.fabric;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.registry.v2.CompatRegistryV2;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.IdentifierUtil;
import net.pitan76.mcpitanlib.api.util.Logger;

public abstract class ExtendModInitializer implements ModInitializer {

    public String MOD_ID;
    public String MOD_NAME;

    public CompatRegistryV2 registry;
    public Logger logger;

    @Override
    public void onInitialize() {
        this.MOD_ID = getId();
        this.MOD_NAME = getName();

        this.registry = CompatRegistryV2.create(MOD_ID);
        this.logger = new Logger(MOD_ID);

        logger.initializeMessage();
        init();
        registry.allRegister();
    }

    public abstract void init();

    public abstract String getId();

    public String getName() {
        return getId();
    }


    // Utilities
    public Identifier id(String path) {
        return IdentifierUtil.id(MOD_ID, path);
    }

    public CompatIdentifier compatId(String path) {
        return CompatIdentifier.of(MOD_ID, path);
    }

    public void info(String message) {
        logger.info(message);
    }

    public void warn(String message) {
        logger.warn(message);
    }

    public void error(String message) {
        logger.error(message);
    }

    public void debug(String message) {
        logger.debug(message);
    }

    public void trace(String message) {
        logger.trace(message);
    }
}
