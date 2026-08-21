package net.pitan76.mcpitanlib.midohra.block;

import net.minecraft.state.property.Property;
import net.pitan76.mcpitanlib.api.sound.CompatBlockSoundGroup;
import net.pitan76.mcpitanlib.api.state.property.BlockHalfProperty;
import net.pitan76.mcpitanlib.api.state.property.DirectionProperty;
import net.pitan76.mcpitanlib.api.state.property.IProperty;
import net.pitan76.mcpitanlib.api.state.property.StairShapeProperty;
import net.pitan76.mcpitanlib.api.util.BlockStateUtil;
import net.pitan76.mcpitanlib.api.util.math.CompatBlockMirror;
import net.pitan76.mcpitanlib.api.util.math.CompatBlockRotation;
import net.pitan76.mcpitanlib.api.util.block.properties.CompatBlockHalf;
import net.pitan76.mcpitanlib.api.util.block.properties.CompatStairShape;
import net.pitan76.mcpitanlib.midohra.fluid.FluidState;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.world.ServerWorld;
import net.pitan76.mcpitanlib.midohra.world.World;

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
        return toMinecraft() == null;
    }

    public boolean isAir() {
        return isEmpty() || BlockStateUtil.isAir(toMinecraft());
    }

    public CompatBlockSoundGroup getSoundGroup() {
        return BlockStateUtil.getCompatSoundGroup(toMinecraft());
    }

    public BlockWrapper getBlock() {
        if (blockWrapper == null)
            blockWrapper = BlockWrapper.of(BlockStateUtil.getBlock(toMinecraft()));

        return blockWrapper;
    }

    public String getName() {
        return getBlock().getName();
    }

    public net.minecraft.block.BlockState toMinecraft() {
        return state;
    }

    public BlockState rotate(CompatBlockRotation rotation) {
        return of(BlockStateUtil.rotate(toMinecraft(), rotation));
    }

    public BlockState mirror(CompatBlockMirror mirror) {
        return of(BlockStateUtil.mirror(toMinecraft(), mirror));
    }

    public <T extends Comparable<T>, V extends T> BlockState with(Property<T> property, V value) {
        return of(toMinecraft().with(property, value));
    }

    public <T extends Comparable<T>, V extends T> BlockState with(IProperty<T> property, V value) {
        return with(property.getProperty(), value);
    }

    public BlockState with(DirectionProperty property, net.pitan76.mcpitanlib.midohra.util.math.Direction value) {
        return of(property.with(toMinecraft(), value.toMinecraft()));
    }

    public BlockState with(BlockHalfProperty property, CompatBlockHalf value) {
        return of(property.with(toMinecraft(), value.getBlockHalf()));
    }

    public BlockState with(StairShapeProperty property, CompatStairShape value) {
        return of(property.with(toMinecraft(), value.getStairShape()));
    }

    public <T extends Comparable<T>> BlockState cycle(Property<T> property) {
        return of(toMinecraft().cycle(property));
    }

    public <T extends Comparable<T>> BlockState cycle(IProperty<T> property) {
        return cycle(property.getProperty());
    }

    public BlockState cycle(DirectionProperty property) {
        return of(property.cycle(toMinecraft()));
    }

    public <T extends Comparable<T>> T get(Property<T> property) {
        return toMinecraft().get(property);
    }

    public <T extends Comparable<T>> T get(IProperty<T> property) {
        return get(property.getProperty());
    }

    public net.pitan76.mcpitanlib.midohra.util.math.Direction get(DirectionProperty property) {
        return net.pitan76.mcpitanlib.midohra.util.math.Direction.of(get(property.getProperty()));
    }

    public CompatBlockHalf get(BlockHalfProperty property) {
        return CompatBlockHalf.of(get(property.getProperty()));
    }

    public CompatStairShape get(StairShapeProperty property) {
        return CompatStairShape.of(get(property.getProperty()));
    }

    public <T extends Comparable<T>> boolean contains(Property<T> property) {
        return toMinecraft().contains(property);
    }

    public <T extends Comparable<T>> boolean contains(IProperty<T> property) {
        return contains(property.getProperty());
    }

    public boolean contains(DirectionProperty property) {
        return contains(property.getProperty());
    }

    public boolean contains(BlockHalfProperty property) {
        return contains(property.getProperty());
    }

    public boolean contains(StairShapeProperty property) {
        return contains(property.getProperty());
    }

    @Override
    public int hashCode() {
        return toMinecraft().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BlockState state = (BlockState) obj;
        return toMinecraft().equals(state.toMinecraft());
    }

    public boolean isOpaque() {
        return BlockStateUtil.isOpaque(toMinecraft());
    }

    public boolean hasRandomTicks() {
        return BlockStateUtil.hasRandomTicks(toMinecraft());
    }

    public boolean canPlaceAt(BlockPos pos, World world) {
        return BlockStateUtil.canPlaceAt(this, pos, world);
    }

    public int getLuminance() {
        return BlockStateUtil.getLuminance(this);
    }

    public int getOpacity() {
        return BlockStateUtil.getOpacity(this);
    }

    public int getComparatorOutput(World world, BlockPos pos) {
        return BlockStateUtil.getComparatorOutput(this, world, pos);
    }

    public float getHardness(World world, BlockPos pos) {
        return BlockStateUtil.getHardness(this.toMinecraft(), world.getRaw(), pos.toRaw());
    }

    public boolean randomTick(ServerWorld world, BlockPos pos) {
        if (!hasRandomTicks())
            return false;

        BlockStateUtil.randomTick(this.toMinecraft(), world.getRaw(), pos.toRaw());
        return true;
    }

    public FluidState getFluidState() {
        return FluidState.of(BlockStateUtil.getFluidState(this.toMinecraft()));
    }
}
