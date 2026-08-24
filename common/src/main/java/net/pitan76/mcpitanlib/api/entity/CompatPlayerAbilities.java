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
        return player.getAbilities().creativeMode;
    }

    public boolean isInvulnerable() {
        return player.getAbilities().invulnerable;
    }

    public void setInvulnerable(boolean invulnerable) {
        player.getAbilities().invulnerable = invulnerable;
    }

    public boolean allowFlying() {
        return player.getAbilities().allowFlying;
    }

    public void setAllowFlying(boolean allowFlying) {
        player.getAbilities().allowFlying = allowFlying;
    }

    public boolean isFlying() {
        return player.getAbilities().flying;
    }

    public void setFlying(boolean flying) {
        player.getAbilities().flying = flying;
    }

    public boolean canModifyWorld() {
        return player.getAbilities().allowModifyWorld;
    }

    public void setCanModifyWorld(boolean allowModifyWorld) {
        player.getAbilities().allowModifyWorld = allowModifyWorld;
    }

    public float getFlySpeed() {
        return player.getAbilities().getFlySpeed();
    }

    public void setFlySpeed(float flySpeed) {
        player.getAbilities().setFlySpeed(flySpeed);
    }

    public float getWalkSpeed() {
        return player.getAbilities().getWalkSpeed();
    }

    public void setWalkSpeed(float walkSpeed) {
        player.getAbilities().setWalkSpeed(walkSpeed);
    }

    /**
     * 変更した能力を同期する。
     */
    public void sync() {
        player.sendAbilitiesUpdate();
    }
}
