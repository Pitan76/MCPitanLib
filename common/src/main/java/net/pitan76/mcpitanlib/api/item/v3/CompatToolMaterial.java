package net.pitan76.mcpitanlib.api.item.v3;

import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import net.pitan76.mcpitanlib.api.item.tool.CompatibleToolMaterial;
import net.pitan76.mcpitanlib.api.tag.item.RepairIngredientTag;

public interface CompatToolMaterial extends CompatibleToolMaterial {
    @Deprecated
    @Override
    default TagKey<Item> getRepairTag() {
        return getRepairIngredientTag().getTag();
    }

    RepairIngredientTag getRepairIngredientTag();
}