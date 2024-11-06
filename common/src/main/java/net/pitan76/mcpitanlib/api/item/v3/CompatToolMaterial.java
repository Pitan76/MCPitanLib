package net.pitan76.mcpitanlib.api.item.v3;

import net.minecraft.recipe.Ingredient;
import net.pitan76.mcpitanlib.api.item.tool.CompatibleToolMaterial;
import net.pitan76.mcpitanlib.api.tag.item.RepairIngredientTag;

public interface CompatToolMaterial extends CompatibleToolMaterial {
    @Deprecated
    @Override
    default Ingredient getRepairIngredient() {
        return getRepairIngredientTag().getIngredient();
    }

    RepairIngredientTag getRepairIngredientTag();
}