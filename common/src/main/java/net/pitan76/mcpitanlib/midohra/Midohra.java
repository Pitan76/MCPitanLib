package net.pitan76.mcpitanlib.midohra;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.CreativeModeTab;
import net.pitan76.mcpitanlib.api.registry.result.RegistryResult;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.block.BlockWrapper;
import net.pitan76.mcpitanlib.midohra.block.SupplierBlockWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.fluid.FluidWrapper;
import net.pitan76.mcpitanlib.midohra.item.ItemGroupWrapper;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;
import net.pitan76.mcpitanlib.midohra.nbt.NbtCompound;
import net.pitan76.mcpitanlib.midohra.nbt.NbtElement;
import net.pitan76.mcpitanlib.midohra.nbt.NbtList;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.util.math.Direction;
import net.pitan76.mcpitanlib.midohra.world.*;

import java.util.function.Supplier;

public class Midohra {

    public static ItemWrapper of(Item item) {
        return ItemWrapper.of(item);
    }

    public static ItemGroupWrapper of(CreativeModeTab itemGroup) {
        return ItemGroupWrapper.of(itemGroup);
    }
    public static BlockWrapper of(Block block) {
        return BlockWrapper.of(block);
    }

    public static SupplierBlockWrapper of(Supplier<Block> block) {
        return SupplierBlockWrapper.of(block);
    }

    public static SupplierBlockWrapper of(RegistryResult<Block> block) {
        return SupplierBlockWrapper.of(block);
    }

    public static BlockEntityWrapper of(BlockEntity blockEntity) {
        return BlockEntityWrapper.of(blockEntity);
    }

    public static FluidWrapper of(net.minecraft.world.level.material.Fluid fluid) {
        return FluidWrapper.of(fluid);
    }

    public static BlockState of(net.minecraft.world.level.block.state.BlockState state) {
        return BlockState.of(state);
    }

    public static NbtElement of(net.minecraft.nbt.Tag nbt) {
        return NbtElement.of(nbt);
    }

    public static NbtCompound of(net.minecraft.nbt.CompoundTag nbt) {
        return NbtCompound.of(nbt);
    }

    public static NbtList of(net.minecraft.nbt.ListTag nbt) {
        return NbtList.of(nbt);
    }

    public static BlockPos of(net.minecraft.core.BlockPos pos) {
        return BlockPos.of(pos);
    }

    public static Direction of(net.minecraft.core.Direction direction) {
        return Direction.of(direction);
    }

    public static World of(net.minecraft.world.level.Level world) {
        return World.of(world);
    }

    public static WorldView of(net.minecraft.world.level.LevelReader world) {
        return WorldView.of(world);
    }

    public static WorldAccess of(net.minecraft.world.level.LevelAccessor world) {
        return WorldAccess.of(world);
    }

    public static BlockView of(net.minecraft.world.level.BlockGetter world) {
        return BlockView.of(world);
    }

    public static IWorldView asWV(net.minecraft.world.level.LevelReader world) {
        return of(world);
    }

    public static IWorldView asWV(net.minecraft.world.level.BlockGetter world) {
        return of(world);
    }

    public static Item raw(ItemWrapper item) {
        return item.get();
    }

    public static CreativeModeTab raw(ItemGroupWrapper itemGroup) {
        return itemGroup.get();
    }

    public static Block raw(BlockWrapper block) {
        return block.get();
    }

    public static BlockEntity raw(BlockEntityWrapper blockEntity) {
        return blockEntity.get();
    }

    public static net.minecraft.world.level.material.Fluid raw(FluidWrapper fluid) {
        return fluid.get();
    }

    public static net.minecraft.world.level.block.state.BlockState raw(BlockState state) {
        return state.toMinecraft();
    }

    public static net.minecraft.nbt.Tag raw(NbtElement nbt) {
        return nbt.toMinecraft();
    }

    public static net.minecraft.nbt.CompoundTag raw(NbtCompound nbt) {
        return nbt.toMinecraft();
    }

    public static net.minecraft.nbt.ListTag raw(NbtList nbt) {
        return nbt.toMinecraft();
    }

    public static net.minecraft.core.BlockPos raw(BlockPos pos) {
        return pos.toMinecraft();
    }

    public static net.minecraft.core.Direction raw(Direction direction) {
        return direction.toMinecraft();
    }

    public static net.minecraft.world.level.Level raw(World world) {
        return world.toMinecraft();
    }

    public static net.minecraft.world.level.LevelReader raw(WorldView world) {
        return world.toMinecraft();
    }

    public static net.minecraft.world.level.LevelAccessor raw(WorldAccess world) {
        return world.toMinecraft();
    }

    public static net.minecraft.world.level.BlockGetter raw(BlockView world) {
        return world.toMinecraft();
    }
}
