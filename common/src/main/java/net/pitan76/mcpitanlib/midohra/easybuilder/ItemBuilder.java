package net.pitan76.mcpitanlib.midohra.easybuilder;

import net.pitan76.mcpitanlib.api.CommonModInitializer;
import net.pitan76.mcpitanlib.api.event.item.*;
import net.pitan76.mcpitanlib.api.item.CompatFoodComponent;
import net.pitan76.mcpitanlib.api.item.CreativeTabBuilder;
import net.pitan76.mcpitanlib.api.item.ExtendItem;
import net.pitan76.mcpitanlib.api.item.v2.ItemSettingsBuilder;
import net.pitan76.mcpitanlib.api.registry.v2.CompatRegistryV2;
import net.pitan76.mcpitanlib.midohra.registry.MidohraRegistryV2;
import net.pitan76.mcpitanlib.api.tag.item.RepairIngredientTag;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.CompatRarity;
import net.pitan76.mcpitanlib.api.util.StackActionResult;
import net.pitan76.mcpitanlib.midohra.easybuilder.built.BuiltItem;
import net.pitan76.mcpitanlib.midohra.item.ItemGroupWrapper;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import net.pitan76.mcpitanlib.midohra.item.SupplierItemWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ItemBuilder {

    public ItemSettingsBuilder settingsBuilder;
    public Function<ItemUseEvent, StackActionResult> onRightClick;
    public Function<ItemUseOnBlockEvent, CompatActionResult> onRightClickOnBlock;
    public Function<ItemUseOnEntityEvent, CompatActionResult> onRightClickOnEntity;
    public Consumer<ItemAppendTooltipEvent> onAppendTooltip;
    public Function<ItemBarColorArgs, Integer> onItemBarColor;
    public Function<ItemBarStepArgs, Integer> onItemBarStep;

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

    public SupplierItemWrapper build(CommonModInitializer initializer) {
        return build(initializer.registry);
    }

    public SupplierItemWrapper build(CommonModInitializer initializer, CompatIdentifier id) {
        return build(initializer.registry, id);
    }

    public SupplierItemWrapper build(MidohraRegistryV2 registry) {
        return build(registry.getCompatRegistry());
    }

    public SupplierItemWrapper build(MidohraRegistryV2 registry, CompatIdentifier id) {
        return build(registry.getCompatRegistry(), id);
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

    public ItemBuilder onItemBarColor(Function<ItemBarColorArgs, Integer> onItemBarColor) {
        this.onItemBarColor = onItemBarColor;
        return this;
    }

    public ItemBuilder onItemBarStep(Function<ItemBarStepArgs, Integer> onItemBarStep) {
        this.onItemBarStep = onItemBarStep;
        return this;
    }

    private final List<TextComponent> tooltip = new ArrayList<>();

    public ItemBuilder addTooltip(TextComponent text) {
        if (tooltip.isEmpty()) {
            onAppendTooltip = e -> e.getTooltip().add(text.getText());
        } else {
            onAppendTooltip = e -> {
                for (TextComponent t : tooltip) {
                    e.getTooltip().add(t.getText());
                }
                e.getTooltip().add(text.getText());
            };
        }

        this.tooltip.add(text);
        return this;
    }

    public ItemBuilder write(ItemBuilder copy) {
        copy.settingsBuilder = this.settingsBuilder;
        copy.onRightClick = this.onRightClick;
        copy.onRightClickOnBlock = this.onRightClickOnBlock;
        copy.onRightClickOnEntity = this.onRightClickOnEntity;
        copy.onAppendTooltip = this.onAppendTooltip;
        copy.onItemBarColor = this.onItemBarColor;
        copy.onItemBarStep = this.onItemBarStep;

        return copy;
    }

    public ItemBuilder copy(ItemSettingsBuilder builder) {
        return write(new ItemBuilder(builder));
    }

    public ItemBuilder copy() {
        return copy(this.settingsBuilder.copy());
    }
}
