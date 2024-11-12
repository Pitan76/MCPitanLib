package net.pitan76.mcpitanlib.midohra.block;

import net.minecraft.state.property.Property;
import net.pitan76.mcpitanlib.api.sound.CompatBlockSoundGroup;
import net.pitan76.mcpitanlib.api.state.property.IProperty;
import net.pitan76.mcpitanlib.api.util.BlockStateUtil;

public class BlockState {
    private final net.minecraft.block.BlockState state;

    private BlockWrapper blockWrapper;

    protected BlockState(net.minecraft.block.BlockState state) {
        this.state = state;
    }

    public static BlockState of(net.minecraft.block.BlockState state) {
        return new BlockState(state);
    }

    public static BlockState of(net.minecraft.block.Block block) {
        if (block == null)
            return of((net.minecraft.block.BlockState) null);

        return of(BlockStateUtil.getDefaultState(block));
    }

    public boolean isExist() {
        return !isEmpty();
    }

    public boolean isEmpty() {
        return state == null;
    }

    public boolean isAir() {
        return isEmpty() || BlockStateUtil.isAir(state);
    }

    public CompatBlockSoundGroup getSoundGroup() {
        return BlockStateUtil.getCompatSoundGroup(state);
    }

    public BlockWrapper getBlock() {
        if (blockWrapper == null)
            blockWrapper = BlockWrapper.of(BlockStateUtil.getBlock(state));

        return blockWrapper;
    }

    public String getName() {
        return getBlock().getName();
    }

    @Deprecated
    public net.minecraft.block.BlockState toMinecraft() {
        return state;
    }

    public <T extends Comparable<T>, V extends T> BlockState with(Property<T> property, V value) {
        return of(state.with(property, value));
    }

    public <T extends Comparable<T>, V extends T> BlockState with(IProperty<T> property, V value) {
        return with(property.getProperty(), value);
    }

    public <T extends Comparable<T>> BlockState cycle(Property<T> property) {
        return of(state.cycle(property));
    }

    public <T extends Comparable<T>> BlockState cycle(IProperty<T> property) {
        return cycle(property.getProperty());
    }

    public <T extends Comparable<T>> T get(Property<T> property) {
        return state.get(property);
    }

    public <T extends Comparable<T>> T get(IProperty<T> property) {
        return get(property.getProperty());
    }

    public <T extends Comparable<T>> boolean contains(Property<T> property) {
        return state.contains(property);
    }

    public <T extends Comparable<T>> boolean contains(IProperty<T> property) {
        return contains(property.getProperty());
    }
}
