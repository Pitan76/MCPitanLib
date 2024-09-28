package net.pitan76.mcpitanlib.midohra.nbt;

public interface ElementConvertible {
    NbtElement toElement();

    default net.minecraft.nbt.NbtElement toMinecraftNbtElement() {
        return toElement().toMinecraft();
    }
}
