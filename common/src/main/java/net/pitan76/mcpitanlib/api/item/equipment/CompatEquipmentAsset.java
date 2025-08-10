package net.pitan76.mcpitanlib.api.item.equipment;

import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.registry.RegistryKey;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class CompatEquipmentAsset {
    private final RegistryKey<net.minecraft.item.equipment.EquipmentAsset> raw;

    @Deprecated
    public CompatEquipmentAsset(RegistryKey<net.minecraft.item.equipment.EquipmentAsset> key) {
        this.raw = key;
    }

    @Deprecated
    public RegistryKey<net.minecraft.item.equipment.EquipmentAsset> raw() {
        return raw;
    }

    public CompatIdentifier getId() {
        return CompatIdentifier.fromMinecraft(raw.getValue());
    }

    @Deprecated
    public static CompatEquipmentAsset of(RegistryKey<net.minecraft.item.equipment.EquipmentAsset> key) {
        return new CompatEquipmentAsset(key);
    }

    public static CompatEquipmentAsset of(CompatIdentifier id) {
        return of(RegistryKey.of(EquipmentAssetKeys.REGISTRY_KEY, id.toMinecraft()));
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
