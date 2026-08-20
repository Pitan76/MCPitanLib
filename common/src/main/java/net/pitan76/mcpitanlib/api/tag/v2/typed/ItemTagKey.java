package net.pitan76.mcpitanlib.api.tag.v2.typed;

import net.pitan76.mcpitanlib.core.tag.TagHooks;
import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.Tag;
import net.pitan76.mcpitanlib.api.tag.v2.CompatTagKey;
import net.pitan76.mcpitanlib.api.tag.v2.CompatTagKeyType;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.IngredientUtil;

import java.util.List;

public class ItemTagKey extends CompatTagKey<Item> {
    @Deprecated
    public ItemTagKey(Tag.Identified<Item> tagKey) {
        super(tagKey);
    }

    public static ItemTagKey of(CompatIdentifier identifier) {
        return new ItemTagKey(TagHooks.getOptional(identifier.toMinecraft(), CompatTagKeyType.ITEM::getTagGroup));
    }

    public Ingredient asIngredient() {
        return IngredientUtil.fromTagByIdentifier(getTagKey().getId());
    }

    public List<Item> values() {
        return getTagKey().values();
    }
}
