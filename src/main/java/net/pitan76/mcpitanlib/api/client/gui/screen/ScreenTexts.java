package net.pitan76.mcpitanlib.api.client.gui.screen;

import net.minecraft.network.chat.Component;
import net.pitan76.mcpitanlib.api.util.TextUtil;

public class ScreenTexts {
    public static final Component ON = TextUtil.translatable("options.on");
    public static final Component OFF = TextUtil.translatable("options.off");
    public static final Component DONE = TextUtil.translatable("gui.done");
    public static final Component CANCEL = TextUtil.translatable("gui.cancel");
    public static final Component YES = TextUtil.translatable("gui.yes");
    public static final Component NO = TextUtil.translatable("gui.no");
    public static final Component PROCEED = TextUtil.translatable("gui.proceed");
    public static final Component BACK = TextUtil.translatable("gui.back");
    public static final Component CONNECT_FAILED = TextUtil.translatable("connect.failed");
    public static final Component LINE_BREAK = TextUtil.literal("\n");
    public static final Component SENTENCE_SEPARATOR = TextUtil.literal(". ");

    public ScreenTexts() {
    }
}
