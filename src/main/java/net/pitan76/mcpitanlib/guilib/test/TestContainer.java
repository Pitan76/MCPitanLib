package net.pitan76.mcpitanlib.guilib.test;

import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.mcpitanlib.guilib.api.container.ContainerGui;

public class TestContainer extends ContainerGui {
    public TestContainer(ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
    }

    public TestContainer(int i, PlayerInventory inventory) {
        this(null, i);
    }

    @Override
    public int getScreenBackgroundWidth() {
        return 176;
    }

    @Override
    public int getScreenBackgroundHeight() {
        return 166;
    }

    @Override
    public int getScreenWidth() {
        return 176;
    }

    @Override
    public int getScreenHeight() {
        return 166;
    }

    @Override
    public int getScreenX() {
        return 0;
    }

    @Override
    public int getScreenY() {
        return 0;
    }

    @Override
    public int getScreenTitleX() {
        return 0;
    }

    @Override
    public int getScreenTitleY() {
        return 0;
    }
}
