package net.pitan76.mcpitanlib.midohra;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
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

    public static ItemGroupWrapper of(ItemGroup itemGroup) {
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

    public static FluidWrapper of(net.minecraft.fluid.Fluid fluid) {
        return FluidWrapper.of(fluid);
    }

    public static BlockState of(net.minecraft.block.BlockState state) {
        return BlockState.of(state);
    }

    public static NbtElement of(net.minecraft.nbt.NbtElement nbt) {
        return NbtElement.of(nbt);
    }

    public static NbtCompound of(net.minecraft.nbt.NbtCompound nbt) {
        return NbtCompound.of(nbt);
    }

    public static NbtList of(net.minecraft.nbt.NbtList nbt) {
        return NbtList.of(nbt);
    }

    public static BlockPos of(net.minecraft.util.math.BlockPos pos) {
        return BlockPos.of(pos);
    }

    public static Direction of(net.minecraft.util.math.Direction direction) {
        return Direction.of(direction);
    }

    public static World of(net.minecraft.world.World world) {
        return World.of(world);
    }

    public static WorldView of(net.minecraft.world.WorldView world) {
        return WorldView.of(world);
    }

    public static WorldAccess of(net.minecraft.world.WorldAccess world) {
        return WorldAccess.of(world);
    }

    public static BlockView of(net.minecraft.world.BlockView world) {
        return BlockView.of(world);
    }

    public static IWorldView asWV(net.minecraft.world.WorldView world) {
        return of(world);
    }

    public static IWorldView asWV(net.minecraft.world.BlockView world) {
        return of(world);
    }

    public static Item raw(ItemWrapper item) {
        return item.get();
    }

    public static ItemGroup raw(ItemGroupWrapper itemGroup) {
        return itemGroup.get();
    }

    public static Block raw(BlockWrapper block) {
        return block.get();
    }

    public static BlockEntity raw(BlockEntityWrapper blockEntity) {
        return blockEntity.get();
    }

    public static net.minecraft.fluid.Fluid raw(FluidWrapper fluid) {
        return fluid.get();
    }

    public static net.minecraft.block.BlockState raw(BlockState state) {
        return state.toMinecraft();
    }

    public static net.minecraft.nbt.NbtElement raw(NbtElement nbt) {
        return nbt.toMinecraft();
    }

    public static net.minecraft.nbt.NbtCompound raw(NbtCompound nbt) {
        return nbt.toMinecraft();
    }

    public static net.minecraft.nbt.NbtList raw(NbtList nbt) {
        return nbt.toMinecraft();
    }

    public static net.minecraft.util.math.BlockPos raw(BlockPos pos) {
        return pos.toMinecraft();
    }

    public static net.minecraft.util.math.Direction raw(Direction direction) {
        return direction.toMinecraft();
    }

    public static net.minecraft.world.World raw(World world) {
        return world.toMinecraft();
    }

    public static net.minecraft.world.WorldView raw(WorldView world) {
        return world.toMinecraft();
    }

    public static net.minecraft.world.WorldAccess raw(WorldAccess world) {
        return world.toMinecraft();
    }

    public static net.minecraft.world.BlockView raw(BlockView world) {
        return world.toMinecraft();
    }
}
