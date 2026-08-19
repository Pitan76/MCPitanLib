package net.pitan76.mcpitanlib.api.gui.neoforge;

import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.pitan76.mcpitanlib.api.gui.ExtendedScreenHandlerTypeBuilder;

public class ExtendedScreenHandlerTypeBuilderImpl {
    public static <T extends ScreenHandler> ScreenHandlerType<T> build(ExtendedScreenHandlerTypeBuilder.Factory<T> factory) {
        return IMenuTypeExtension.create(factory::create);
    }
}
