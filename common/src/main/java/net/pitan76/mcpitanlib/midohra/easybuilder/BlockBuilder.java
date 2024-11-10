package net.pitan76.mcpitanlib.midohra.easybuilder;

import net.minecraft.item.Item;
import net.pitan76.mcpitanlib.api.block.CompatibleMaterial;
import net.pitan76.mcpitanlib.api.block.ExtendBlock;
import net.pitan76.mcpitanlib.api.block.v2.BlockSettingsBuilder;
import net.pitan76.mcpitanlib.api.event.block.AppendPropertiesArgs;
import net.pitan76.mcpitanlib.api.event.block.BlockUseEvent;
import net.pitan76.mcpitanlib.api.event.block.StateReplacedEvent;
import net.pitan76.mcpitanlib.api.event.item.ItemAppendTooltipEvent;
import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.api.registry.v2.CompatRegistryV2;
import net.pitan76.mcpitanlib.api.sound.CompatBlockSoundGroup;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.color.CompatDyeColor;
import net.pitan76.mcpitanlib.api.util.color.CompatMapColor;
import net.pitan76.mcpitanlib.api.util.item.ItemUtil;
import net.pitan76.mcpitanlib.core.datafixer.Pair;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.block.SupplierBlockWrapper;
import net.pitan76.mcpitanlib.midohra.item.SupplierItemWrapper;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class BlockBuilder {

    public BlockSettingsBuilder settingsBuilder;
    public Function<BlockUseEvent, CompatActionResult> onRightClick;
    public Consumer<StateReplacedEvent> onStateReplaced;
    public Consumer<ItemAppendTooltipEvent> onAppendTooltip;
    public Consumer<AppendPropertiesArgs> onAppendProperties;

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
}
