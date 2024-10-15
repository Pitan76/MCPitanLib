package net.pitan76.mcpitanlib.api.tag.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.IngredientUtil;
import net.pitan76.mcpitanlib.api.util.item.ItemUtil;

public class RepairIngredientTag {

    public static final RepairIngredientTag REPAIRS_LEATHER_ARMOR = of("leather_armor_materials");
    public static final RepairIngredientTag REPAIRS_CHAIN_ARMOR = of("iron_tool_materials");
    public static final RepairIngredientTag REPAIRS_IRON_ARMOR = of("iron_tool_materials");
    public static final RepairIngredientTag REPAIRS_GOLD_ARMOR = of("golden_tool_materials");
    public static final RepairIngredientTag REPAIRS_DIAMOND_ARMOR = of("diamond_tool_materials");
    public static final RepairIngredientTag REPAIRS_NETHERITE_ARMOR = of("netherite_tool_materials");
    public static final RepairIngredientTag REPAIRS_TURTLE_HELMET = of("turtle_helmet_materials");
    public static final RepairIngredientTag REPAIRS_WOLF_ARMOR = of("leather_armor_materials");
    public static final RepairIngredientTag WOODEN_TOOL_MATERIALS = of("wooden_tool_materials");
    public static final RepairIngredientTag STONE_TOOL_MATERIALS = of("stone_tool_materials");
    public static final RepairIngredientTag IRON_TOOL_MATERIALS = of("iron_tool_materials");
    public static final RepairIngredientTag GOLDEN_TOOL_MATERIALS = of("golden_tool_materials");
    public static final RepairIngredientTag DIAMOND_TOOL_MATERIALS = of("diamond_tool_materials");
    public static final RepairIngredientTag NETHERITE_TOOL_MATERIALS = of("netherite_tool_materials");

    private TagKey<Item> tag;

    protected static RepairIngredientTag of(String path) {
        return new RepairIngredientTag(CompatIdentifier.of(MCPitanLib.MOD_ID, path));
    }

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

    public boolean contains(Item item) {
        if (item == null || tag == null)
            return false;

        return ItemUtil.isInTag(item, CompatIdentifier.fromMinecraft(tag.id()));
    }

    public boolean contains(ItemStack stack) {
        if (stack.isEmpty() || tag == null)
            return false;

        return getIngredient().test(stack);
    }
}
