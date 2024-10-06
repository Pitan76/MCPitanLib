package net.pitan76.mcpitanlib.guilib.api;

public interface ISimpleScreenInfo extends IScreenInfo {

    @Override
    default int getScreenWidth() {
        return 176;
    }

    @Override
    default int getScreenHeight() {
        return 166;
    }
}
