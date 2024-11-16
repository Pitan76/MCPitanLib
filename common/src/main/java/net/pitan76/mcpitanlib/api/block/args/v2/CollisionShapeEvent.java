package net.pitan76.mcpitanlib.api.block.args.v2;

import net.minecraft.block.ShapeContext;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.holder.BlockStatePropertyHolder;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.world.BlockView;
import net.pitan76.mcpitanlib.midohra.world.IWorldView;

public class CollisionShapeEvent extends BaseEvent implements BlockStatePropertyHolder {
    public BlockState state;
    public BlockView world;
    public BlockPos pos;
    public ShapeContext context;

    public CollisionShapeEvent(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.context = context;
    }

    public CollisionShapeEvent(net.minecraft.block.BlockState state, net.minecraft.world.BlockView world, net.minecraft.util.math.BlockPos pos, ShapeContext context) {
        this(BlockState.of(state), BlockView.of(world), BlockPos.of(pos), context);
    }

    @Override
    public BlockState getBlockState() {
        return state;
    }

    public BlockEntityWrapper getBlockEntity() {
        return world.getBlockEntity(pos);
    }

    public BlockEntity getRawBlockEntity() {
        return getBlockEntity().get();
    }

    public IWorldView getWorldView() {
        return world;
    }

    public BlockPos getPos() {
        return pos;
    }

    public ShapeContext getContext() {
        return context;
    }

    public boolean isHolding(Item item) {
        return getContext().isHolding(item);
    }

    public boolean isHolding(ItemWrapper item) {
        return isHolding(item.get());
    }
}
