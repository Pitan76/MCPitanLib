package net.pitan76.mcpitanlib.api.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CustomData;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.entity.player.Abilities;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.stats.Stat;
import net.minecraft.stats.StatType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.resources.Identifier;
import net.minecraft.core.NonNullList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.Level;
import net.pitan76.mcpitanlib.api.entity.effect.CompatStatusEffect;
import net.pitan76.mcpitanlib.api.entity.effect.CompatStatusEffectInstance;
import net.pitan76.mcpitanlib.api.gui.ExtendedNamedScreenHandlerFactory;
import net.pitan76.mcpitanlib.api.gui.v2.ExtendedScreenHandlerFactory;
import net.pitan76.mcpitanlib.api.item.ArmorEquipmentType;
import net.pitan76.mcpitanlib.api.item.CompatFoodComponent;
import net.pitan76.mcpitanlib.api.sound.CompatSoundCategory;
import net.pitan76.mcpitanlib.api.sound.CompatSoundEvent;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.api.util.NbtUtil;
import net.pitan76.mcpitanlib.api.util.ScreenHandlerUtil;
import net.pitan76.mcpitanlib.api.util.TextUtil;
import net.pitan76.mcpitanlib.api.util.collection.ItemStackList;
import net.pitan76.mcpitanlib.api.util.entity.LivingEntityUtil;
import net.pitan76.mcpitanlib.api.util.inventory.CompatPlayerInventory;
import net.pitan76.mcpitanlib.api.util.inventory.ICompatInventory;
import net.pitan76.mcpitanlib.core.mc261.ExtendedMenuProvider;
import net.pitan76.mcpitanlib.core.player.ItemCooldown;
import net.pitan76.mcpitanlib.midohra.entity.EntityWrapper;
import net.pitan76.mcpitanlib.midohra.server.MCServer;
import net.pitan76.mcpitanlib.midohra.util.math.Vector3d;

import java.util.*;
import java.util.function.Consumer;

/*
PlayerEntity helper
 */
public class Player {
    private final net.minecraft.world.entity.player.Player entity;

    public net.minecraft.world.entity.player.Player getEntity() {
        return entity;
    }

    public net.minecraft.world.entity.player.Player getPlayerEntity() {
        return getEntity();
    }

    public Player(net.minecraft.world.entity.player.Player playerEntity) {
        this.entity = playerEntity;
    }

    /**
     * Get player inventory
     * @return PlayerInventory
     */
    public Inventory getInv() {
        return getEntity().getInventory();
    }

    /**
     * Alias of getInv()
     * @return PlayerInventory
     */
    public Inventory getInventory() {
        return getInv();
    }

    /**
     * Get armor's item stack list
     * @return DefaultedList<ItemStack>
     */
    public NonNullList<ItemStack> getArmor() {
        NonNullList<ItemStack> stacks = NonNullList.withSize(4, ItemStack.EMPTY);
        stacks.set(0, getInv().getItem(36));
        stacks.set(1, getInv().getItem(37));
        stacks.set(2, getInv().getItem(38));
        stacks.set(3, getInv().getItem(39));
        return stacks;
    }

    /**
     * Get main's item stack list
     * @return DefaultedList<ItemStack>
     */
    public NonNullList<ItemStack> getMain() {
        return getInv().getNonEquipmentItems();
    }

    /**
     * Get off hand's item stack list
     * @return DefaultedList<ItemStack>
     */
    public NonNullList<ItemStack> getOffHand() {
        NonNullList<ItemStack> stacks = NonNullList.withSize(1, ItemStack.EMPTY);
        stacks.set(0, getInv().getItem(Inventory.SLOT_OFFHAND));
        return stacks;
    }

    /**
     * Get select slot integer
     * @return int
     */
    public int getSelectSlot() {
        return getInv().getSelectedSlot();
    }

    /**
     * Get player inventory size
     * @return player inventory size
     */
    public int getInvSize() {
        return getInv().getContainerSize();
    }

    public OptionalInt openGuiScreen(MenuProvider factory) {
        return getEntity().openMenu(factory);
    }

    public OptionalInt openGuiScreen(Level world, BlockState state, BlockPos pos) {
        return openGuiScreen(state.getMenuProvider(world, pos));
    }

    public boolean isServerPlayerEntity() {
        return this.getEntity() instanceof ServerPlayer;
    }

    public void openExtendedMenu(MenuProvider provider, Consumer<FriendlyByteBuf> bufWriter) {
        if (isServerPlayerEntity())
            ScreenHandlerUtil.openExtendedMenu((ServerPlayer) this.getPlayerEntity(), provider, bufWriter);
    }

    public void openExtendedMenu(ExtendedMenuProvider provider) {
        if (isServerPlayerEntity())
            ScreenHandlerUtil.openExtendedMenu((ServerPlayer) this.getPlayerEntity(), provider);
    }

    public void openExtendedMenu(ExtendedNamedScreenHandlerFactory provider) {
        this.openExtendedMenu((ExtendedMenuProvider) provider);
    }

    public void openMenu(MenuProvider provider) {
        if (isServerPlayerEntity())
            ScreenHandlerUtil.openMenu((ServerPlayer) this.getPlayerEntity(), provider);
    }

    public void insertStack(ItemStack stack) {
        getInv().add(stack);
    }

    public void insertStack(int slot, ItemStack stack) {
        getInv().add(slot, stack);
    }

    public void offerOrDrop(ItemStack itemStack) {
        getInv().placeItemBackInInventory(itemStack);
    }

    public void giveStack(ItemStack stack) {
        getEntity().addItem(stack);
    }

    public String getName() {
        return getEntity().getName().getString();
    }

    public UUID getUUID() {
        return getEntity().getUUID();
    }

    public Abilities getAbilities() {
        return getEntity().getAbilities();
    }

    /**
     * Returns whether this player is in creative mode.
     */
    public boolean isCreative() {
        return getAbilities().instabuild;
    }

    public boolean isFlying() {
        return getAbilities().flying;
    }

    public boolean isInvulnerable() {
        return getAbilities().invulnerable;
    }

    public Level getWorld() {
        return getEntity().level();
    }

    public AbstractContainerMenu getCurrentScreenHandler() {
        return getEntity().containerMenu;
    }

    public boolean isSneaking() {
        return getEntity().isShiftKeyDown();
    }

    public ItemStack getCursorStack() {
        return getCurrentScreenHandler().getCarried();
    }

    public boolean isClient() {
        return getWorld().isClientSide();
    }
    public boolean isServer() {
        return !isClient();
    }

    public void readCustomDataFromNbt(CompoundTag nbt) {
        getEntity().setComponent(DataComponents.CUSTOM_DATA, CustomData.of(nbt));
    }

    public void writeCustomDataToNbt(CompoundTag nbt) {
        CompoundTag source = getEntity().get(DataComponents.CUSTOM_DATA).copyTag();
        NbtUtil.copyFrom(source, nbt);
    }

    public void sendMessage(Component text) {
        getEntity().sendSystemMessage(text);
    }

    public void sendActionBar(Component text) {
        getEntity().sendOverlayMessage(text);
    }

    public void equipStack(EquipmentSlot slot, ItemStack stack) {
        getEntity().setItemSlot(slot, stack);
    }

    public void dropStack(ItemStack stack, boolean throwRandomly, boolean retainOwnership) {
        getEntity().drop(stack, throwRandomly, retainOwnership);
    }

    public void dropStack(ItemStack stack, boolean retainOwnership) {
        dropStack(stack, false, retainOwnership);
    }

    public void dropStack(ItemStack stack) {
        dropStack(stack, false, false);
    }

    public BlockPos getBlockPos() {
        return getEntity().blockPosition();
    }

    public Vec3 getPos() {
        return getEntity().position();
    }

    public ItemStack getStackInHand(InteractionHand hand) {
        return this.getEntity().getItemInHand(hand);
    }

    public void heal(float amount) {
        this.getEntity().heal(amount);
    }

    public float getYaw() {
        return this.getEntity().getYRot();
    }

    public float getPitch() {
        return this.getEntity().getXRot();
    }

    public void playSound(SoundEvent event, SoundSource category, float volume, float pitch) {
        if (isServerPlayerEntity()) {
            Optional<ServerPlayer> player = getServerPlayer();
            if (player.isPresent()) {
                player.get().playSound(event, volume, pitch);
                return;
            }
        }

        playSound(event, volume, pitch);
    }

    public void playSound(SoundEvent event, float volume, float pitch) {
        if (isServerPlayerEntity()) {
            Optional<ServerPlayer> player = getServerPlayer();
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

    public ItemCooldowns getItemCooldownManager() {
        return getEntity().getCooldowns();
    }

    public void incrementStat(Stat<?> stat) {
        getEntity().awardStat(stat);
    }

    public <T> void incrementStat(StatType<T> type, T object) {
        getEntity().awardStat(type.get(object));
    }

    public void incrementStat(Identifier id) {
        getEntity().awardStat(id);
    }

    public void incrementStat(CompatIdentifier id) {
        getEntity().awardStat(id.toMinecraft());
    }

    public void teleport(double x, double y, double z) {
        getEntity().randomTeleport(x, y, z, false);
    }

    public ItemStack getMainHandStack() {
        return getStackInHand(InteractionHand.MAIN_HAND);
    }

    public ItemStack getOffHandStack() {
        return getStackInHand(InteractionHand.OFF_HAND);
    }

    public Direction getHorizontalFacing() {
        return getEntity().getDirection();
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
        return getEntity() instanceof ServerPlayer;
    }

    public Optional<ServerPlayer> getServerPlayer() {
        if (isServerPlayer())
            return Optional.of((ServerPlayer) getEntity());

        return Optional.empty();
    }

    @Environment(EnvType.CLIENT)
    public Optional<net.minecraft.client.player.LocalPlayer> getClientPlayer() {
        return ClientPlayerHolder.get(getEntity());
    }

    /**
     * クライアント専用クラス(LocalPlayer)への参照をPlayer本体から切り離すためのホルダー。
     * Player本体のバイトコードが直接参照していると、専用サーバーでPlayerクラスの検証時に
     * NoClassDefFoundErrorになることがある。
     */
    @Environment(EnvType.CLIENT)
    private static class ClientPlayerHolder {
        private static Optional<net.minecraft.client.player.LocalPlayer> get(net.minecraft.world.entity.player.Player entity) {
            if (entity instanceof net.minecraft.client.player.LocalPlayer)
                return Optional.of((net.minecraft.client.player.LocalPlayer) entity);

            return Optional.empty();
        }
    }

    public void setVelocity(double x, double y, double z) {
        getEntity().setDeltaMovement(x, y, z);
    }

    public void setVelocity(Vec3 velocity) {
        getEntity().setDeltaMovement(velocity);
    }

    public Vec3 getVelocity() {
        return getEntity().getDeltaMovement();
    }

    public Optional<ServerGamePacketListenerImpl> getNetworkHandler() {
        Optional<ServerPlayer> player = getServerPlayer();
        return player.map(sp -> sp.connection);
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
        getEntity().addEffect(effect.getInstance());
    }

    public void removeStatusEffect(CompatStatusEffect effect) {
        getEntity().removeEffect(effect.getEntry(getWorld()));
    }

    public List<CompatStatusEffectInstance> getStatusEffects() {
        List<CompatStatusEffectInstance> compatEffects = new ArrayList<>();

        for (MobEffectInstance effect : getEntity().getActiveEffects()) {
            compatEffects.add(new CompatStatusEffectInstance(effect));
        }

        return compatEffects;
    }

    public void addExperience(int experience) {
        getEntity().giveExperiencePoints(experience);
    }

    public int getExperienceLevel() {
        return getEntity().experienceLevel;
    }

    public void addExperienceLevels(int levels) {
        getEntity().giveExperienceLevels(levels);
    }

    public void setExperienceLevel(int level) {
        getEntity().experienceLevel = level;
    }

    public void addScore(int score) {
        getEntity().increaseScore(score);
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

    public boolean isSwimming() {
        return getEntity().isSwimming();
    }

    public void setStackInHand(InteractionHand hand, ItemStack stack) {
        getEntity().setItemInHand(hand, stack);
    }

    public void setStackInHand(InteractionHand hand, net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        setStackInHand(hand, stack.toMinecraft());
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getMidohraStackInHand(InteractionHand hand) {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getStackInHand(hand));
    }

    public InteractionHand getActiveHand() {
        return getEntity().getUsedItemHand();
    }

    public float getBlockBreakingSpeed(BlockState state) {
        return getEntity().getDestroySpeed(state);
    }

    public boolean canHarvest(BlockState state) {
        return getEntity().hasCorrectToolForDrops(state);
    }

    public net.pitan76.mcpitanlib.midohra.world.World getMidohraWorld() {
        return net.pitan76.mcpitanlib.midohra.world.World.of(getWorld());
    }

    public void eatFood(ItemStack stack, CompatFoodComponent foodComponent) {
        getEntity().getFoodData().eat(foodComponent.build());
    }

    public void sendMessage(String message) {
        sendMessage(TextUtil.of(message));
    }

    public void sendActionBar(String message) {
        sendActionBar(TextUtil.of(message));
    }

    public void sendMessagef(String format, Object... args) {
        sendMessage(TextUtil.of(String.format(format, args)));
    }

    public void sendMessage(TextComponent textComponent) {
        sendMessage(textComponent.getText());
    }

    public void sendActionBar(TextComponent textComponent) {
        sendActionBar(textComponent.getText());
    }

    public void openExtendedMenu(ExtendedScreenHandlerFactory provider) {
        this.openExtendedMenu((ExtendedMenuProvider) provider);
    }

    public void offerOrDrop(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        offerOrDrop(stack.toMinecraft());
    }

    public void insertStack(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        insertStack(stack.toMinecraft());
    }

    public void insertStack(int slot, net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        insertStack(slot, stack.toMinecraft());
    }

    public void giveStack(net.pitan76.mcpitanlib.midohra.item.ItemStack stack) {
        giveStack(stack.toMinecraft());
    }

    public Vector3d getPosM() {
        return Vector3d.of(getPos());
    }

    public net.pitan76.mcpitanlib.midohra.util.math.BlockPos getBlockPosM() {
        return net.pitan76.mcpitanlib.midohra.util.math.BlockPos.of(getBlockPos());
    }

    public ItemStack getEquippedStack(ArmorEquipmentType type) {
        return LivingEntityUtil.getEquippedStack(getEntity(), type.getSlot());
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getEquippedStackM(ArmorEquipmentType type) {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getEquippedStack(type));
    }

    public ItemStackList getMainAsM() {
        return ItemStackList.of(getMain());
    }

    public ItemStackList getOffHandAsM() {
        return ItemStackList.of(getOffHand());
    }

    public ItemStackList getArmorAsM() {
        return ItemStackList.of(getArmor());
    }

    public ICompatInventory getInventoryAsM() {
        return new CompatPlayerInventory(getInv());
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getMainHandStackAsM() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getMainHandStack());
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getOffHandStackAsM() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getOffHandStack());
    }

    public net.pitan76.mcpitanlib.midohra.util.math.Direction getHorizontalFacingM() {
        return net.pitan76.mcpitanlib.midohra.util.math.Direction.of(getHorizontalFacing());
    }

    public net.pitan76.mcpitanlib.midohra.item.ItemStack getCursorStackAsM() {
        return net.pitan76.mcpitanlib.midohra.item.ItemStack.of(getCursorStack());
    }

    public EntityWrapper wrap() {
        return EntityWrapper.of(getEntity());
    }

    public MCServer getServer() {
        return getMidohraWorld().getMCServer();
    }

    public Optional<net.pitan76.mcpitanlib.midohra.item.ItemStack> getCurrentHandItemM() {
        return getCurrentHandItem().map(net.pitan76.mcpitanlib.midohra.item.ItemStack::of);
    }
}