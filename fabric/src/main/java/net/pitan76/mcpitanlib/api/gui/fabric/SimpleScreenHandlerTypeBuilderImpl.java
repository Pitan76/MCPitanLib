package net.pitan76.mcpitanlib.api.gui.fabric;

import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.pitan76.mcpitanlib.api.gui.SimpleScreenHandlerTypeBuilder;

public class SimpleScreenHandlerTypeBuilderImpl {
    public static <T extends AbstractContainerMenu> MenuType<T> build(SimpleScreenHandlerTypeBuilder.Factory<T> factory) {
        return new MenuType<>(factory::create, FeatureFlags.VANILLA_SET);
    }
}
