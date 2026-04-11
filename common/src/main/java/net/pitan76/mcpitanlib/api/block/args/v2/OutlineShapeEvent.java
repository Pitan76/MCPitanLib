package net.pitan76.mcpitanlib.api.block.args.v2;

import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.item.Item;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.holder.BlockStatePropertyHolder;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.world.BlockView;
import net.pitan76.mcpitanlib.midohra.world.IWorldView;

public class OutlineShapeEvent extends BaseEvent implements BlockStatePropertyHolder {
    public BlockState state;
    public BlockView world;
    public BlockPos pos;
    public CollisionContext context;

    public OutlineShapeEvent(BlockState state, BlockView world, BlockPos pos, CollisionContext context) {
        this.state = state;
        this.world = world;
        this.pos = pos;
        this.context = context;
    }

    public OutlineShapeEvent(net.minecraft.world.level.block.state.BlockState state, net.minecraft.world.level.BlockGetter world, net.minecraft.core.BlockPos pos, net.minecraft.world.phys.shapes.CollisionContext context) {
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

    public CollisionContext getContext() {
        return context;
    }

    public boolean isHolding(Item item) {
        return getContext().isHoldingItem(item);
    }

    public boolean isHolding(ItemWrapper item) {
        return isHolding(item.get());
    }
}
