/*
Copyright (C) 2022-2024 Pitan

MCPitanLib is under the MIT license. For more information, please refer to the LICENSE file at the root of the repository.
MCPitanLibはMITライセンスです。ライセンスについてはリポジトリ直下のLICENSEファイルを参照してください。
 */

package net.pitan76.mcpitanlib;

import net.minecraft.util.Identifier;
import net.pitan76.easyapi.config.Config;
import net.pitan76.easyapi.config.JsonConfig;
import net.pitan76.mcpitanlib.api.registry.CompatRegistry;
import net.pitan76.mcpitanlib.api.util.PlatformUtil;
import net.pitan76.mcpitanlib.debug.DebugTool;
import net.pitan76.mcpitanlib.guilib.MPLGuiLib;
import net.pitan76.mcpitanlib.test.ExampleMod;
import net.pitan76.mcpitanlib.test.ExampleModClient;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MCPitanLib {
    public static final String MOD_ID = "mcpitanlib";

    private static final File configFile = new File(PlatformUtil.getConfigFolder().toFile(), "mcpitanlib/blacklist.json");
    private static final File oldConfigFile = new File(PlatformUtil.getConfigFolder().toFile(), "mcpitanlibarch/blacklist.json");

    public static Config config = new JsonConfig();
    private static boolean configLoaded = false;

    public static List<String> itemBlackList = new ArrayList<>();
    public static List<String> blockBlackList = new ArrayList<>();

    // MCPitanLibのアイテム、ブロック、タイルエンティティの登録
    public static CompatRegistry registry = CompatRegistry.createRegistry(MOD_ID);

    /**
     * MCPitanLibの初期化
     */
    public static void init() {
        configInit();
        new MPLGuiLib().init();

        if (PlatformUtil.isDevelopmentEnvironment() || (config.has("debugMode") && config.getBoolean("debugMode"))) {
            System.out.println("MCPitanLib: Debug Mode");
            DebugTool.register();
            registry.allRegister();

            new ExampleMod();
            if (PlatformUtil.isClient())
                ExampleModClient.init();
        }
    }

    /**
     * Configの初期化
     */
    public static void configInit() {
        if (oldConfigFile.exists() && !configFile.exists()) {
            oldConfigFile.renameTo(configFile);
        }

        try {
            if (configLoaded) return;
            configLoaded = true;
            if (!configFile.getParentFile().exists())
                if (!configFile.getParentFile().mkdirs())
                    return;

            config.setString("item", "examplemod:hogehoge_item,examplemod:fuga_item");
            config.setString("block", "examplemod:hogehoge_block,examplemod:fuga_block");
            config.setBoolean("debugMode", false);

            if (configFile.exists())
                config.load(configFile);

            if (!config.has("debugMode"))
                config.setBoolean("debugMode", false);

            if (config.has("item"))
                itemBlackList.addAll(Arrays.asList(config.getString("item").split(",")));

            if (config.has("block"))
                blockBlackList.addAll(Arrays.asList(config.getString("block").split(",")));

            config.save(configFile);
        } catch (Exception e) {
            System.out.println("MCPitanLib: Cannot save config file");
        }
    }

    /**
     * Identifierを生成
     * @param path パス
     * @return Identifier
     */
    public static Identifier id(String path) {
        return new Identifier(MOD_ID, path);
    }

    public static boolean isItemBlackListed(Identifier id) {
        try {
            return itemBlackList.contains(id.toString());
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isBlockBlackListed(Identifier id) {
        try {
            return blockBlackList.contains(id.toString());
        } catch (Exception e) {
            return false;
        }
    }
}