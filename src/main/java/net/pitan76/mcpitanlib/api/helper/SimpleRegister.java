package net.pitan76.mcpitanlib.api.helper;

import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.block.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.block.ExtendBlock;
import net.pitan76.mcpitanlib.api.item.CompatFoodComponent;
import net.pitan76.mcpitanlib.api.item.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.CreativeTabBuilder;
import net.pitan76.mcpitanlib.api.item.ExtendItem;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.registry.v2.CompatRegistryV2;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.ItemStackUtil;
import net.pitan76.mcpitanlib.api.util.ItemUtil;
import net.pitan76.mcpitanlib.core.datafixer.Pair;

import java.util.function.Supplier;

public class SimpleRegister {
    public static Supplier<ExtendItem> createItem(CompatRegistryV2 registry, CompatIdentifier id, CompatibleItemSettings settings) {
        return registry.registerExtendItem(id, () -> new ExtendItem(settings));
    }

    public static Supplier<ExtendItem> createSimpleItem(CompatRegistryV2 registry, CompatIdentifier id) {
        return createItem(registry, id, new CompatibleItemSettings());
    }

    public static Supplier<ExtendItem> createFoodItem(CompatRegistryV2 registry, CompatIdentifier id, CompatFoodComponent component) {
        return createItem(registry, id, new CompatibleItemSettings().food(component));
    }

    public static Supplier<ExtendItem> createSimpleFoodItem(CompatRegistryV2 registry, CompatIdentifier id, int hunger, float saturation, boolean meat, boolean snack) {
        CompatFoodComponent component = new CompatFoodComponent().setHunger(hunger).setSaturation(saturation);
        if (meat) component.setMeat();
        if (snack) component.setSnack();
        return createFoodItem(registry, id, component);
    }

    public static Supplier<ExtendItem> createSimpleFoodItem(CompatRegistryV2 registry, CompatIdentifier id, int hunger, float saturation) {
        return createSimpleFoodItem(registry, id, hunger, saturation, false, false);
    }

    public static Pair<Supplier<ExtendBlock>, Supplier<Item>> createBlock(CompatRegistryV2 registry, CompatIdentifier id, CompatibleBlockSettings blockSettings, CompatibleItemSettings itemSettings) {
        Supplier<ExtendBlock> blockSupplier = registry.registerExtendBlock(id, () -> new ExtendBlock(blockSettings));
        RegistryResult<Item> itemRegistryResult = registry.registerItem(id, () -> ItemUtil.ofBlock(blockSupplier.get(), itemSettings));
        Supplier<Item> itemSupplier = itemRegistryResult::getOrNull;
        return new Pair<>(blockSupplier, itemSupplier);
    }

    public static Pair<Supplier<ExtendBlock>, Supplier<Item>> createSimpleBlock(CompatRegistryV2 registry, CompatIdentifier id, CompatibleItemSettings itemSettings) {
        return createBlock(registry, id, new CompatibleBlockSettings(), itemSettings);
    }

    public static Pair<Supplier<ExtendBlock>, Supplier<Item>> createSimpleBlock(CompatRegistryV2 registry, CompatIdentifier id) {
        return createBlock(registry, id, new CompatibleBlockSettings(), new CompatibleItemSettings());
    }

    public static CreativeTabBuilder createCreativeTab(CompatRegistryV2 registry, CompatIdentifier id, Supplier<ItemStack> icon) {
        CreativeTabBuilder builder = CreativeTabBuilder.create(id).setIcon(icon);
        registry.registerItemGroup(builder);
        return builder;
    }

    public static CreativeTabBuilder createCreativeTabByItem(CompatRegistryV2 registry, CompatIdentifier id, Supplier<ItemConvertible> item) {
        return createCreativeTab(registry, id, () -> ItemStackUtil.create(item.get()));
    }
}
