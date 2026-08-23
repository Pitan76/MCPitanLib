package net.pitan76.mcpitanlib.api.block.v3;

import net.pitan76.mcpitanlib.api.block.args.v2.CollisionShapeEvent;
import net.pitan76.mcpitanlib.api.block.args.v2.OutlineShapeEvent;
import net.pitan76.mcpitanlib.api.block.v2.BlockSettingsBuilder;
import net.pitan76.mcpitanlib.api.block.v2.CompatibleBlockSettings;
import net.pitan76.mcpitanlib.api.event.block.FluidStateArgs;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.fluid.FluidState;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import net.pitan76.mcpitanlib.midohra.util.shape.VoxelShape;

public class CompatBlock extends net.pitan76.mcpitanlib.api.block.v2.CompatBlock {

    public CompatBlock(CompatibleBlockSettings settings) {
        super(settings);
    }

    public CompatBlock(BlockSettingsBuilder builder, CompatIdentifier id) {
        super(builder.build(id));
    }

    public CompatBlock(BlockSettingsBuilder builder) {
        super(builder.build());
    }

    public ItemWrapper getItemWrapper() {
        return getWrapper().asItem();
    }

    public VoxelShape getCollisionShapeM(CollisionShapeEvent e) {
        return VoxelShape.of(super.getCollisionShape(e.state.toMinecraft(), e.world.getRaw(), e.pos.toMinecraft(), e.context));
    }

    public VoxelShape getOutlineShapeM(OutlineShapeEvent e) {
        return VoxelShape.of(super.getOutlineShape(e.state.toMinecraft(), e.world.getRaw(), e.pos.toMinecraft(), e.context));
    }

    public FluidState getFluidStateM(FluidStateArgs args) {
        return FluidState.of(super.getFluidState(args));
    }

    @Override
    @Deprecated
    public net.minecraft.util.shape.VoxelShape getCollisionShape(CollisionShapeEvent e) {
        return getCollisionShapeM(e).raw();
    }

    @Override
    @Deprecated
    public net.minecraft.util.shape.VoxelShape getOutlineShape(OutlineShapeEvent e) {
        return getOutlineShapeM(e).raw();
    }

    @Override
    @Deprecated
    public net.minecraft.fluid.FluidState getFluidState(FluidStateArgs args) {
        return getFluidStateM(args).getRaw();
    }
}
