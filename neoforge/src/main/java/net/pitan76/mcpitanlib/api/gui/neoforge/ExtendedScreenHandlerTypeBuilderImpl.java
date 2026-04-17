package net.pitan76.mcpitanlib.api.gui.neoforge;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.pitan76.mcpitanlib.api.gui.ExtendedScreenHandlerTypeBuilder;

public class ExtendedScreenHandlerTypeBuilderImpl {
    public static <T extends AbstractContainerMenu> MenuType<T> build(ExtendedScreenHandlerTypeBuilder.Factory<T> factory) {
        return IMenuTypeExtension.create(factory::create);
    }
}
