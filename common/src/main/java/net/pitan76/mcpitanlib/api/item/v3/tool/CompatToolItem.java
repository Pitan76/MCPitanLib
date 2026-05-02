package net.pitan76.mcpitanlib.api.item.v3.tool;

import net.minecraft.item.ItemStack;
import net.minecraft.block.BlockState;
import net.pitan76.mcpitanlib.api.item.args.tool.MiningSpeedMultiplierArgs;
import net.pitan76.mcpitanlib.api.item.args.tool.SuitableForArgs;
import net.pitan76.mcpitanlib.api.item.tool.CompatibleToolItem;
import net.pitan76.mcpitanlib.api.item.tool.CompatibleToolMaterial;
import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.v2.ItemSettingsBuilder;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class CompatToolItem extends CompatibleToolItem {

    public CompatToolItem(CompatibleToolMaterial material, CompatibleItemSettings settings) {
        super(material, settings);
    }

    public CompatToolItem(CompatibleToolMaterial material, ItemSettingsBuilder builder, CompatIdentifier id) {
        super(material, builder.build(id));
    }

    public CompatToolItem(CompatibleToolMaterial material, ItemSettingsBuilder builder) {
        super(material, builder.build());
    }

    public float getMiningSpeedMultiplier(MiningSpeedMultiplierArgs args) {
        return super.overrideGetMiningSpeedMultiplier(args.stack.toMinecraft(), args.state.toMinecraft());
    }

    @Override
    @Deprecated
    public float overrideGetMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        return getMiningSpeedMultiplier(
                new MiningSpeedMultiplierArgs(net.pitan76.mcpitanlib.midohra.item.ItemStack.of(stack),
                        net.pitan76.mcpitanlib.midohra.block.BlockState.of(state)));
    }

    public boolean isSuitableFor(SuitableForArgs args) {
        return super.overrideIsSuitableFor(args.state.toMinecraft());
    }

    @Override
    @Deprecated
    public boolean overrideIsSuitableFor(BlockState state) {
        return isSuitableFor(new SuitableForArgs(net.pitan76.mcpitanlib.midohra.block.BlockState.of(state)));
    }
}
