package net.pitan76.mcpitanlib.debug;

import net.pitan76.mcpitanlib.api.item.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.ExtendItem;

import static net.pitan76.mcpitanlib.MCPitanLib.*;

/**
 * A tool for debugging.
 * Example of condition: item instance DebugTool
 */
public class DebugTool extends ExtendItem {

    public static CompatibleItemSettings defaultSettings = CompatibleItemSettings.of();

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
