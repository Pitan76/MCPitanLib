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
        return super.getCollisionShapeM(e);
    }

    public VoxelShape getOutlineShapeM(OutlineShapeEvent e) {
        return super.getOutlineShapeM(e);
    }

    public FluidState getFluidStateM(FluidStateArgs args) {
        return FluidState.of(super.getFluidState(args));
    }

    @Override
    @Deprecated
    public net.minecraft.util.shape.VoxelShape getCollisionShape(CollisionShapeEvent e) {
        VoxelShape shape = getCollisionShapeM(e);
        if (shape != null) return shape.raw();
        return super.getCollisionShape(e);
    }

    @Override
    @Deprecated
    public net.minecraft.util.shape.VoxelShape getOutlineShape(OutlineShapeEvent e) {
        VoxelShape shape = getOutlineShapeM(e);
        if (shape != null) return shape.raw();
        return super.getOutlineShape(e);
    }

    @Override
    @Deprecated
    public net.minecraft.fluid.FluidState getFluidState(FluidStateArgs args) {
        FluidState state = getFluidStateM(args);
        if (state != null) return state.getRaw();
        return super.getFluidState(args);
    }
}
