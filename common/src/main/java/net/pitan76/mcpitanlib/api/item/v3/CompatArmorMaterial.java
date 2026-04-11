package net.pitan76.mcpitanlib.api.item.v3;

import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.pitan76.mcpitanlib.api.tag.item.RepairIngredientTag;

public interface CompatArmorMaterial extends net.pitan76.mcpitanlib.api.item.v2.CompatArmorMaterial {

    @Deprecated
    @Override
    default TagKey<Item> getRepairTag() {
        return getRepairIngredientTag().getTag();
    }

    RepairIngredientTag getRepairIngredientTag();
}
