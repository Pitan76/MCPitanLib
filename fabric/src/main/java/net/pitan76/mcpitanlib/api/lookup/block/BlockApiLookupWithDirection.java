package net.pitan76.mcpitanlib.api.lookup.block;

import net.fabricmc.fabric.api.lookup.v1.block.BlockApiLookup;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.util.math.Direction;
import net.pitan76.mcpitanlib.midohra.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

public class BlockApiLookupWithDirection<A> extends BlockApiLookupWrapper<A, net.minecraft.util.math.Direction> {

    public BlockApiLookupWithDirection(BlockApiLookup<@NotNull A, net.minecraft.util.math.Direction> lookup) {
        super(lookup);
    }

    @Deprecated
    public static <A, C> BlockApiLookupWrapper<A, C> of(BlockApiLookup<@NotNull A, C> lookup) {
        return new BlockApiLookupWrapper<>(lookup);
    }

    public static <A> BlockApiLookupWithDirection<A> ofDir(BlockApiLookup<@NotNull A, net.minecraft.util.math.Direction> lookup) {
        return new BlockApiLookupWithDirection<>(lookup);
    }

    public @Nullable A find(World world, BlockPos pos, Direction direction) {
        return super.find(world, pos, direction.toMinecraft());
    }

    public @Nullable A find(World world, BlockPos pos, BlockState state, BlockEntity blockEntity, Direction direction) {
        return super.find(world, pos, state, blockEntity, direction.toMinecraft());
    }

    public @Nullable A find(World world, BlockPos pos, BlockState state, BlockEntityWrapper blockEntity, Direction direction) {
        return super.find(world, pos, state, blockEntity, direction.toMinecraft());
    }

    public <T extends BlockEntity> void registerForBlockEntityM(BiFunction<? super T, Direction, @Nullable A> provider, BlockEntityType<T> blockEntityType) {
        super.registerForBlockEntity((blockEntity, direction) -> provider.apply(blockEntity, Direction.of(direction)), blockEntityType);
    }

    @Deprecated
    public <T extends BlockEntity> void registerForBlockEntity(BiFunction<? super T, net.minecraft.util.math.Direction, @Nullable A> provider, BlockEntityType<T> blockEntityType) {
        super.registerForBlockEntity(provider, blockEntityType);
    }

    public void registerForBlockEntityWrapperM(BiFunction<? super BlockEntityWrapper, Direction, @Nullable A> provider, BlockEntityTypeWrapper blockEntityType) {
        super.registerForBlockEntityWrapper((blockEntity, direction) -> provider.apply(blockEntity, Direction.of(direction)), blockEntityType);
    }
}