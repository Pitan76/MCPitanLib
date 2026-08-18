package net.pitan76.mcpitanlib.core.screen;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.server.network.ServerPlayerEntity;

public class MenuOpener {
    /**
     * 拡張メニューを開く。
     * <p>
     * {@link ExtendedMenuProvider} を各プラットフォームの拡張メニュー機構へ橋渡しするため、
     * 呼び出し側はローダーの違いを意識しなくてよい。
     */
    @ExpectPlatform
    public static void openExtendedMenu(ServerPlayerEntity player, ExtendedMenuProvider<?> provider) {

    }
}
