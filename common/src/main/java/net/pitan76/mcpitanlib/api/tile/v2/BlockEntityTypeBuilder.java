package net.pitan76.mcpitanlib.api.tile.v2;

import com.mojang.datafixers.types.Type;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.midohra.block.SupplierBlockWrapper;
import net.pitan76.mcpitanlib.midohra.core.INonTypedSupplier;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BlockEntityTypeBuilder<T extends BlockEntity> extends net.pitan76.mcpitanlib.api.tile.BlockEntityTypeBuilder<T> {
    public final Factory<? extends T> factory;
    public final Consumer<List<Block>> consumer;

    public BlockEntityTypeBuilder(Factory<? extends T> factory, Consumer<List<Block>> blocks) {
        super(null, null);

        this.factory = factory;
        this.consumer = blocks;
    }

    @Deprecated
    public static <T extends BlockEntity> BlockEntityTypeBuilder<T> create(Factory<? extends T> factory, Block... blocks) {
        throw new IllegalStateException("Use create(Factory, Consumer<List<Block>>) instead");
    }

    public static <T extends BlockEntity> BlockEntityTypeBuilder<T> create(BlockEntityTypeBuilder.Factory<? extends T> factory, Consumer<List<Block>> blocks) {
        return new BlockEntityTypeBuilder<>(factory, blocks);
    }

    public static <T extends BlockEntity> BlockEntityTypeBuilder<T> createA(BlockEntityTypeBuilder.Factory<? extends T> factory, Supplier<Block> block) {
        return new BlockEntityTypeBuilder<>(factory, blocks -> blocks.add(block.get()));
    }

    public static <T extends BlockEntity> BlockEntityTypeBuilder<T> create(BlockEntityTypeBuilder.Factory<? extends T> factory, SupplierBlockWrapper wrapper) {
        return new BlockEntityTypeBuilder<>(factory, blocks -> blocks.add(wrapper.get()));
    }

    public static <T extends BlockEntity> BlockEntityTypeBuilder<T> create(BlockEntityTypeBuilder.Factory<? extends T> factory, INonTypedSupplier<SupplierBlockWrapper> wrapper) {
        return create(factory, wrapper.asNonTyped());
    }

    public static <T extends BlockEntity> BlockEntityTypeBuilder<T> create(BlockEntityTypeBuilder.Factory<? extends T> factory, INonTypedSupplier<SupplierBlockWrapper>... wrappers) {
        return create(factory, blocks -> {
            for (INonTypedSupplier<SupplierBlockWrapper> wrapper : wrappers) {
                blocks.add(wrapper.asNonTyped().get());
            }
        });
    }

    @Override
    @Deprecated
    public BlockEntityTypeBuilder<T> addBlock(Block block) {
        return this;
    }

    @Override
    @Deprecated
    public BlockEntityTypeBuilder<T> addBlocks(Block... blocks) {
        return this;
    }

    public BlockEntityType<T> build() {
        return build(null);
    }

    @ExpectPlatform
    public BlockEntityType<T> build(Type<?> type) {
        throw new AssertionError();
    }
}
