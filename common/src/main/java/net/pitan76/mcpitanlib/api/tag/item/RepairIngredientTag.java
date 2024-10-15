package net.pitan76.mcpitanlib.api.tag.item;

import net.minecraft.item.Item;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.IngredientUtil;

public class RepairIngredientTag {

    public static final RepairIngredientTag REPAIRS_LEATHER_ARMOR = new RepairIngredientTag(ItemTags.REPAIRS_LEATHER_ARMOR);
    public static final RepairIngredientTag REPAIRS_CHAIN_ARMOR = new RepairIngredientTag(ItemTags.REPAIRS_CHAIN_ARMOR);
    public static final RepairIngredientTag REPAIRS_IRON_ARMOR = new RepairIngredientTag(ItemTags.REPAIRS_IRON_ARMOR);
    public static final RepairIngredientTag REPAIRS_GOLD_ARMOR = new RepairIngredientTag(ItemTags.REPAIRS_GOLD_ARMOR);
    public static final RepairIngredientTag REPAIRS_DIAMOND_ARMOR = new RepairIngredientTag(ItemTags.REPAIRS_DIAMOND_ARMOR);
    public static final RepairIngredientTag REPAIRS_NETHERITE_ARMOR = new RepairIngredientTag(ItemTags.REPAIRS_NETHERITE_ARMOR);
    public static final RepairIngredientTag REPAIRS_TURTLE_HELMET = new RepairIngredientTag(ItemTags.REPAIRS_TURTLE_HELMET);
    public static final RepairIngredientTag REPAIRS_WOLF_ARMOR = new RepairIngredientTag(ItemTags.REPAIRS_WOLF_ARMOR);
    public static final RepairIngredientTag WOODEN_TOOL_MATERIALS = new RepairIngredientTag(ItemTags.WOODEN_TOOL_MATERIALS);
    public static final RepairIngredientTag STONE_TOOL_MATERIALS = new RepairIngredientTag(ItemTags.STONE_TOOL_MATERIALS);
    public static final RepairIngredientTag IRON_TOOL_MATERIALS = new RepairIngredientTag(ItemTags.IRON_TOOL_MATERIALS);
    public static final RepairIngredientTag GOLDEN_TOOL_MATERIALS = new RepairIngredientTag(ItemTags.GOLD_TOOL_MATERIALS);
    public static final RepairIngredientTag DIAMOND_TOOL_MATERIALS = new RepairIngredientTag(ItemTags.DIAMOND_TOOL_MATERIALS);
    public static final RepairIngredientTag NETHERITE_TOOL_MATERIALS = new RepairIngredientTag(ItemTags.NETHERITE_TOOL_MATERIALS);

    private TagKey<Item> tag;

    public RepairIngredientTag(CompatIdentifier identifier) {
        this.tag = TagKey.of(RegistryKeys.ITEM, identifier.toMinecraft());
    }

    @Deprecated
    public RepairIngredientTag(TagKey<Item> tag) {
        this.tag = tag;
    }

    @Deprecated
    public TagKey<Item> getTag() {
        return tag;
    }

    @Deprecated
    public Ingredient getIngredient() {
        return IngredientUtil.fromTagByIdentifier(tag.id());
    }
}
