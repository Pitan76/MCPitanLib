package net.pitan76.mcpitanlib.api.item;

import net.fabricmc.fabric.api.creativetab.v1.CreativeModeTabEvents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.pitan76.mcpitanlib.api.registry.result.RegistrySupplier;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.core.registry.MCPLRegistry1_20;

import java.util.function.Supplier;

@Deprecated
public class CompatibleItemSettings {
    protected final ExtendSettings settings = new ExtendSettings();

    protected Identifier itemGroupId = null;
    protected Identifier _itemId = null;

    @Deprecated
    public static CompatibleItemSettings of() {
        return new CompatibleItemSettings();
    }

    // ～1.19.2
    public CompatibleItemSettings addGroup(CreativeModeTab itemGroup) {
        settings.addGroup(itemGroup);
        return this;
    }

    // 1.19.3～
    // identifier: Item ID
    public CompatibleItemSettings addGroup(CreativeModeTab itemGroup, Identifier identifier) {
        settings.addGroup(itemGroup, identifier);
        _itemId = identifier;
        return this;
    }

    public CompatibleItemSettings addGroup(Supplier<CreativeModeTab> itemGroup, Identifier identifier) {
        settings.addGroup(itemGroup, identifier);
        _itemId = identifier;
        return this;
    }

    public CompatibleItemSettings addGroup(CreativeTabBuilder itemGroup) {
        this.itemGroupId = itemGroup.getIdentifier();
        return this;
    }

    public CompatibleItemSettings maxCount(int maxCount) {
        settings.stacksTo(maxCount);
        return this;
    }

    public CompatibleItemSettings maxDamage(int maxDamage) {
        settings.durability(maxDamage);
        return this;
    }

    public CompatibleItemSettings maxDamageIfAbsent(int maxDamage) {
        try {
            settings.durability(maxDamage);
        } catch (Exception ignored) {}

        return this;
    }

    @Deprecated
    public CompatibleItemSettings food(FoodProperties foodComponent) {
        settings.food(foodComponent);
        return this;
    }

    public CompatibleItemSettings rarity(Rarity rarity) {
        settings.rarity(rarity);
        return this;
    }

    public CompatibleItemSettings recipeRemainder(Item recipeRemainder) {
        settings.craftRemainder(recipeRemainder);
        return this;
    }

    public ExtendSettings build() {
        if (itemGroupId != null && _itemId != null) {
            ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, itemGroupId);
//            RegistrySupplier<CreativeModeTab> itemGroup = MCPLRegistry1_20.REGISTRY_SUPPLIER_ITEM_GROUP_CACHE.get(key);
//            CreativeModeTabEvents.modifyOutputEvent(key).register(entries -> {
//                entries.accept(new ItemStack());
//            });
//                if (output instanceof Item.Settings settings)
//            settings.arch$tab(itemGroup);
            MCPLRegistry1_20.ITEM_GROUP_ITEM_ID_CACHE.put(key, _itemId);
        }

        if (_itemId != null && !(this instanceof net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings)) {
            settings.setId(ResourceKey.create(Registries.ITEM, _itemId));
        }

        return settings;
    }

    public CompatibleItemSettings addGroup(CreativeModeTab itemGroup, CompatIdentifier identifier) {
        return addGroup(itemGroup, identifier.toMinecraft());
    }

    public CompatibleItemSettings addGroup(Supplier<CreativeModeTab> itemGroup, CompatIdentifier identifier) {
        return addGroup(itemGroup, identifier.toMinecraft());
    }

    public CompatibleItemSettings food(CompatFoodComponent foodComponent) {
        return food(foodComponent.build());
    }
}
