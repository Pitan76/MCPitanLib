package net.pitan76.mcpitanlib.api.item.v3.tool;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.pitan76.mcpitanlib.api.item.args.tool.MiningSpeedMultiplierArgs;
import net.pitan76.mcpitanlib.api.item.args.tool.SuitableForArgs;
import net.pitan76.mcpitanlib.api.item.tool.CompatibleShovelItem;
import net.pitan76.mcpitanlib.api.item.tool.CompatibleToolMaterial;
import net.pitan76.mcpitanlib.api.item.v2.CompatibleItemSettings;
import net.pitan76.mcpitanlib.api.item.v2.ItemSettingsBuilder;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

public class CompatShovelItem extends CompatibleShovelItem {

    public CompatShovelItem(CompatibleToolMaterial material, float attackDamage, float attackSpeed, CompatibleItemSettings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    public CompatShovelItem(CompatibleToolMaterial material, float attackDamage, float attackSpeed, ItemSettingsBuilder builder, CompatIdentifier id) {
        this(material, attackDamage, attackSpeed, builder.build(id));
    }

    public CompatShovelItem(CompatibleToolMaterial material, float attackDamage, float attackSpeed, ItemSettingsBuilder builder) {
        this(material, attackDamage, attackSpeed, builder.build());
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
