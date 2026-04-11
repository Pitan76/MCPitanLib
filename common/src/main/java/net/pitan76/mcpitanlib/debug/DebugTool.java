package net.pitan76.mcpitanlib.debug;

import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.v2.CompatItem;

import static net.pitan76.mcpitanlib.MCPitanLib.*;

/**
 * A tool for debugging.
 * Example of condition: item instance DebugTool
 */
public class DebugTool extends CompatItem {

    public static CompatibleItemSettings defaultSettings = CompatibleItemSettings.of(compatId("debug_tool"));

    public DebugTool() {
        super(defaultSettings);
    }

    public static void debug(String message) {
        System.out.println(message);
    }

    public static void register() {
        registry.registerItem(id("debug_tool"), DebugTool::new);
    }
}
