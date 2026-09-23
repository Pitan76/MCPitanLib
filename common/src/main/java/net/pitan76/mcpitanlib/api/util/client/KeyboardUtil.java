package net.pitan76.mcpitanlib.api.util.client;

import net.minecraft.client.util.InputUtil;
import net.pitan76.mcpitanlib.api.client.option.KeyCodes;

public class KeyboardUtil {

    /**
     * キーが押されているかどうか
     * @param keyCode {@link net.pitan76.mcpitanlib.api.client.option.KeyCodes} のキーコード
     * @return 押されていれば true
     */
    public static boolean isKeyDown(int keyCode) {
        return InputUtil.isKeyPressed(WindowUtil.getWindow(), keyCode);
    }

    public static boolean hasShiftDown() {
        return isKeyDown(KeyCodes.KEY_LEFT_SHIFT) || isKeyDown(KeyCodes.KEY_RIGHT_SHIFT);
    }

    public static boolean hasControlDown() {
        return isKeyDown(KeyCodes.KEY_LEFT_CONTROL) || isKeyDown(KeyCodes.KEY_RIGHT_CONTROL);
    }

    public static boolean hasAltDown() {
        return isKeyDown(KeyCodes.KEY_LEFT_ALT) || isKeyDown(KeyCodes.KEY_RIGHT_ALT);
    }
}
