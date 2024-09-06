package net.pitan76.mcpitanlib.api.entity.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.util.StatusEffectUtil;
import org.jetbrains.annotations.Nullable;

public class CompatStatusEffect {
    private final StatusEffect statusEffect;

    @Deprecated
    public CompatStatusEffect(StatusEffect statusEffect) {
        this.statusEffect = statusEffect;
    }

    public CompatStatusEffect of(Identifier identifier) {
        return StatusEffectUtil.getStatusEffect(identifier);
    }

    public Identifier getId() {
        return Registry.STATUS_EFFECT.getId(statusEffect);
    }

    public String toString() {
        return getId().toString();
    }

    public boolean equals(Object obj) {
        if (obj instanceof CompatStatusEffect) {
            return ((CompatStatusEffect) obj).getId().equals(getId());
        }
        return false;
    }

    public StatusEffect getStatusEffect(@Nullable World world) {
        return statusEffect;
    }
}
