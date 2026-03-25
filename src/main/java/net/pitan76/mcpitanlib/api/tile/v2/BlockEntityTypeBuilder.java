package net.pitan76.mcpitanlib.api.tile.v2;

import com.mojang.datafixers.types.Type;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.pitan76.mcpitanlib.midohra.block.SupplierBlockWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class BlockEntityTypeBuilder<T extends BlockEntity> extends net.pitan76.mcpitanlib.api.tile.BlockEntityTypeBuilder<T> {
    private final Factory<? extends T> factory;
    private final Consumer<List<Block>> consumer;

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
        return new BlockEntityTypeBuilder<>(factory, blocks -> {
            blocks.add(block.get());
        });
    }

    public static <T extends BlockEntity> BlockEntityTypeBuilder<T> create(BlockEntityTypeBuilder.Factory<? extends T> factory, SupplierBlockWrapper wrapper) {
        return new BlockEntityTypeBuilder<>(factory, blocks -> {
            blocks.add(wrapper.get());
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

    public BlockEntityType<T> build(Type<?> type) {
        List<Block> blocks = new ArrayList<>();
        if (consumer != null)
            consumer.accept(blocks);

        return build(type, factory, blocks.toArray(new Block[0]));
    }
}
