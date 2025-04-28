package net.pitan76.mcpitanlib.api.item.v2;

import net.pitan76.mcpitanlib.api.item.CompatFoodComponent;
import net.pitan76.mcpitanlib.api.item.CreativeTabBuilder;
import net.pitan76.mcpitanlib.api.item.ExtendSettings;
import net.pitan76.mcpitanlib.api.tag.item.RepairIngredientTag;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.CompatRarity;
import net.pitan76.mcpitanlib.midohra.item.ItemGroupWrapper;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;

public class ItemSettingsBuilder {

    public CompatIdentifier id;
    public int maxCount = -1;
    public int maxDamage = -1;
    public int maxDamageIfAbsent = -1;
    public int enchantability = -1;
    public RepairIngredientTag repairIngredientTag;
    public CompatFoodComponent foodComponent;
    public CompatRarity rarity;
    public ItemWrapper recipeRemainder;

    public ItemGroupWrapper itemGroupWrapper;
    public CreativeTabBuilder itemGroupBuilder;

    public ItemSettingsBuilder(CompatIdentifier id) {
        this.id = id;
    }

    public ItemSettingsBuilder() {

    }

    public ItemSettingsBuilder addGroup(ItemGroupWrapper itemGroup) {
        itemGroupWrapper = itemGroup;
        return this;
    }

    public ItemSettingsBuilder addGroup(CreativeTabBuilder itemGroup) {
        itemGroupBuilder = itemGroup;
        return this;
    }

    public ItemSettingsBuilder maxCount(int maxCount) {
        this.maxCount = maxCount;
        return this;
    }

    public ItemSettingsBuilder maxDamage(int maxDamage) {
        this.maxDamage = maxDamage;
        return this;
    }

    public ItemSettingsBuilder maxDamageIfAbsent(int maxDamage) {
        this.maxDamageIfAbsent = maxDamage;
        return this;
    }

    public ItemSettingsBuilder recipeRemainder(ItemWrapper recipeRemainder) {
        this.recipeRemainder = recipeRemainder;
        return this;
    }

    public ItemSettingsBuilder enchantability(int enchantability) {
        this.enchantability = enchantability;
        return this;
    }

    public ItemSettingsBuilder repairable(RepairIngredientTag repairIngredientTag) {
        this.repairIngredientTag = repairIngredientTag;
        return this;
    }

    public ItemSettingsBuilder repairable(CompatIdentifier repairIngredientTag) {
        this.repairIngredientTag = new RepairIngredientTag(repairIngredientTag);
        return this;
    }

    public ItemSettingsBuilder foodComponent(CompatFoodComponent foodComponent) {
        this.foodComponent = foodComponent;
        return this;
    }

    public ItemSettingsBuilder rarity(CompatRarity rarity) {
        this.rarity = rarity;
        return this;
    }

    public CompatibleItemSettings build() {
        return build(id);
    }

    public ExtendSettings _build() {
        return build().build();
    }

    public CompatibleItemSettings build(CompatIdentifier id) {
        CompatibleItemSettings settings = new CompatibleItemSettings(id);

        if (itemGroupWrapper != null) settings.addGroup(itemGroupWrapper);
        if (itemGroupBuilder != null) settings.addGroup(itemGroupBuilder);

        if (maxCount != -1) settings.maxCount(maxCount);

        if (maxDamage != -1) settings.maxDamage(maxDamage);

        if (maxDamageIfAbsent != -1) settings.maxDamageIfAbsent(maxDamageIfAbsent);

        if (rarity != null) settings.rarity(rarity);
        if (foodComponent != null) settings.food(foodComponent);

        if (recipeRemainder != null) settings.recipeRemainder(recipeRemainder);

        if (enchantability != -1) settings.enchantable(enchantability);

        if (repairIngredientTag != null) settings.repairable(repairIngredientTag);

        return settings;
    }

    public ExtendSettings _build(CompatIdentifier id) {
        return build(id).build();
    }

    public ItemSettingsBuilder copy(CompatIdentifier id) {
        ItemSettingsBuilder builder = new ItemSettingsBuilder();
        builder.id = id;
        builder.maxCount = this.maxCount;
        builder.maxDamage = this.maxDamage;
        builder.maxDamageIfAbsent = this.maxDamageIfAbsent;
        builder.enchantability = this.enchantability;
        builder.repairIngredientTag = this.repairIngredientTag;
        builder.foodComponent = this.foodComponent;
        builder.rarity = this.rarity;
        builder.recipeRemainder = this.recipeRemainder;
        builder.itemGroupWrapper = this.itemGroupWrapper;
        builder.itemGroupBuilder = this.itemGroupBuilder;
        return builder;
    }

    public ItemSettingsBuilder copy() {
        return copy(this.id);
    }

    public static ItemSettingsBuilder of(CompatIdentifier id) {
        return new ItemSettingsBuilder(id);
    }

    public static ItemSettingsBuilder of() {
        return new ItemSettingsBuilder();
    }
}
