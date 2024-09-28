package net.pitan76.mcpitanlib.api.gui.inventory.sided;

import net.minecraft.util.math.Direction;
import net.pitan76.mcpitanlib.api.gui.inventory.sided.args.CanExtractArgs;
import net.pitan76.mcpitanlib.api.gui.inventory.sided.args.CanInsertArgs;

public interface VanillaStyleSidedInventory extends CompatSidedInventory {
    @Override
    default boolean canInsert(CanInsertArgs args) {
        if (args.getDir() == null)
            return false;

        return args.getDir() != Direction.DOWN;
    }

    @Override
    default boolean canExtract(CanExtractArgs args) {
        return args.getDir() == Direction.DOWN;
    }
}
