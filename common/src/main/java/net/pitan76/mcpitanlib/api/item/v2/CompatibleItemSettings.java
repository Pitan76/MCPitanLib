package net.pitan76.mcpitanlib.api.item.v2;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.pitan76.mcpitanlib.api.item.CompatFoodComponent;
import net.pitan76.mcpitanlib.api.item.CreativeTabBuilder;
import net.pitan76.mcpitanlib.api.item.ExtendSettings;
import net.pitan76.mcpitanlib.api.tag.item.RepairIngredientTag;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.item.ItemGroupWrapper;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;

import java.util.function.Supplier;

@SuppressWarnings("deprecation")
public class CompatibleItemSettings extends net.pitan76.mcpitanlib.api.item.CompatibleItemSettings {
    protected CompatIdentifier identifier = null;
    public boolean changedTranslationKey = false;

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
        return this;
    }

    public CompatibleItemSettings recipeRemainder(ItemWrapper recipeRemainder) {
        return recipeRemainder(recipeRemainder.get());
    }

    @Override
    public CompatibleItemSettings rarity(Rarity rarity) {
        super.rarity(rarity);
        return this;
    }

    @Override
    public CompatibleItemSettings food(CompatFoodComponent foodComponent) {
        super.food(foodComponent);
        return this;
    }

    public CompatibleItemSettings useItemPrefixedTranslationKey() {
        settings.useItemPrefixedTranslationKey();
        changedTranslationKey = true;
        return this;
    }

    public CompatibleItemSettings useBlockPrefixedTranslationKey() {
        settings.useBlockPrefixedTranslationKey();
        changedTranslationKey = true;
        return this;
    }

    public CompatibleItemSettings translationKey(String translationKey) {
        settings.translationKey(translationKey);
        changedTranslationKey = true;
        return this;
    }

    public CompatibleItemSettings enchantable(int enchantability) {
        settings.enchantable(enchantability);
        return this;
    }

    public CompatibleItemSettings repairable(RepairIngredientTag tag) {
        settings.repairable(tag.getTag());
        return this;
    }

    @Override
    public ExtendSettings build() {
        super.build();

        if (identifier != null) {
            settings.registryKey(RegistryKey.of(RegistryKeys.ITEM, identifier.toMinecraft()));
        }

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
