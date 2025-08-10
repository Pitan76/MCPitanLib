package net.pitan76.mcpitanlib.api.item.v2;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.pitan76.mcpitanlib.api.entity.attribute.AttributeModifiersComponentBuilder;
import net.pitan76.mcpitanlib.api.entity.attribute.CompatAttributeModifiersComponent;
import net.pitan76.mcpitanlib.api.item.CompatFoodComponent;
import net.pitan76.mcpitanlib.api.item.CreativeTabBuilder;
import net.pitan76.mcpitanlib.api.item.ExtendSettings;
import net.pitan76.mcpitanlib.api.item.equipment.CompatEquippableComponent;
import net.pitan76.mcpitanlib.api.item.equipment.EquippableComponentBuilder;
import net.pitan76.mcpitanlib.api.tag.item.RepairIngredientTag;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.CompatRarity;
import net.pitan76.mcpitanlib.midohra.item.ItemGroupWrapper;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;

import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public class CompatibleItemSettings extends net.pitan76.mcpitanlib.api.item.CompatibleItemSettings {
    protected CompatIdentifier identifier = null;
    public boolean changedTranslationKey = false;
    public CompatRarity rarity = CompatRarity.COMMON;
    public int enchantability = -1;
    public RepairIngredientTag repairIngredientTag = null;
    public Item recipeRemainder = null;

    public CompatibleItemSettings(CompatIdentifier identifier) {
        super();
        setId(identifier);
    }

    public static CompatibleItemSettings of(CompatIdentifier id) {
        return new CompatibleItemSettings(id);
    }

    @Deprecated
    public CompatibleItemSettings setId(CompatIdentifier identifier) {
        this.identifier = identifier;
        return this;
    }

    public CompatibleItemSettings addGroup(ItemGroup itemGroup) {
        super.addGroup(itemGroup, identifier);
        return this;
    }

    public CompatibleItemSettings addGroup(Supplier<ItemGroup> itemGroup) {
        super.addGroup(itemGroup, identifier);
        return this;
    }

    @Override
    public CompatibleItemSettings addGroup(CreativeTabBuilder itemGroup) {
        super.addGroup(itemGroup);
        return this;
    }

    public CompatibleItemSettings addGroup(ItemGroupWrapper itemGroup) {
        return addGroup(itemGroup.get());
    }

    @Override
    public CompatibleItemSettings maxCount(int maxCount) {
        super.maxCount(maxCount);
        return this;
    }

    @Override
    public CompatibleItemSettings maxDamage(int maxDamage) {
        super.maxDamage(maxDamage);
        return this;
    }

    @Override
    public CompatibleItemSettings maxDamageIfAbsent(int maxDamage) {
        super.maxDamageIfAbsent(maxDamage);
        return this;
    }

    @Override
    public CompatibleItemSettings recipeRemainder(Item recipeRemainder) {
        super.recipeRemainder(recipeRemainder);
        this.recipeRemainder = recipeRemainder;
        return this;
    }

    public CompatibleItemSettings recipeRemainder(ItemWrapper recipeRemainder) {
        return recipeRemainder(recipeRemainder.get());
    }

    @Deprecated
    @Override
    public CompatibleItemSettings rarity(Rarity rarity) {
        super.rarity(rarity);
        this.rarity = CompatRarity.of(rarity);
        return this;
    }

    public CompatibleItemSettings rarity(CompatRarity rarity) {
        super.rarity(rarity.getRarity());
        this.rarity = rarity;
        return this;
    }

    @Override
    public CompatibleItemSettings food(CompatFoodComponent foodComponent) {
        super.food(foodComponent);
        return this;
    }

    public CompatibleItemSettings useItemPrefixedTranslationKey() {
        changedTranslationKey = true;
        return this;
    }

    public CompatibleItemSettings useBlockPrefixedTranslationKey() {
        changedTranslationKey = true;
        return this;
    }

    public String translationKey = null;

    public CompatibleItemSettings translationKey(String translationKey) {
        changedTranslationKey = true;
        this.translationKey = translationKey;
        return this;
    }

    public CompatibleItemSettings enchantable(int enchantability) {
        this.enchantability = enchantability;
        return this;
    }

    public CompatibleItemSettings repairable(RepairIngredientTag tag) {
        this.repairIngredientTag = tag;
        return this;
    }

    public CompatibleItemSettings equipable(CompatEquippableComponent component) {
        return this;
    }

    public CompatibleItemSettings equipable(EquippableComponentBuilder builder) {
        return equipable(builder.build());
    }

    public CompatibleItemSettings attributeModifiers(CompatAttributeModifiersComponent component) {
        return this;
    }

    public CompatibleItemSettings attributeModifiers(AttributeModifiersComponentBuilder builder) {
        return attributeModifiers(builder.build());
    }

    @Override
    public ExtendSettings build() {
        super.build();
        return settings;
    }

    // Deprecated

    @Deprecated
    @Override
    public net.pitan76.mcpitanlib.api.item.CompatibleItemSettings addGroup(ItemGroup itemGroup, Identifier identifier) {
        return super.addGroup(itemGroup, identifier);
    }

    @Deprecated
    @Override
    public net.pitan76.mcpitanlib.api.item.CompatibleItemSettings addGroup(Supplier<ItemGroup> itemGroup, Identifier identifier) {
        return super.addGroup(itemGroup, identifier);
    }

    @Deprecated
    @Override
    public net.pitan76.mcpitanlib.api.item.CompatibleItemSettings addGroup(Supplier<ItemGroup> itemGroup, CompatIdentifier identifier) {
        return super.addGroup(itemGroup, identifier);
    }

    @Deprecated
    @Override
    public net.pitan76.mcpitanlib.api.item.CompatibleItemSettings addGroup(ItemGroup itemGroup, CompatIdentifier identifier) {
        return super.addGroup(itemGroup, identifier);
    }
}
