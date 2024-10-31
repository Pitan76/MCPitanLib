package net.pitan76.mcpitanlib.api.item.v3;

import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.TagKey;
import net.pitan76.mcpitanlib.api.item.CompatibleArmorMaterial;
import net.pitan76.mcpitanlib.api.tag.item.RepairIngredientTag;

public interface CompatToolMaterial extends CompatibleArmorMaterial {
    @Deprecated
    @Override
    default Ingredient getRepairIngredient() {
        return getRepairIngredientTag().getIngredient();
    }

    RepairIngredientTag getRepairIngredientTag();
}