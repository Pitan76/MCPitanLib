package net.pitan76.mcpitanlib.api.event.item;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.BaseEvent;
import net.pitan76.mcpitanlib.api.item.ArmorEquipmentType;
import net.pitan76.mcpitanlib.api.tag.TagKey;
import net.pitan76.mcpitanlib.api.util.BlockUtil;
import net.pitan76.mcpitanlib.api.util.WorldUtil;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;
import net.pitan76.mcpitanlib.midohra.world.World;

public class PostMineEvent extends BaseEvent {
    public ItemStack stack;
    public Level world;
    public BlockState state;
    public BlockPos pos;
    public LivingEntity miner;

    public PostMineEvent(ItemStack stack, Level world, BlockState state, BlockPos pos, LivingEntity miner) {
        this.stack = stack;
        this.world = world;
        this.state = state;
        this.pos = pos;
        this.miner = miner;
    }

    public BlockState getState() {
        return state;
    }

    public BlockPos getPos() {
        return pos;
    }

    public Level getWorld() {
        return world;
    }

    public ItemStack getStack() {
        return stack;
    }

    public LivingEntity getMiner() {
        return miner;
    }

    public BlockEntity getBlockEntity() {
        return WorldUtil.getBlockEntity(world, pos);
    }

    public boolean isClient() {
        return world.isClientSide();
    }

    public boolean stateIsIn(TagKey<Block> tagKey) {
        return BlockUtil.isIn(state.getBlock(), tagKey);
    }

    public boolean stateIsOf(Block block) {
        return BlockUtil.isEqual(state.getBlock(), block);
    }

    /**
     * Damages the stack in the given slot
     * @param amount the amount of damage to deal
     * @param slot the slot to damage
     */
    public void damageStack(int amount, EquipmentSlot slot) {
        stack.hurtAndBreak(amount, miner, slot);
    }

    /**
     * Damages the stack in the given slot
     * @param amount the amount of damage to deal
     * @param type the type of armor equipment
     */
    public void damageStack(int amount, ArmorEquipmentType type) {
        stack.hurtAndBreak(amount, miner, type.getSlot());
    }

    /**
     * Damages the stack in the main hand
     * @param amount the amount of damage to deal
     */
    public void damageStack(int amount) {
        stack.hurtAndBreak(amount, miner, EquipmentSlot.MAINHAND);
    }

    public boolean isPlayer() {
        return miner instanceof net.minecraft.world.entity.player.Player;
    }

    public Player getPlayer() {
        if (isPlayer())
            return new Player((net.minecraft.world.entity.player.Player) miner);

        return null;
    }

    public boolean isCreative() {
        return isPlayer() && getPlayer().isCreative();
    }

    public boolean isSneaking() {
        return miner.isShiftKeyDown();
    }

    public ItemStack getMainHandStack() {
        return miner.getMainHandItem();
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getStackM() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getStack());
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getMainHandStackM() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getMainHandStack());
    }

    public World getWorldM() {
        return World.of(getWorld());
    }

    public net.pitan76.mcpitanlib.midohra.block.BlockState getStateM() {
        return net.pitan76.mcpitanlib.midohra.block.BlockState.of(getState());
    }

    public net.pitan76.mcpitanlib.midohra.util.math.BlockPos getPosM() {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(getPos());
    }

    public BlockEntityWrapper getBlockEntityM() {
        BlockEntity blockEntity = getBlockEntity();
        if (blockEntity != null) {
            return BlockEntityWrapper.of(blockEntity);
        }
        return null;
    }

    public EntityWrapper getMinerM() {
        return EntityWrapper.of(getMiner());
    }

    public boolean stateIsOf(BlockWrapper block) {
        return stateIsOf(block.get());
    }
}
