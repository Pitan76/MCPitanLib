package net.pitan76.mcpitanlib.api.gui.fabric;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.pitan76.mcpitanlib.api.gui.ExtendedScreenHandlerTypeBuilder;

public class ExtendedScreenHandlerTypeBuilderImpl {
    public static <T extends AbstractContainerMenu> MenuType<T> build(ExtendedScreenHandlerTypeBuilder.Factory<T> factory) {
        return new ExtendedMenuType<>(factory::create, ExtendedScreenHandlerTypeBuilder.CODEC);
    }
}
