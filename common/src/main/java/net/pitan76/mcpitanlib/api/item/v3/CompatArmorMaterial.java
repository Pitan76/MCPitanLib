package net.pitan76.mcpitanlib.api.item.v3;

import net.minecraft.recipe.Ingredient;
import net.pitan76.mcpitanlib.api.tag.item.RepairIngredientTag;

public interface CompatArmorMaterial extends net.pitan76.mcpitanlib.api.item.v2.CompatArmorMaterial {

    @Override
    default Ingredient getRepairIngredient() {
        return getRepairIngredientTag().getIngredient();
    }

    RepairIngredientTag getRepairIngredientTag();
}
