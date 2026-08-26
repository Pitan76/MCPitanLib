package net.pitan76.mcpitanlib.midohra.registry;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.block.Block;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.pitan76.mcpitanlib.api.block.ICompatBlock;
import net.pitan76.mcpitanlib.api.entity.CompatEntity;
import net.pitan76.mcpitanlib.api.entity.EntityTypeBuilder;
import net.pitan76.mcpitanlib.api.gui.ExtendedScreenHandlerTypeBuilder;
import net.pitan76.mcpitanlib.api.gui.SimpleScreenHandlerTypeBuilder;
import net.pitan76.mcpitanlib.api.item.ICompatItem;
import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.v2.ItemSettingsBuilder;
import net.pitan76.mcpitanlib.api.registry.v2.CompatRegistryV2;
import net.pitan76.mcpitanlib.api.tile.BlockEntityTypeBuilder;
import net.pitan76.mcpitanlib.api.tile.CompatBlockEntity;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.block.*;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.SupplierTypedBlockEntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.entity.EntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.entity.SupplierTypedEntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.item.*;
import net.pitan76.mcpitanlib.midohra.screen.SupplierTypedScreenHandlerTypeWrapper;

import java.util.function.Supplier;

import net.pitan76.mcpitanlib.api.enchantment.EnchantmentBuilder;
import net.pitan76.mcpitanlib.api.entity.effect.StatusEffectBuilder;
import net.pitan76.mcpitanlib.api.item.CreativeTabBuilder;
import net.pitan76.mcpitanlib.api.potion.PotionBuilder;
import net.pitan76.mcpitanlib.api.sound.CompatSoundEvent;
import net.pitan76.mcpitanlib.midohra.easybuilder.BlockBuilder;
import net.pitan76.mcpitanlib.midohra.easybuilder.BlockEntityBuilder;
import net.pitan76.mcpitanlib.midohra.easybuilder.BlockWithBlockEntityBuilder;
import net.pitan76.mcpitanlib.midohra.easybuilder.ItemBuilder;
import net.pitan76.mcpitanlib.midohra.enchantment.EnchantmentWrapper;
import net.pitan76.mcpitanlib.midohra.entity.effect.SupplierStatusEffectWrapper;
import net.pitan76.mcpitanlib.midohra.fluid.SupplierFluidWrapper;
import net.pitan76.mcpitanlib.midohra.item.SupplierItemGroupWrapper;
import net.pitan76.mcpitanlib.midohra.item.SupplierItemWrapper;
import net.pitan76.mcpitanlib.midohra.potion.SupplierPotionWrapper;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Potion;

public class MidohraRegistryV2 {
    protected final MidohraRegistry registry;

    public MidohraRegistryV2(MidohraRegistry registry) {
        this.registry = registry;
    }

    public MidohraRegistryV2(CompatRegistryV2 registry) {
        this(MidohraRegistry.of(registry));
    }

    public static MidohraRegistryV2 of(CompatRegistryV2 registry) {
        return new MidohraRegistryV2(registry);
    }

    public MidohraRegistry getMidohraRegistryV1() {
        return registry;
    }

    public CompatRegistryV2 getCompatRegistry() {
        return getMidohraRegistryV1().getCompatRegistry();
    }

    // ------------------------------------------------------------------
    // easybuilder
    // ------------------------------------------------------------------

    public SupplierItemWrapper registerItem(ItemBuilder builder) {
        return builder.build(getCompatRegistry());
    }

    public SupplierItemWrapper registerItem(ItemBuilder builder, CompatIdentifier id) {
        return builder.build(getCompatRegistry(), id);
    }

    public SupplierBlockWrapper registerBlock(BlockBuilder builder) {
        return builder.build(getCompatRegistry());
    }

    public SupplierBlockWrapper registerBlock(BlockBuilder builder, CompatIdentifier id) {
        return builder.build(getCompatRegistry(), id);
    }

    public SupplierBlockWrapper registerBlock(BlockWithBlockEntityBuilder builder) {
        return builder.build(getCompatRegistry());
    }

    public SupplierBlockWrapper registerBlock(BlockWithBlockEntityBuilder builder, CompatIdentifier id) {
        return builder.build(getCompatRegistry(), id);
    }

    public BlockEntityTypeWrapper registerBlockEntityType(BlockEntityBuilder builder, CompatIdentifier id, BlockWrapper... blocks) {
        return builder.build(getCompatRegistry(), id, blocks);
    }

    public BlockEntityTypeWrapper registerBlockEntityType(BlockEntityBuilder builder, BlockWrapper... blocks) {
        return builder.build(getCompatRegistry(), blocks);
    }

    // ------------------------------------------------------------------
    // その他のレジストリ
    // ------------------------------------------------------------------

    public SupplierItemGroupWrapper registerRawItemGroup(CompatIdentifier id, Supplier<ItemGroup> supplier) {
        return SupplierItemGroupWrapper.of(getCompatRegistry().registerItemGroup(id, supplier));
    }

    public SupplierItemGroupWrapper registerItemGroup(CreativeTabBuilder builder) {
        return SupplierItemGroupWrapper.of(getCompatRegistry().registerItemGroup(builder));
    }

    public SupplierFluidWrapper registerRawFluid(CompatIdentifier id, Supplier<Fluid> supplier) {
        return SupplierFluidWrapper.of(getCompatRegistry().registerFluid(id, supplier));
    }

    public SupplierFluidWrapper registerRawFluid(String id, Supplier<Fluid> supplier) {
        return registerRawFluid(fixId(id), supplier);
    }

    public SupplierStatusEffectWrapper registerRawStatusEffect(CompatIdentifier id, Supplier<? extends StatusEffect> supplier) {
        return SupplierStatusEffectWrapper.of(getCompatRegistry().registerStatusEffect(id, supplier)::get);
    }

    public SupplierStatusEffectWrapper registerStatusEffect(StatusEffectBuilder builder) {
        return builder.build(getCompatRegistry());
    }

    public SupplierPotionWrapper registerRawPotion(CompatIdentifier id, Supplier<Potion> supplier) {
        return SupplierPotionWrapper.of(getCompatRegistry().registerPotion(id, supplier)::get);
    }

    public SupplierPotionWrapper registerPotion(PotionBuilder builder) {
        return builder.build(getCompatRegistry());
    }

    public EnchantmentWrapper registerEnchantment(EnchantmentBuilder builder) {
        return builder.build(getCompatRegistry());
    }

    public CompatSoundEvent registerSoundEvent(CompatIdentifier id) {
        return getCompatRegistry().registerCompatSoundEvent(id);
    }

    public CompatSoundEvent registerSoundEvent(CompatIdentifier id, float distanceToTravel) {
        return getCompatRegistry().registerCompatSoundEvent(id, distanceToTravel);
    }

    /**
     * かまどの燃料として登録する。
     * @param time 燃焼時間 (tick)
     */
    public void registerFuel(ItemWrapper item, int time) {
        getCompatRegistry().registerFuel(item::get, time);
    }

    public ItemWrapper registerRawItem(CompatIdentifier id, Supplier<Item> supplier) {
        return registry.registerRawItem(id, supplier);
    }

    public ItemWrapper registerRawItem(String id, Supplier<Item> supplier) {
        return registry.registerRawItem(id, supplier);
    }

    public <T extends ICompatItem> SupplierITypedItemWrapper<T> registerItem(CompatIdentifier id, Supplier<T> supplier) {
        ItemWrapper wrapper = registerRawItem(id, () -> (Item) supplier.get());
        return SupplierITypedItemWrapper.of(wrapper);
    }

    public <T extends ICompatItem> SupplierITypedItemWrapper<T> registerItem(String id, Supplier<T> supplier) {
        return registerItem(fixId(id), supplier);
    }

    public BlockWrapper registerRawBlock(CompatIdentifier id, Supplier<Block> supplier) {
        return registry.registerRawBlock(id, supplier);
    }

    public BlockWrapper registerRawBlock(String id, Supplier<Block> supplier) {
        return registry.registerRawBlock(id, supplier);
    }

    public <T extends ICompatBlock> SupplierITypedBlockWrapper<T> registerBlock(CompatIdentifier id, Supplier<T> supplier) {
        BlockWrapper wrapper = registerRawBlock(id, () -> (Block) supplier.get());
        return SupplierITypedBlockWrapper.of(wrapper);
    }

    public <T extends ICompatBlock> SupplierITypedBlockWrapper<T> registerBlock(String id, Supplier<T> supplier) {
        return registerBlock(fixId(id), supplier);
    }

    public BlockEntityTypeWrapper registerRawBlockEntityType(CompatIdentifier id, Supplier<BlockEntityType<?>> supplier) {
        return registry.registerRawBlockEntityType(id, supplier);
    }

    public BlockEntityTypeWrapper registerRawBlockEntityType(String id, Supplier<BlockEntityType<?>> supplier) {
        return registry.registerRawBlockEntityType(id, supplier);
    }

    public <T extends CompatBlockEntity> BlockEntityTypeWrapper registerRawBlockEntityType(CompatIdentifier id, BlockEntityTypeBuilder<T> builder) {
        return registry.registerRawBlockEntityType(id, builder);
    }

    public <T extends CompatBlockEntity> BlockEntityTypeWrapper registerRawBlockEntityType(String id, BlockEntityTypeBuilder<T> builder) {
        return registry.registerRawBlockEntityType(id, builder);
    }

    public <T extends CompatBlockEntity> SupplierTypedBlockEntityTypeWrapper<T> registerBlockEntityType0(CompatIdentifier id, BlockEntityTypeBuilder<T> builder) {
        BlockEntityTypeWrapper wrapper = registerRawBlockEntityType(id, builder);
        return SupplierTypedBlockEntityTypeWrapper.of(() -> (BlockEntityType<T>) wrapper.get());
    }

    public EntityTypeWrapper registerRawEntityType(CompatIdentifier id, Supplier<EntityType<?>> supplier) {
        return registry.registerRawEntityType(id, supplier);
    }

    public EntityTypeWrapper registerRawEntityType(String id, Supplier<EntityType<?>> supplier) {
        return registry.registerRawEntityType(id, supplier);
    }

    public <T extends CompatEntity> EntityTypeWrapper registerRawEntityType(CompatIdentifier id, EntityTypeBuilder<T> builder) {
        return registry.registerRawEntityType(id, builder);
    }

    public <T extends CompatEntity> EntityTypeWrapper registerRawEntityType(String id, EntityTypeBuilder<T> builder) {
        return registry.registerRawEntityType(id, builder);
    }

    public <T extends CompatEntity> SupplierTypedEntityTypeWrapper<T> registerEntityType0(CompatIdentifier id, EntityTypeBuilder<T> builder) {
        EntityTypeWrapper wrapper = registerRawEntityType(id, builder);
        return SupplierTypedEntityTypeWrapper.of(() -> (EntityType<T>) wrapper.get());
    }

    public <T extends CompatEntity> SupplierTypedEntityTypeWrapper<T> registerEntityType0(String id, EntityTypeBuilder<T> builder) {
        return registerEntityType0(fixId(id), builder);
    }

    public ItemWrapper registerRawBlockItem(CompatIdentifier id, Supplier<Block> block, CompatibleItemSettings settings) {
        return registry.registerRawBlockItem(id, block, settings);
    }

    public ItemWrapper registerRawBlockItem(CompatIdentifier id, Supplier<Block> block, ItemSettingsBuilder builder) {
        return registry.registerRawBlockItem(id, block, builder);
    }

    public <T extends Block> SupplierTypedBlockItemWrapper<T> registerBlockItem(CompatIdentifier id, BlockWrapper block, CompatibleItemSettings settings) {
        return SupplierTypedBlockItemWrapper.of(registerRawBlockItem(id, block::get, settings));
    }

    public <T extends Block> SupplierTypedBlockItemWrapper<T> registerBlockItem(CompatIdentifier id, BlockWrapper block, ItemSettingsBuilder builder) {
        return registerBlockItem(id, block, builder.build(id));
    }

    public <T extends Block> SupplierTypedBlockItemWrapper<T> registerBlockItem(String id, BlockWrapper block, CompatibleItemSettings settings) {
        return registerBlockItem(fixId(id), block, settings);
    }

    public <T extends Block> SupplierTypedBlockItemWrapper<T> registerBlockItem(String id, BlockWrapper block, ItemSettingsBuilder builder) {
        return registerBlockItem(fixId(id), block, builder);
    }

    public <T extends ScreenHandler> SupplierTypedScreenHandlerTypeWrapper<T> registerRawScreenHandlerType(CompatIdentifier id, Supplier<ScreenHandlerType<T>> supplier) {
        Supplier<ScreenHandlerType<T>> result = getCompatRegistry().registerScreenHandlerTypeSavingGenerics(id, supplier);
        return SupplierTypedScreenHandlerTypeWrapper.of(result);
    }

    public <T extends ScreenHandler> SupplierTypedScreenHandlerTypeWrapper<T> registerScreenHandlerType(CompatIdentifier id, SimpleScreenHandlerTypeBuilder<T> builder) {
        return registerRawScreenHandlerType(id, builder::build);
    }

    public <T extends ScreenHandler> SupplierTypedScreenHandlerTypeWrapper<T> registerScreenHandlerType(CompatIdentifier id, ExtendedScreenHandlerTypeBuilder<T> builder) {
        return registerRawScreenHandlerType(id, builder::build);
    }

    public <T extends ScreenHandler> SupplierTypedScreenHandlerTypeWrapper<T> registerScreenHandlerType(String id, SimpleScreenHandlerTypeBuilder<T> builder) {
        return registerScreenHandlerType(fixId(id), builder);
    }

    public <T extends ScreenHandler> SupplierTypedScreenHandlerTypeWrapper<T> registerScreenHandlerType(String id, ExtendedScreenHandlerTypeBuilder<T> builder) {
        return registerScreenHandlerType(fixId(id), builder);
    }

    /**
     * If the id doesn't contain a namespace, add the default namespace to it.
     * @param id The id to fix.
     * @return The fixed id.
     */
    public CompatIdentifier fixId(String id) {
        return registry.fixId(id);
    }
}
