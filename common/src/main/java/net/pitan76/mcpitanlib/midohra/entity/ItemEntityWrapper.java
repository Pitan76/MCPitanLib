package net.pitan76.mcpitanlib.midohra.entity;

import net.pitan76.mcpitanlib.api.util.entity.ItemEntityUtil;
import net.pitan76.mcpitanlib.midohra.item.ItemStack;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.util.math.Vector3d;
import net.pitan76.mcpitanlib.midohra.world.World;

public class ItemEntityWrapper extends EntityWrapper {
    public ItemEntityWrapper(net.minecraft.entity.ItemEntity entity) {
        super(entity);
    }

    public static ItemEntityWrapper of(net.minecraft.entity.ItemEntity entity) {
        return new ItemEntityWrapper(entity);
    }

    @Override
    public net.minecraft.entity.ItemEntity get() {
        return (net.minecraft.entity.ItemEntity) super.get();
    }

    public net.minecraft.item.ItemStack getStackRaw() {
        return ItemEntityUtil.getStack(get());
    }

    public ItemStack getStack() {
        return ItemStack.of(getStackRaw());
    }

    public void setStack(net.minecraft.item.ItemStack stack) {
        get().setStack(stack);
    }

    public void setStack(ItemStack stack) {
        setStack(stack.toMinecraft());
    }

    public ItemWrapper getItem() {
        return getStack().getItem();
    }

    public static ItemEntityWrapper create(ItemStack stack, World world, double x, double y, double z) {
        return of(ItemEntityUtil.create(world.toMinecraft(), x, y, z, stack.toMinecraft()));
    }

    public static ItemEntityWrapper create(ItemStack stack, World world, Vector3d pos) {
        return create(stack, world, pos.getX(), pos.getY(), pos.getZ());
    }

    public static ItemEntityWrapper create(ItemStack stack, World world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        return of(ItemEntityUtil.create(world.toMinecraft(), x, y, z, stack.toMinecraft(), velocityX, velocityY, velocityZ));
    }

    public static ItemEntityWrapper create(ItemStack stack, World world, Vector3d pos, Vector3d velocity) {
        return create(stack, world, pos.getX(), pos.getY(), pos.getZ(), velocity.getX(), velocity.getY(), velocity.getZ());
    }

    public static ItemEntityWrapper create(ItemStack stack, World world, BlockPos pos) {
        return create(stack, world, pos.getX(), pos.getY(), pos.getZ());
    }

    public static ItemEntityWrapper createWithSpawn(ItemStack stack, World world, double x, double y, double z) {
        return of(ItemEntityUtil.createWithSpawn(world.toMinecraft(), stack.toMinecraft(), x, y, z));
    }

    public static ItemEntityWrapper createWithSpawn(ItemStack stack, World world, Vector3d pos) {
        return createWithSpawn(stack, world, pos.getX(), pos.getY(), pos.getZ());
    }

    public static ItemEntityWrapper createWithSpawn(ItemStack stack, World world, BlockPos pos) {
        return createWithSpawn(stack, world, pos.getX(), pos.getY(), pos.getZ());
    }

    public void setToDefaultPickupDelay() {
        ItemEntityUtil.setToDefaultPickupDelay(get());
    }
}
