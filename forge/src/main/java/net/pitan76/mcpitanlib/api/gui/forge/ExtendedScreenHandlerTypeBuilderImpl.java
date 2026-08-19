package net.pitan76.mcpitanlib.api.gui.forge;

import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.pitan76.mcpitanlib.api.gui.ExtendedScreenHandlerTypeBuilder;

public class ExtendedScreenHandlerTypeBuilderImpl {
    public static <T extends ScreenHandler> ScreenHandlerType<T> build(ExtendedScreenHandlerTypeBuilder<T> builder) {
        return IForgeMenuType.create(builder.factory::create);
    }
}

