package net.pitan76.mcpitanlib.api.entity;

import net.minecraft.world.entity.player.Abilities;

/**
 * Player Abilities Wrapper
 */
public class CompatPlayerAbilities {

    private final net.minecraft.world.entity.player.Player player;

    protected CompatPlayerAbilities(net.minecraft.world.entity.player.Player player) {
        this.player = player;
    }

    public static CompatPlayerAbilities of(net.minecraft.world.entity.player.Player player) {
        return new CompatPlayerAbilities(player);
    }

    public static CompatPlayerAbilities of(Player player) {
        return new CompatPlayerAbilities(player.getEntity());
    }

    public net.minecraft.world.entity.player.Player getPlayer() {
        return player;
    }

    @Deprecated
    public Abilities getAbilities() {
        return player.getAbilities();
    }

    public boolean isCreativeMode() {
        return player.isCreative();
    }

    public boolean isInvulnerable() {
        return getAbilities().invulnerable;
    }

    public void setInvulnerable(boolean invulnerable) {
        getAbilities().invulnerable = invulnerable;
    }

    public boolean allowFlying() {
        return getAbilities().mayfly;
    }

    public void setAllowFlying(boolean allowFlying) {
        getAbilities().mayfly = allowFlying;
    }

    public boolean isFlying() {
        return getAbilities().flying;
    }

    public void setFlying(boolean flying) {
        getAbilities().flying = flying;
    }

    public boolean canModifyWorld() {
        return getAbilities().mayBuild;
    }

    public void setCanModifyWorld(boolean allowModifyWorld) {
        getAbilities().mayBuild = allowModifyWorld;
    }

    public float getFlySpeed() {
        return getAbilities().getFlyingSpeed();
    }

    public void setFlySpeed(float flySpeed) {
        getAbilities().setFlyingSpeed(flySpeed);
    }

    public float getWalkSpeed() {
        return getAbilities().getWalkingSpeed();
    }

    public void setWalkSpeed(float walkSpeed) {
        getAbilities().setWalkingSpeed(walkSpeed);
    }

    /**
     * 変更した能力を同期する。
     */
    public void sync() {
        player.onUpdateAbilities();
    }
}
