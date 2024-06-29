package net.pitan76.mcpitanlib.api.util;

import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class IngredientUtil {
    public static Ingredient fromTagByIdentifier(Identifier id) {
        return Ingredient.fromTag(TagKey.of(Registries.ITEM.getKey(), id));
    }

    public static Ingredient fromTagByString(String id) {
        return fromTagByIdentifier(IdentifierUtil.id(id));
    }
}
