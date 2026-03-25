package net.pitan76.mcpitanlib.api.util;

import com.mojang.brigadier.arguments.*;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.world.level.block.Block;
import net.minecraft.commands.arguments.blocks.BlockStateArgument;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.commands.arguments.item.ItemArgument;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.pitan76.mcpitanlib.api.event.ServerCommandEvent;

public class CommandUtil {
    public static Item getItemArgument(String name, ServerCommandEvent e) {
        return ItemArgument.getItem(e.getContext(), name).getItem();
    }

    public static Block getBlockArgument(String name, ServerCommandEvent e) {
        return BlockStateArgument.getBlock(e.getContext(), name).getState().getBlock();
    }

    public static Integer getIntegerArgument(String name, ServerCommandEvent e) {
        return IntegerArgumentType.getInteger(e.getContext(), name);
    }

    public static Double getDoubleArgument(String name, ServerCommandEvent e) {
        return DoubleArgumentType.getDouble(e.getContext(), name);
    }

    public static Float getFloatArgument(String name, ServerCommandEvent e) {
        return FloatArgumentType.getFloat(e.getContext(), name);
    }

    public static Long getLongArgument(String name, ServerCommandEvent e) {
        return LongArgumentType.getLong(e.getContext(), name);
    }

    public static Boolean getBooleanArgument(String name, ServerCommandEvent e) {
        return BoolArgumentType.getBool(e.getContext(), name);
    }

    public static String getStringArgument(String name, ServerCommandEvent e) {
        return StringArgumentType.getString(e.getContext(), name);
    }

    public static Entity getEntityArgument(String name, ServerCommandEvent e) {
        try {
            return EntityArgument.getEntity(e.getContext(), name);
        } catch (CommandSyntaxException ex) {
            return null;
        }
    }
}
