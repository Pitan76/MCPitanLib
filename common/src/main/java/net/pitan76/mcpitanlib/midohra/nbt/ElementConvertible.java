package net.pitan76.mcpitanlib.midohra.nbt;

public interface ElementConvertible {
    NbtElement toElement();

    default net.minecraft.nbt.Tag toMinecraftNbtElement() {
        return toElement().toMinecraft();
    }
}
