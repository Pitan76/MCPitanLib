package net.pitan76.mcpitanlib.midohra.world;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.sound.CompatSoundCategory;
import net.pitan76.mcpitanlib.api.sound.CompatSoundEvent;
import net.pitan76.mcpitanlib.api.util.math.random.CompatRandom;
import net.pitan76.mcpitanlib.api.util.world.WorldAccessUtil;
import net.pitan76.mcpitanlib.midohra.block.BlockState;
import net.pitan76.mcpitanlib.midohra.block.entity.BlockEntityWrapper;
import net.pitan76.mcpitanlib.midohra.entity.EntityTypeWrapper;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;
import net.pitan76.mcpitanlib.midohra.server.MCServer;
import net.pitan76.mcpitanlib.midohra.util.math.BlockPos;
import net.pitan76.mcpitanlib.midohra.util.math.Box;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class WorldAccess extends WorldView {
    private final net.minecraft.world.WorldAccess world;

    protected WorldAccess(net.minecraft.world.WorldAccess world) {
        super(null);
        this.world = world;
    }

    public static WorldAccess of(net.minecraft.world.WorldAccess world) {
        return new WorldAccess(world);
    }

    @Override
    protected net.minecraft.world.WorldAccess getRaw() {
        return world;
    }

    public net.minecraft.world.WorldAccess toMinecraft() {
        return getRaw();
    }

    public boolean isClient() {
        return WorldAccessUtil.isClient(getRaw());
    }

    public boolean isServer() {
        return !isClient();
    }

    public CompatRandom getRandom() {
        return new CompatRandom(getRaw().getRandom());
    }

    public MinecraftServer getServer() {
        return WorldAccessUtil.getServer(getRaw());
    }

    public BlockEntityWrapper getBlockEntity(BlockPos pos) {
        return BlockEntityWrapper.of(WorldAccessUtil.getBlockEntity(getRaw(), pos.toMinecraft()));
    }

    public boolean hasBlockEntity(BlockPos pos) {
        return WorldAccessUtil.getBlockEntity(getRaw(), pos.toMinecraft()) != null;
    }

    public <T extends BlockEntity> Optional<T> getRawBlockEntity(BlockPos pos, BlockEntityType<T> type) {
        return WorldAccessUtil.getBlockEntity(getRaw(), pos.toMinecraft(), type);
    }

    public <T extends BlockEntity> BlockEntityWrapper getBlockEntity(BlockPos pos, BlockEntityType<T> type) {
        Optional<T> blockEntity = WorldAccessUtil.getBlockEntity(getRaw(), pos.toMinecraft(), type);
        return blockEntity.map(BlockEntityWrapper::of).orElse(BlockEntityWrapper.of());
    }

    public boolean removeBlock(BlockPos pos, boolean move) {
        return WorldAccessUtil.removeBlock(getRaw(), pos.toMinecraft(), move);
    }

    public boolean breakBlock(BlockPos pos, boolean drop) {
        return WorldAccessUtil.breakBlock(getRaw(), pos.toMinecraft(), drop);
    }

    public boolean breakBlock(BlockPos pos, boolean drop, Entity entity) {
        return WorldAccessUtil.breakBlock(getRaw(), pos.toMinecraft(), drop, entity);
    }

    public BlockState getBlockState(BlockPos pos) {
        return BlockState.of(WorldAccessUtil.getBlockState(getRaw(), pos.toMinecraft()));
    }

    public boolean setBlockState(BlockPos pos, BlockState state, int flags) {
        return WorldAccessUtil.setBlockState(getRaw(), pos.toMinecraft(), state.toMinecraft(), flags);
    }

    public boolean setBlockState(BlockPos pos, BlockState state, int flags, int maxUpdateDepth) {
        return WorldAccessUtil.setBlockState(getRaw(), pos.toMinecraft(), state.toMinecraft(), flags, maxUpdateDepth);
    }

    public boolean setBlockState(BlockPos pos, BlockState state) {
        return WorldAccessUtil.setBlockState(getRaw(), pos.toMinecraft(), state.toMinecraft());
    }

    @Deprecated
    public void playSound(PlayerEntity playerEntity, net.minecraft.util.math.BlockPos pos, SoundEvent sound, SoundCategory category, float volume, float pitch) {
        getRaw().playSound(playerEntity, pos, sound, category, volume, pitch);
    }

    @Deprecated
    public void playSound(PlayerEntity playerEntity, net.minecraft.util.math.BlockPos pos, SoundEvent sound, SoundCategory category) {
        playSound(playerEntity, pos, sound, category, 1.0F, 1.0F);
    }

    public void playSound(Player player, BlockPos pos, CompatSoundEvent soundEvent, CompatSoundCategory category, float volume, float pitch) {
        playSound(player.getEntity(), pos.toMinecraft(), soundEvent.get(), category.get(), volume, pitch);
    }

    public void playSound(Player player, BlockPos pos, CompatSoundEvent soundEvent, CompatSoundCategory category) {
        playSound(player.getEntity(), pos.toMinecraft(), soundEvent.get(), category.get());
    }

    public void playSound(BlockPos pos, CompatSoundEvent soundEvent, CompatSoundCategory category, float volume, float pitch) {
        playSound(null, pos.toMinecraft(), soundEvent.get(), category.get(), volume, pitch);
    }

    public void playSound(BlockPos pos, CompatSoundEvent soundEvent, CompatSoundCategory category) {
        playSound(null, pos.toMinecraft(), soundEvent.get(), category.get());
    }

    public MCServer getMCServer() {
        return MCServer.of(getServer());
    }

    public boolean isChunkLoaded(BlockPos pos) {
        return WorldAccessUtil.isChunkLoaded(getRaw(), pos.toMinecraft());
    }

    public <T extends Entity> List<T> getEntitiesByClass(Class<T> entityClass, Box box, Predicate<? super T> predicate) {
        return WorldAccessUtil.getEntitiesByClass(getRaw(), entityClass, box, predicate);
    }

    public <T extends Entity> List<T> getEntitiesByType(EntityType<T> entityType, Box box, Predicate<? super Entity> predicate) {
        return WorldAccessUtil.getEntitiesByType(getRaw(), entityType, box, predicate);
    }

    public List<?> getEntitiesByType(EntityTypeWrapper entityType, Box box, Predicate<? super Entity> predicate) {
        return WorldAccessUtil.getEntitiesByType(getRaw(), entityType.get(), box, predicate);
    }

    public <T extends Entity> List<T> getEntitiesByClass(Class<T> entityClass, Box box) {
        return getEntitiesByClass(entityClass, box, EntityPredicates.VALID_ENTITY);
    }

    public <T extends Entity> List<T> getEntitiesByType(EntityType<T> entityType, Box box) {
        return getEntitiesByType(entityType, box, EntityPredicates.VALID_ENTITY);
    }

    public boolean breakBlock(BlockPos pos, boolean drop, Player player) {
        return breakBlock(pos, drop, player.getEntity());
    }

    public List<EntityWrapper> getEntitiesByTypeM(EntityTypeWrapper entityType, Box box, Predicate<? super EntityWrapper> predicate) {
        return getEntitiesByType(entityType.get(), box, (e) -> predicate.test(EntityWrapper.of(e)))
                .stream().map(EntityWrapper::of).collect(Collectors.toList());
    }

    public List<EntityWrapper> getEntitiesByTypeM(EntityTypeWrapper entityType, Box box) {
        return getEntitiesByType(entityType.get(), box).stream().map(EntityWrapper::of).collect(Collectors.toList());
    }

    public List<EntityWrapper> getEntitiesByClassM(Class<?> entityClass, Box box, Predicate<? super EntityWrapper> predicate) {
        return getEntitiesByClass((Class<? extends Entity>) entityClass, box, (e) -> predicate.test(EntityWrapper.of(e)))
                .stream().map(EntityWrapper::of).collect(Collectors.toList());
    }

    public List<EntityWrapper> getEntitiesByClassM(Class<?> entityClass, Box box) {
        return getEntitiesByClass((Class<? extends Entity>) entityClass, box).stream().map(EntityWrapper::of).collect(Collectors.toList());
    }

    public void spawnEntity(Entity entity) {
        getRaw().spawnEntity(entity);
    }

    public void spawnEntity(EntityWrapper entity) {
        spawnEntity(entity.get());
    }

    public List<EntityWrapper> getMobs(Box box) {
        return getEntitiesByClassM(LivingEntity.class, box, entity -> entity.get() instanceof MobEntity);
    }

    public List<EntityWrapper> getMonsters(Box box) {
        return getEntitiesByClassM(LivingEntity.class, box, entity -> entity.get() instanceof Monster);
    }

    public List<EntityWrapper> getAnimals(Box box) {
        return getEntitiesByClassM(LivingEntity.class, box, entity -> entity.get() instanceof AnimalEntity);
    }

    public int getLuminance(BlockPos pos) {
        return getBlockState(pos).getLuminance();
    }

    public List<EntityWrapper> getLivingEntities(Box box) {
        return getEntitiesByClassM(LivingEntity.class, box);
    }
}
