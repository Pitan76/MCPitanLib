package net.pitan76.mcpitanlib.api.entity;

import dev.architectury.registry.menu.ExtendedMenuProvider;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.client.network.ClientPlayerEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.stat.Stat;
import net.minecraft.stat.StatType;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.entity.effect.CompatStatusEffect;
import net.pitan76.mcpitanlib.api.entity.effect.CompatStatusEffectInstance;
import net.pitan76.mcpitanlib.api.gui.ExtendedNamedScreenHandlerFactory;
import net.pitan76.mcpitanlib.api.item.CompatFoodComponent;
import net.pitan76.mcpitanlib.api.sound.CompatSoundCategory;
import net.pitan76.mcpitanlib.api.sound.CompatSoundEvent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.ScreenHandlerUtil;
import net.pitan76.mcpitanlib.core.player.ItemCooldown;

import java.util.*;
import java.util.function.Consumer;

/*
PlayerEntity helper
 */
public class Player {
    private final PlayerEntity entity;

    public PlayerEntity getEntity() {
        return entity;
    }

    public PlayerEntity getPlayerEntity() {
        return getEntity();
    }

    public Player(PlayerEntity playerEntity) {
        this.entity = playerEntity;
    }

    /**
     * Get player inventory
     * @return PlayerInventory
     */
    public PlayerInventory getInv() {
        return getEntity().getInventory();
    }

    /**
     * Alias of getInv()
     * @return PlayerInventory
     */
    public PlayerInventory getInventory() {
        return getInv();
    }

    /**
     * Get armor's item stack list
     * @return DefaultedList<ItemStack>
     */
    public DefaultedList<ItemStack> getArmor() {
        return getInv().armor;
    }

    /**
     * Get main's item stack list
     * @return DefaultedList<ItemStack>
     */
    public DefaultedList<ItemStack> getMain() {
        return getInv().main;
    }

    /**
     * Get off hand's item stack list
     * @return DefaultedList<ItemStack>
     */
    public DefaultedList<ItemStack> getOffHand() {
        return getInv().offHand;
    }

    /**
     * Get select slot integer
     * @return int
     */
    public int getSelectSlot() {
        return getInv().selectedSlot;
    }

    /**
     * Get player inventory size
     * @return player inventory size
     */
    public int getInvSize() {
        return getInv().size();
    }

    public OptionalInt openGuiScreen(NamedScreenHandlerFactory factory) {
        return getEntity().openHandledScreen(factory);
    }

    public OptionalInt openGuiScreen(World world, BlockState state, BlockPos pos) {
        return openGuiScreen(state.createScreenHandlerFactory(world, pos));
    }

    public boolean isServerPlayerEntity() {
        return this.getEntity() instanceof ServerPlayerEntity;
    }

    public void openExtendedMenu(NamedScreenHandlerFactory provider, Consumer<PacketByteBuf> bufWriter) {
        if (isServerPlayerEntity())
            ScreenHandlerUtil.openExtendedMenu((ServerPlayerEntity) this.getPlayerEntity(), provider, bufWriter);
    }

    public void openExtendedMenu(ExtendedMenuProvider provider) {
        if (isServerPlayerEntity())
            ScreenHandlerUtil.openExtendedMenu((ServerPlayerEntity) this.getPlayerEntity(), provider);
    }

    public void openExtendedMenu(ExtendedNamedScreenHandlerFactory provider) {
        this.openExtendedMenu((ExtendedMenuProvider) provider);
    }

    public void openMenu(NamedScreenHandlerFactory provider) {
        if (isServerPlayerEntity())
            ScreenHandlerUtil.openMenu((ServerPlayerEntity) this.getPlayerEntity(), provider);
    }

    public void insertStack(ItemStack stack) {
        getInv().insertStack(stack);
    }

    public void insertStack(int slot, ItemStack stack) {
        getInv().insertStack(slot, stack);
    }

    public void offerOrDrop(ItemStack itemStack) {
        getInv().offerOrDrop(itemStack);
    }

    public void giveStack(ItemStack stack) {
        getEntity().giveItemStack(stack);
    }

    public String getName() {
        return getEntity().getName().getString();
    }

    public UUID getUUID() {
        return getEntity().getUuid();
    }

    public PlayerAbilities getAbilities() {
        return getEntity().getAbilities();
    }

    /**
     * Returns whether this player is in creative mode.
     */
    public boolean isCreative() {
        return getAbilities().creativeMode;
    }

    public boolean isFlying() {
        return getAbilities().flying;
    }

    public boolean isInvulnerable() {
        return getAbilities().invulnerable;
    }

    public World getWorld() {
        return getEntity().getWorld();
    }

    public ScreenHandler getCurrentScreenHandler() {
        return getEntity().currentScreenHandler;
    }

    public boolean isSneaking() {
        return getEntity().isSneaking();
    }

    public ItemStack getCursorStack() {
        return getCurrentScreenHandler().getCursorStack();
    }

    public boolean isClient() {
        return getWorld().isClient();
    }
    public boolean isServer() {
        return !isClient();
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        getEntity().readCustomDataFromNbt(nbt);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        getEntity().writeCustomDataToNbt(nbt);
    }

    public void sendMessage(Text text) {
        getEntity().sendMessage(text, false);
    }

    public void sendActionBar(Text text) {
        getEntity().sendMessage(text, true);
    }

    public void equipStack(EquipmentSlot slot, ItemStack stack) {
        getEntity().equipStack(slot, stack);
    }

    public void dropStack(ItemStack stack, boolean throwRandomly, boolean retainOwnership) {
        getEntity().dropItem(stack, throwRandomly, retainOwnership);
    }

    public void dropStack(ItemStack stack, boolean retainOwnership) {
        dropStack(stack, false, retainOwnership);
    }

    public void dropStack(ItemStack stack) {
        dropStack(stack, false, false);
    }

    public BlockPos getBlockPos() {
        return getEntity().getBlockPos();
    }

    public Vec3d getPos() {
        return getEntity().getPos();
    }

    public ItemStack getStackInHand(Hand hand) {
        return this.getEntity().getStackInHand(hand);
    }

    public void heal(float amount) {
        this.getEntity().heal(amount);
    }

    public float getYaw() {
        return this.getEntity().getYaw();
    }

    public float getPitch() {
        return this.getEntity().getPitch();
    }

    public void playSound(SoundEvent event, SoundCategory category, float volume, float pitch) {
        if (isServerPlayerEntity()) {
            Optional<ServerPlayerEntity> player = getServerPlayer();
            if (player.isPresent()) {
                player.get().playSound(event, category, volume, pitch);
                return;
            }
        }

        playSound(event, volume, pitch);
    }

    public void playSound(SoundEvent event, float volume, float pitch) {
        if (isServerPlayerEntity()) {
            Optional<ServerPlayerEntity> player = getServerPlayer();
            if (player.isPresent()) {
                player.get().playSound(event, volume, pitch);
                return;
            }
        }

        getEntity().playSound(event, volume, pitch);
    }

    public void playSound(CompatSoundEvent event, CompatSoundCategory category, float volume, float pitch) {
        playSound(event.get(), category.get(), volume, pitch);
    }

    public void playSound(CompatSoundEvent event, float volume, float pitch) {
        playSound(event.get(), volume, pitch);
    }

    public ItemCooldown itemCooldown = new ItemCooldown(this);

    public ItemCooldown getItemCooldown() {
        return itemCooldown;
    }

    public ItemCooldownManager getItemCooldownManager() {
        return getEntity().getItemCooldownManager();
    }

    public void incrementStat(Stat<?> stat) {
        getEntity().incrementStat(stat);
    }

    public <T> void incrementStat(StatType<T> type, T object) {
        getEntity().incrementStat(type.getOrCreateStat(object));
    }

    public void incrementStat(Identifier id) {
        getEntity().incrementStat(id);
    }

    public void incrementStat(CompatIdentifier id) {
        getEntity().incrementStat(id.toMinecraft());
    }

    public void teleport(double x, double y, double z) {
        getEntity().teleport(x, y, z);
    }

    public ItemStack getMainHandStack() {
        return getStackInHand(Hand.MAIN_HAND);
    }

    public ItemStack getOffHandStack() {
        return getStackInHand(Hand.OFF_HAND);
    }

    public Direction getHorizontalFacing() {
        return getEntity().getHorizontalFacing();
    }

    public void eatFood(World world, ItemStack stack, CompatFoodComponent foodComponentBuilder) {
        getEntity().eatFood(world, stack);
    }

    public double getX() {
        return getEntity().getX();
    }

    public double getY() {
        return getEntity().getY();
    }

    public double getZ() {
        return getEntity().getZ();
    }

    public boolean isServerPlayer() {
        return getEntity() instanceof ServerPlayerEntity;
    }

    public Optional<ServerPlayerEntity> getServerPlayer() {
        if (isServerPlayer())
            return Optional.of((ServerPlayerEntity) getEntity());

        return Optional.empty();
    }

    @Environment(EnvType.CLIENT)
    public Optional<ClientPlayerEntity> getClientPlayer() {
        if (getEntity() instanceof ClientPlayerEntity)
            return Optional.of((ClientPlayerEntity) getEntity());

        return Optional.empty();
    }

    public void setVelocity(double x, double y, double z) {
        getEntity().setVelocity(x, y, z);
    }

    public void setVelocity(Vec3d velocity) {
        getEntity().setVelocity(velocity);
    }

    public Vec3d getVelocity() {
        return getEntity().getVelocity();
    }

    public Optional<ServerPlayNetworkHandler> getNetworkHandler() {
        Optional<ServerPlayerEntity> player = getServerPlayer();
        return player.map(sp -> sp.networkHandler);
    }

    public boolean hasNetworkHandler() {
        return getNetworkHandler().isPresent();
    }

    public boolean isSpectator() {
        return getEntity().isSpectator();
    }

    /**
     * Returns the current {@link ItemStack} in the {@link Player}'s hand, or offhand if the
     * main hand is empty.
     *
     * @return {@code ItemStack} that the {@link Player} is holding. Can be {@link null}.
     */
    public Optional<ItemStack> getCurrentHandItem() {
        boolean playerIsHoldingInMainHand = !getMainHandStack().isEmpty();
        if (playerIsHoldingInMainHand)
            return Optional.ofNullable(getMainHandStack());

        boolean playerIsHoldingInOffHand = !getOffHandStack().isEmpty();

        if (playerIsHoldingInOffHand)
            return Optional.ofNullable(getOffHandStack());

        return Optional.empty();
    }

    public void addStatusEffect(CompatStatusEffectInstance effect) {
        getEntity().addStatusEffect(effect.getInstance());
    }

    public void removeStatusEffect(CompatStatusEffect effect) {
        getEntity().removeStatusEffect(effect.getStatusEffect(getWorld()));
    }

    public List<CompatStatusEffectInstance> getStatusEffects() {
        List<CompatStatusEffectInstance> compatEffects = new ArrayList<>();

        for (StatusEffectInstance effect : getEntity().getStatusEffects()) {
            compatEffects.add(new CompatStatusEffectInstance(effect));
        }

        return compatEffects;
    }

    public void addExperience(int experience) {
        getEntity().addExperience(experience);
    }

    public int getExperienceLevel() {
        return getEntity().experienceLevel;
    }

    public void addExperienceLevels(int levels) {
        getEntity().addExperienceLevels(levels);
    }

    public void setExperienceLevel(int level) {
        getEntity().experienceLevel = level;
    }

    public void addScore(int score) {
        getEntity().addScore(score);
    }

    public int getScore() {
        return getEntity().getScore();
    }

    public void setScore(int score) {
        getEntity().setScore(score);
    }

    public int getTotalExperience() {
        return getEntity().totalExperience;
    }

    public void setTotalExperience(int experience) {
        getEntity().totalExperience = experience;
    }

    public boolean isFallFlying() {
        return getEntity().isFallFlying();
    }

    public boolean isSwimming() {
        return getEntity().isSwimming();
    }

    public void startFallFlying() {
        getEntity().startFallFlying();
    }

    public void stopFallFlying() {
        getEntity().stopFallFlying();
    }

    @Deprecated
    public int getFallFlyingTicks() {
        return 0;
    }

    public boolean checkFallFlying() {
        return getEntity().checkFallFlying();
    }

    public void setStackInHand(Hand hand, ItemStack stack) {
        getEntity().setStackInHand(hand, stack);
    }
}