package net.pitan76.mcpitanlib.api.item.v3;

import net.minecraft.recipe.Ingredient;
import net.pitan76.mcpitanlib.api.item.CompatibleArmorMaterial;
import net.pitan76.mcpitanlib.api.tag.item.RepairIngredientTag;

public interface CompatArmorMaterial extends CompatibleArmorMaterial {

    @Deprecated
    @Override
    default Ingredient getRepairIngredient() {
        return getRepairIngredientTag().getIngredient();
    }

    RepairIngredientTag getRepairIngredientTag();
}
