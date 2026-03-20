package net.pitan76.mcpitanlib.api.lookup.block;

import net.fabricmc.fabric.api.lookup.v1.block.BlockApiLookup;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiFunction;

public class BlockApiLookupWrapper<A, C> {

    private final BlockApiLookup<@NotNull A, C> lookup;

    public BlockApiLookupWrapper(BlockApiLookup<@NotNull A, C> lookup) {
        this.lookup = lookup;
    }

    public BlockApiLookup<@NotNull A, C> getRaw() {
        return lookup;
    }

    public static <A, C> BlockApiLookupWrapper<A, C> of(BlockApiLookup<@NotNull A, C> lookup) {
        return new BlockApiLookupWrapper<>(lookup);
    }

    public @Nullable A find(World world, BlockPos pos, C context) {
        return lookup.find(world.getRaw(), pos.toMinecraft(), context);
    }

    public @Nullable A find(World world, BlockPos pos, BlockState state, BlockEntity blockEntity, C context) {
        return lookup.find(world.getRaw(), pos.toMinecraft(), state.toMinecraft(), blockEntity, context);
    }

    public @Nullable A find(World world, BlockPos pos, BlockState state, BlockEntityWrapper blockEntity, C context) {
        return lookup.find(world.getRaw(), pos.toMinecraft(), state.toMinecraft(), blockEntity.get(), context);
    }

    public <T extends BlockEntity> void registerForBlockEntity(BiFunction<? super T, C, @Nullable A> provider, BlockEntityType<T> blockEntityType) {
        lookup.registerForBlockEntity(provider, blockEntityType);
    }

    public void registerForBlockEntityWrapper(BiFunction<BlockEntityWrapper, C, @Nullable A> provider, BlockEntityTypeWrapper blockEntityWrapperType) {
        lookup.registerForBlockEntity((blockEntity, context) ->
                provider.apply(BlockEntityWrapper.of(blockEntity), context), blockEntityWrapperType.get());
    }

    public CompatIdentifier getId() {
        return CompatIdentifier.fromMinecraft(lookup.getId());
    }
}