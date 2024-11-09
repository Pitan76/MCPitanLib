package net.pitan76.mcpitanlib.midohra.easybuilder;

import net.pitan76.mcpitanlib.api.event.item.ItemAppendTooltipEvent;
import net.pitan76.mcpitanlib.api.event.item.ItemUseEvent;
import net.pitan76.mcpitanlib.api.event.item.ItemUseOnBlockEvent;
import net.pitan76.mcpitanlib.api.event.item.ItemUseOnEntityEvent;
import net.pitan76.mcpitanlib.api.item.CompatFoodComponent;
import net.pitan76.mcpitanlib.api.item.CreativeTabBuilder;
import net.pitan76.mcpitanlib.api.item.ExtendItem;
import net.pitan76.mcpitanlib.api.item.v2.ItemSettingsBuilder;
import net.pitan76.mcpitanlib.api.registry.v2.CompatRegistryV2;
import net.pitan76.mcpitanlib.api.tag.item.RepairIngredientTag;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.CompatRarity;
import net.pitan76.mcpitanlib.api.util.StackActionResult;
import net.pitan76.mcpitanlib.midohra.item.ItemGroupWrapper;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import net.pitan76.mcpitanlib.midohra.item.SupplierItemWrapper;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ItemBuilder {

    public ItemSettingsBuilder settingsBuilder;
    public Function<ItemUseEvent, StackActionResult> onRightClick;
    public Function<ItemUseOnBlockEvent, CompatActionResult> onRightClickOnBlock;
    public Function<ItemUseOnEntityEvent, CompatActionResult> onRightClickOnEntity;
    public Consumer<ItemAppendTooltipEvent> onAppendTooltip;

    public ItemBuilder(ItemSettingsBuilder settingsBuilder) {
        this.settingsBuilder = settingsBuilder;
    }

    public ItemBuilder(CompatIdentifier id) {
        this(new ItemSettingsBuilder(id));
    }

    public static ItemBuilder of(CompatIdentifier id) {
        return new ItemBuilder(id);
    }

    public static ItemBuilder of(ItemSettingsBuilder settingsBuilder) {
        return new ItemBuilder(settingsBuilder);
    }

    public SupplierItemWrapper build(CompatRegistryV2 registry) {
        if (settingsBuilder.id == null)
            throw new IllegalStateException("Item id is not set. hint: use build(CompatRegistryV2, CompatIdentifier)");

        Supplier<ExtendItem> result = registry.registerExtendItem(settingsBuilder.id, () -> new BuiltItem(this));

        return SupplierItemWrapper.of(result::get);
    }

    public SupplierItemWrapper build(CompatRegistryV2 registry, CompatIdentifier id) {
        Supplier<ExtendItem> result = registry.registerExtendItem(settingsBuilder.id, () -> new BuiltItem(this, id));

        return SupplierItemWrapper.of(result::get);
    }

    public ItemBuilder maxCount(int maxCount) {
        settingsBuilder.maxCount(maxCount);
        return this;
    }

    public ItemBuilder maxDamage(int maxDamage) {
        settingsBuilder.maxDamage(maxDamage);
        return this;
    }

    public ItemBuilder maxDamageIfAbsent(int maxDamage) {
        settingsBuilder.maxDamageIfAbsent(maxDamage);
        return this;
    }

    public ItemBuilder food(CompatFoodComponent foodComponent) {
        settingsBuilder.foodComponent(foodComponent);
        return this;
    }

    public ItemBuilder recipeRemainder(ItemWrapper recipeRemainder) {
        settingsBuilder.recipeRemainder(recipeRemainder);
        return this;
    }

    public ItemBuilder enchantability(int enchantability) {
        settingsBuilder.enchantability(enchantability);
        return this;
    }

    public ItemBuilder repairable(CompatIdentifier repairIngredientTag) {
        settingsBuilder.repairable(repairIngredientTag);
        return this;
    }

    public ItemBuilder repairable(RepairIngredientTag repairIngredientTag) {
        settingsBuilder.repairable(repairIngredientTag);
        return this;
    }

    public ItemBuilder rarity(CompatRarity rarity) {
        settingsBuilder.rarity(rarity);
        return this;
    }

    public ItemBuilder group(ItemGroupWrapper itemGroup) {
        settingsBuilder.addGroup(itemGroup);
        return this;
    }

    public ItemBuilder group(CreativeTabBuilder itemGroup) {
        settingsBuilder.addGroup(itemGroup);
        return this;
    }

    public ItemBuilder onRightClick(Function<ItemUseEvent, StackActionResult> onRightClick) {
        this.onRightClick = onRightClick;
        return this;
    }

    public ItemBuilder onRightClickOnBlock(Function<ItemUseOnBlockEvent, CompatActionResult> onRightClickOnBlock) {
        this.onRightClickOnBlock = onRightClickOnBlock;
        return this;
    }

    public ItemBuilder onRightClickOnEntity(Function<ItemUseOnEntityEvent, CompatActionResult> onRightClickOnEntity) {
        this.onRightClickOnEntity = onRightClickOnEntity;
        return this;
    }

    public ItemBuilder onAppendTooltip(Consumer<ItemAppendTooltipEvent> onAppendTooltip) {
        this.onAppendTooltip = onAppendTooltip;
        return this;
    }
}
