package net.pitan76.mcpitanlib.api.item.equipment;

import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class CompatEquipmentAsset {

    @Deprecated
    public CompatEquipmentAsset() {

    }

    public CompatIdentifier getId() {
        return CompatIdentifier.empty();
    }

    @Deprecated
    public static CompatEquipmentAsset of() {
        return new CompatEquipmentAsset();
    }

    public static CompatEquipmentAsset of(CompatIdentifier id) {
        return of();
    }

    public static CompatEquipmentAsset of(String id) {
        return of(CompatIdentifier.of(id));
    }
}
