package net.pitan76.mcpitanlib.midohra.easybuilder;

import net.minecraft.item.Item;
import net.pitan76.mcpitanlib.api.CommonModInitializer;
import net.pitan76.mcpitanlib.api.block.CompatBlockRenderType;
import net.pitan76.mcpitanlib.api.block.CompatibleMaterial;
import net.pitan76.mcpitanlib.api.block.ExtendBlock;
import net.pitan76.mcpitanlib.api.block.args.RenderTypeArgs;
import net.pitan76.mcpitanlib.api.block.args.v2.CollisionShapeEvent;
import net.pitan76.mcpitanlib.api.block.args.v2.OutlineShapeEvent;
import net.pitan76.mcpitanlib.api.block.args.v2.PlacementStateArgs;
import net.pitan76.mcpitanlib.api.block.args.v2.StateForNeighborUpdateArgs;
import net.pitan76.mcpitanlib.api.block.v2.BlockSettingsBuilder;
import net.pitan76.mcpitanlib.api.event.block.AppendPropertiesArgs;
import net.pitan76.mcpitanlib.api.event.block.BlockUseEvent;
import net.pitan76.mcpitanlib.api.event.block.DroppedStacksArgs;
import net.pitan76.mcpitanlib.api.event.block.StateReplacedEvent;
import net.pitan76.mcpitanlib.api.event.item.ItemAppendTooltipEvent;
import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.registry.v2.CompatRegistryV2;
import net.pitan76.mcpitanlib.midohra.registry.MidohraRegistryV2;
import net.pitan76.mcpitanlib.api.sound.CompatBlockSoundGroup;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.color.CompatDyeColor;
import net.pitan76.mcpitanlib.api.util.color.CompatMapColor;
import net.pitan76.mcpitanlib.api.util.item.ItemUtil;
import net.pitan76.mcpitanlib.core.datafixer.Pair;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;
import net.pitan76.mcpitanlib.midohra.block.SupplierBlockWrapper;
import net.pitan76.mcpitanlib.midohra.easybuilder.built.BuiltBlock;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;
import net.pitan76.mcpitanlib.midohra.item.SupplierItemWrapper;
import net.pitan76.mcpitanlib.midohra.util.shape.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.*;

public class BlockBuilder {

    public BlockSettingsBuilder settingsBuilder;
    public Function<BlockUseEvent, CompatActionResult> onRightClick;
    public Consumer<StateReplacedEvent> onStateReplaced;
    public Consumer<ItemAppendTooltipEvent> onAppendTooltip;
    public Consumer<AppendPropertiesArgs> onAppendProperties;
    public BlockState defaultState;
    public BiConsumer<BlockWrapper, BlockBuilder> onInit;
    public Function<OutlineShapeEvent, net.minecraft.util.shape.VoxelShape> onOutlineShape;
    public Function<CollisionShapeEvent, net.minecraft.util.shape.VoxelShape> onCollisionShape;
    public Function<RenderTypeArgs, CompatBlockRenderType> onRenderType;
    public Function<PlacementStateArgs, @Nullable BlockState> onPlacementState;
    public Function<StateForNeighborUpdateArgs, BlockState> onStateForNeighborUpdate;
    public Function<DroppedStacksArgs, List<net.minecraft.item.ItemStack>> onDroppedStacks;

    public BlockBuilder(BlockSettingsBuilder settingsBuilder) {
        this.settingsBuilder = settingsBuilder;
    }

    public BlockBuilder(CompatIdentifier id) {
        this(new BlockSettingsBuilder(id));
    }

    public static BlockBuilder of(CompatIdentifier id) {
        return new BlockBuilder(id);
    }

    public static BlockBuilder of(BlockSettingsBuilder settingsBuilder) {
        return new BlockBuilder(settingsBuilder);
    }

    public SupplierBlockWrapper build(CompatRegistryV2 registry) {
        if (settingsBuilder.id == null)
            throw new IllegalStateException("Block id is not set. hint: use build(CompatRegistryV2, CompatIdentifier)");

        Supplier<ExtendBlock> result = registry.registerExtendBlock(settingsBuilder.id, () -> new BuiltBlock(this));

        return SupplierBlockWrapper.of(result::get);
    }

    public SupplierBlockWrapper build(CompatRegistryV2 registry, CompatIdentifier id) {
        Supplier<ExtendBlock> result = registry.registerExtendBlock(id, () -> new BuiltBlock(this, id));

        return SupplierBlockWrapper.of(result::get);
    }

    public SupplierBlockWrapper build(CommonModInitializer initializer) {
        return build(initializer.registry);
    }

    public SupplierBlockWrapper build(CommonModInitializer initializer, CompatIdentifier id) {
        return build(initializer.registry, id);
    }

    public SupplierBlockWrapper build(MidohraRegistryV2 registry) {
        return build(registry.getCompatRegistry());
    }

    public SupplierBlockWrapper build(MidohraRegistryV2 registry, CompatIdentifier id) {
        return build(registry.getCompatRegistry(), id);
    }

    public Pair<SupplierBlockWrapper, SupplierItemWrapper> buildWithItem(CompatRegistryV2 registry, CompatibleItemSettings settings) {
        SupplierBlockWrapper block = build(registry);

        RegistryResult<Item> result = registry.registerItem(settingsBuilder.id, () -> ItemUtil.create(block.get(), settings));

        return Pair.of(block, SupplierItemWrapper.of(result::get));
    }

    public Pair<SupplierBlockWrapper, SupplierItemWrapper> buildWithItem(CompatRegistryV2 registry, CompatIdentifier id, CompatibleItemSettings settings) {
        SupplierBlockWrapper block = build(registry, id);

        RegistryResult<Item> result = registry.registerItem(id, () -> ItemUtil.create(block.get(), settings.setId(id)));

        return Pair.of(block, SupplierItemWrapper.of(result::get));
    }

    public Pair<SupplierBlockWrapper, SupplierItemWrapper> buildWithItem(CommonModInitializer initializer, CompatibleItemSettings settings) {
        return buildWithItem(initializer.registry, settings);
    }

    public Pair<SupplierBlockWrapper, SupplierItemWrapper> buildWithItem(CommonModInitializer initializer, CompatIdentifier id, CompatibleItemSettings settings) {
        return buildWithItem(initializer.registry, id, settings);
    }

    public BlockBuilder material(CompatibleMaterial material) {
        settingsBuilder.material(material);
        return this;
    }

    public BlockBuilder sounds(CompatBlockSoundGroup sounds) {
        settingsBuilder.sounds(sounds);
        return this;
    }

    public BlockBuilder strength(float hardness, float resistance) {
        settingsBuilder.strength(hardness, resistance);
        return this;
    }

    public BlockBuilder strength(float hardness) {
        settingsBuilder.hardness(hardness);
        return this;
    }

    public BlockBuilder resistance(float resistance) {
        settingsBuilder.resistance(resistance);
        return this;
    }

    public BlockBuilder mapColor(CompatMapColor color) {
        settingsBuilder.mapColor(color);
        return this;
    }

    public BlockBuilder mapColor(CompatDyeColor color) {
        settingsBuilder.dyeColor(color);
        return this;
    }

    public BlockBuilder luminance(ToIntFunction<BlockState> luminance) {
        settingsBuilder.luminance(luminance);
        return this;
    }

    public BlockBuilder requiresTool() {
        settingsBuilder.requiresTool();
        return this;
    }

    public BlockBuilder dropsNothing() {
        settingsBuilder.dropsNothing();
        return this;
    }

    public BlockBuilder onRightClick(Function<BlockUseEvent, CompatActionResult> onRightClick) {
        this.onRightClick = onRightClick;
        return this;
    }

    public BlockBuilder onStateReplaced(Consumer<StateReplacedEvent> onStateReplaced) {
        this.onStateReplaced = onStateReplaced;
        return this;
    }

    public BlockBuilder onAppendTooltip(Consumer<ItemAppendTooltipEvent> onAppendTooltip) {
        this.onAppendTooltip = onAppendTooltip;
        return this;
    }

    public BlockBuilder onAppendProperties(Consumer<AppendPropertiesArgs> onAppendProperties) {
        this.onAppendProperties = onAppendProperties;
        return this;
    }

    public BlockBuilder onOutlineShape(Function<OutlineShapeEvent, VoxelShape> onOutlineShape) {
        return onOutlineShapeRaw(e -> onOutlineShape.apply(e).raw());
    }

    public BlockBuilder onCollisionShape(Function<CollisionShapeEvent, VoxelShape> onCollisionShape) {
        return onCollisionShapeRaw(e -> onCollisionShape.apply(e).raw());
    }

    public BlockBuilder onOutlineShapeRaw(Function<OutlineShapeEvent, net.minecraft.util.shape.VoxelShape> onOutlineShape) {
        this.onOutlineShape = onOutlineShape;
        return this;
    }

    public BlockBuilder onCollisionShapeRaw(Function<CollisionShapeEvent, net.minecraft.util.shape.VoxelShape> onCollisionShape) {
        this.onCollisionShape = onCollisionShape;
        return this;
    }

    public BlockBuilder onRenderType(Function<RenderTypeArgs, CompatBlockRenderType> onRenderType) {
        this.onRenderType = onRenderType;
        return this;
    }

    public BlockBuilder onPlacementState(Function<PlacementStateArgs, @Nullable BlockState> onPlacementState) {
        this.onPlacementState = onPlacementState;
        return this;
    }

    public BlockBuilder onStateForNeighborUpdate(Function<StateForNeighborUpdateArgs, BlockState> onStateForNeighborUpdate) {
        this.onStateForNeighborUpdate = onStateForNeighborUpdate;
        return this;
    }

    public BlockBuilder setOutlineShape(VoxelShape shape) {
        return onOutlineShape(e -> shape);
    }

    public BlockBuilder setCollisionShape(VoxelShape shape) {
        return onCollisionShape(e -> shape);
    }

    public BlockBuilder setOutlineShape(net.minecraft.util.shape.VoxelShape shape) {
        return onOutlineShapeRaw(e -> shape);
    }

    public BlockBuilder setCollisionShape(net.minecraft.util.shape.VoxelShape shape) {
        return onCollisionShapeRaw(e -> shape);
    }

    public BlockBuilder setRenderType(CompatBlockRenderType renderType) {
        return onRenderType(e -> renderType);
    }

    public BlockBuilder onInit(BiConsumer<BlockWrapper, BlockBuilder> onInit) {
        this.onInit = onInit;
        return this;
    }

    public BlockBuilder setDefaultState(BlockState defaultState) {
        this.defaultState = defaultState;
        return this;
    }

    private final List<TextComponent> tooltip = new ArrayList<>();

    public BlockBuilder addTooltip(TextComponent text) {
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

    public BlockBuilder onDroppedStacksRaw(Function<DroppedStacksArgs, List<net.minecraft.item.ItemStack>> onDroppedStacks) {
        this.onDroppedStacks = onDroppedStacks;
        return this;
    }

    public BlockBuilder onDroppedStacks(Function<DroppedStacksArgs, List<ItemStack>> onDroppedStacks) {
        return onDroppedStacksRaw(e -> onDroppedStacks.apply(e).stream()
                .map(ItemStack::toMinecraft)
                .toList());
    }

    public BlockBuilder write(BlockBuilder copy) {
        copy.onRightClick = onRightClick;
        copy.onStateReplaced = onStateReplaced;
        copy.onAppendTooltip = onAppendTooltip;
        copy.onAppendProperties = onAppendProperties;
        copy.defaultState = defaultState;
        copy.onInit = onInit;
        copy.onOutlineShape = onOutlineShape;
        copy.onCollisionShape = onCollisionShape;
        copy.onRenderType = onRenderType;
        copy.onPlacementState = onPlacementState;
        copy.onStateForNeighborUpdate = onStateForNeighborUpdate;
        copy.onDroppedStacks = onDroppedStacks;

        return copy;
    }

    public BlockBuilder copy(BlockSettingsBuilder settingsBuilder) {
        return write(new BlockBuilder(settingsBuilder));
    }

    public BlockBuilder copy() {
        return copy(this.settingsBuilder.copy());
    }
}
