package net.pitan76.mcpitanlib.api.item;

import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.core.registry.MCPLRegistry1_20;

import java.util.function.Supplier;

@Deprecated
public class CompatibleItemSettings {
    protected final ExtendSettings settings = new ExtendSettings();

    protected Identifier itemGroupId = null;

    @Deprecated
    public static CompatibleItemSettings of() {
        return new CompatibleItemSettings();
    }

    // ～1.19.2
    public CompatibleItemSettings addGroup(ItemGroup itemGroup) {
        settings.addGroup(itemGroup);
        return this;
    }

    // 1.19.3～
    // identifier: Item ID
    public CompatibleItemSettings addGroup(ItemGroup itemGroup, Identifier identifier) {
        settings.addGroup(itemGroup, identifier);
        return this;
    }

    public CompatibleItemSettings addGroup(Supplier<ItemGroup> itemGroup, Identifier identifier) {
        settings.addGroup(itemGroup, identifier);
        return this;
    }

    public CompatibleItemSettings addGroup(CreativeTabBuilder itemGroup) {
        this.itemGroupId = itemGroup.getIdentifier();
        return this;
    }

    public CompatibleItemSettings maxCount(int maxCount) {
        settings.maxCount(maxCount);
        return this;
    }

    public CompatibleItemSettings maxDamage(int maxDamage) {
        settings.maxDamage(maxDamage);
        return this;
    }

    public CompatibleItemSettings maxDamageIfAbsent(int maxDamage) {
        try {
            settings.maxDamage(maxDamage);
        } catch (Exception ignored) {}

        return this;
    }

    @Deprecated
    public CompatibleItemSettings food(FoodComponent foodComponent) {
        settings.food(foodComponent);
        return this;
    }

    public CompatibleItemSettings rarity(Rarity rarity) {
        settings.rarity(rarity);
        return this;
    }

    public CompatibleItemSettings recipeRemainder(Item recipeRemainder) {
        settings.recipeRemainder(recipeRemainder);
        return this;
    }

    public Item.Settings build() {
        if (itemGroupId != null) {
            RegistrySupplier<ItemGroup> itemGroup = MCPLRegistry1_20.REGISTRY_SUPPLIER_ITEM_GROUP_CACHE.get(itemGroupId);
            settings.arch$tab(itemGroup);
        }
        return settings;
    }

    public CompatibleItemSettings addGroup(ItemGroup itemGroup, CompatIdentifier identifier) {
        return addGroup(itemGroup, identifier.toMinecraft());
    }

    public CompatibleItemSettings addGroup(Supplier<ItemGroup> itemGroup, CompatIdentifier identifier) {
        return addGroup(itemGroup, identifier.toMinecraft());
    }

    public CompatibleItemSettings food(CompatFoodComponent foodComponent) {
        return food(foodComponent.build());
    }
}
