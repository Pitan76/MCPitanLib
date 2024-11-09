package net.pitan76.mcpitanlib.api.tag.v2.typed;

import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.tag.TagKey;
import net.pitan76.mcpitanlib.api.tag.v2.CompatTagKey;
import net.pitan76.mcpitanlib.api.tag.v2.CompatTagKeyType;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.IngredientUtil;
import net.pitan76.mcpitanlib.api.util.item.ItemUtil;

import java.util.List;

public class ItemTagKey extends CompatTagKey<Item> {
    @Deprecated
    public ItemTagKey(TagKey<Item> tagKey) {
        super(tagKey);
    }

    public static ItemTagKey of(CompatIdentifier identifier) {
        return new ItemTagKey(net.minecraft.tag.TagKey.of(CompatTagKeyType.ITEM.getRegistryKey(), identifier.toMinecraft()));
    }

    public Ingredient asIngredient() {
        return IngredientUtil.fromTagByIdentifier(getTagKey().id());
    }

    public List<Item> values() {
        return ItemUtil.getInTag(this);
    }
}
