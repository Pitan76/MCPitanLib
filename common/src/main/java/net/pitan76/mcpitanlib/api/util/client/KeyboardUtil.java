package net.pitan76.mcpitanlib.api.util.client;

import com.mojang.blaze3d.platform.InputConstants;

public class KeyboardUtil {

    /**
     * キーが押されているかどうか
     * @param keyCode {@link net.pitan76.mcpitanlib.api.client.option.KeyCodes} のキーコード
     * @return 押されていれば true
     */
    public static boolean isKeyDown(int keyCode) {
        return InputConstants.isKeyDown(WindowUtil.getWindow(), keyCode);
    }

    public static boolean hasShiftDown() {
        return ClientUtil.getClient().hasShiftDown();
    }

    public static boolean hasControlDown() {
        return ClientUtil.getClient().hasControlDown();
    }

    public static boolean hasAltDown() {
        return ClientUtil.getClient().hasAltDown();
    }
}
