package net.pitan76.mcpitanlib.api.util.debug;

import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.util.ItemUtil;
import net.pitan76.mcpitanlib.api.util.PlatformUtil;

public class OutputUtil {

    public static boolean dev = PlatformUtil.isDevelopmentEnvironment();

    public static void print(Item item) {
        if (!dev) return;
        System.out.println(getString(item));
    }

    public static void print(ItemStack stack) {
        if (!dev) return;
        System.out.println(getString(stack));
    }

    public static void print(Inventory inventory) {
        if (!dev) return;
        System.out.println(getString(inventory));
    }

    public static String getString(Item item) {
        StringBuilder sb = new StringBuilder();
        sb.append("Item: {").append("\n");

        sb.append("  Name: ").append(item.getTranslationKey()).append("\n");
        sb.append("  Id: ").append(ItemUtil.toCompatID(item)).append("\n");
        sb.append("}\n");

        return sb.toString();
    }

    public static String getString(ItemStack stack) {
        Item item = stack.getItem();

        StringBuilder sb = new StringBuilder();
        sb.append("ItemStack: {").append("\n");

        sb.append(getString(item));
        sb.append("  Count: ").append(stack.getCount()).append("\n");
        sb.append("}\n");

        return sb.toString();
    }

    public static String getString(Inventory inventory) {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventory: {").append("\n");

        for (int i = 0; i < inventory.size(); i++) {
            ItemStack stack = inventory.getStack(i);
            if (stack.isEmpty()) continue;

            sb.append("  Slot ").append(i).append(": {").append("\n");
            sb.append(getString(stack));
            sb.append("  }\n");
        }

        sb.append("}\n");

        return sb.toString();
    }
}
