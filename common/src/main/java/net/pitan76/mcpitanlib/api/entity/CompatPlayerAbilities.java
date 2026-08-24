package net.pitan76.mcpitanlib.api.entity;

import net.minecraft.entity.player.PlayerAbilities;
import net.minecraft.entity.player.PlayerEntity;

/**
 * Player Abilities Wrapper
 */
public class CompatPlayerAbilities {

    private final PlayerEntity player;

    protected CompatPlayerAbilities(PlayerEntity player) {
        this.player = player;
    }

    public static CompatPlayerAbilities of(PlayerEntity player) {
        return new CompatPlayerAbilities(player);
    }

    public static CompatPlayerAbilities of(Player player) {
        return new CompatPlayerAbilities(player.getEntity());
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    @Deprecated
    public PlayerAbilities getAbilities() {
        return player.getAbilities();
    }

    public boolean isCreativeMode() {
        return getAbilities().creativeMode;
    }

    public boolean isInvulnerable() {
        return getAbilities().invulnerable;
    }

    public void setInvulnerable(boolean invulnerable) {
        getAbilities().invulnerable = invulnerable;
    }

    public boolean allowFlying() {
        return getAbilities().allowFlying;
    }

    public void setAllowFlying(boolean allowFlying) {
        getAbilities().allowFlying = allowFlying;
    }

    public boolean isFlying() {
        return getAbilities().flying;
    }

    public void setFlying(boolean flying) {
        getAbilities().flying = flying;
    }

    public boolean canModifyWorld() {
        return getAbilities().allowModifyWorld;
    }

    public void setCanModifyWorld(boolean allowModifyWorld) {
        getAbilities().allowModifyWorld = allowModifyWorld;
    }

    public float getFlySpeed() {
        return getAbilities().getFlySpeed();
    }

    public void setFlySpeed(float flySpeed) {
        getAbilities().setFlySpeed(flySpeed);
    }

    public float getWalkSpeed() {
        return getAbilities().getWalkSpeed();
    }

    public void setWalkSpeed(float walkSpeed) {
        getAbilities().setWalkSpeed(walkSpeed);
    }

    /**
     * 変更した能力を同期する。
     */
    public void sync() {
        player.sendAbilitiesUpdate();
    }
}
