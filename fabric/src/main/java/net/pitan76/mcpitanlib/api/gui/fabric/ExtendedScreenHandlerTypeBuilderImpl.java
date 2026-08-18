package net.pitan76.mcpitanlib.api.gui.fabric;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.mcpitanlib.api.gui.ExtendedScreenHandlerTypeBuilder;

public class ExtendedScreenHandlerTypeBuilderImpl {
    public static <T extends ScreenHandler> ScreenHandlerType<T> build(ExtendedScreenHandlerTypeBuilder<T> builder) {
        return new ExtendedScreenHandlerType<>((syncId, inventory, buf) -> builder.factory.create(syncId, inventory, buf));
    }
}
