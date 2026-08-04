package net.pitan76.mcpitanlib.core.mc261;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.server.level.ServerPlayer;

public class MenuOpener {
    /**
     * Opens an extended menu for the player.
     * <p>
     * Bridges {@link ExtendedMenuProvider} to whatever the platform's own extended-menu
     * mechanism requires, so callers do not have to care about the loader.
     */
    @ExpectPlatform
    public static void openExtendedMenu(ServerPlayer player, ExtendedMenuProvider<?> provider) {
    }
}
