package net.pitan76.mcpitanlib.core.screen;

import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.server.network.ServerPlayerEntity;

public interface ExtendedMenuProvider<D> extends NamedScreenHandlerFactory {
    D getScreenOpeningData(ServerPlayerEntity player);
}
