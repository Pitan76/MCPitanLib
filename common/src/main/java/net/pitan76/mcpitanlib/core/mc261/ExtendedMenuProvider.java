package net.pitan76.mcpitanlib.core.mc261;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;

public interface ExtendedMenuProvider<D> extends MenuProvider {
    D getScreenOpeningData(ServerPlayer player);
}
