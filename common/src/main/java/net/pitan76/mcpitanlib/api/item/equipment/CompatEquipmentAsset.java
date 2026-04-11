package net.pitan76.mcpitanlib.api.item.equipment;

import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.resources.ResourceKey;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class CompatEquipmentAsset {
    private final ResourceKey<net.minecraft.world.item.equipment.EquipmentAsset> raw;

    @Deprecated
    public CompatEquipmentAsset(ResourceKey<net.minecraft.world.item.equipment.EquipmentAsset> key) {
        this.raw = key;
    }

    @Deprecated
    public ResourceKey<net.minecraft.world.item.equipment.EquipmentAsset> raw() {
        return raw;
    }

    public CompatIdentifier getId() {
        return CompatIdentifier.fromMinecraft(raw.identifier());
    }

    @Deprecated
    public static CompatEquipmentAsset of(ResourceKey<net.minecraft.world.item.equipment.EquipmentAsset> key) {
        return new CompatEquipmentAsset(key);
    }

    public static CompatEquipmentAsset of(CompatIdentifier id) {
        return of(ResourceKey.create(EquipmentAssets.ROOT_ID, id.toMinecraft()));
    }

    public static CompatEquipmentAsset of(String id) {
        return of(CompatIdentifier.of(id));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof CompatEquipmentAsset)) return false;
        CompatEquipmentAsset that = (CompatEquipmentAsset) obj;
        return raw.equals(that.raw);
    }

    @Override
    public int hashCode() {
        if (raw == null) return super.hashCode();
        return raw.hashCode();
    }
}
