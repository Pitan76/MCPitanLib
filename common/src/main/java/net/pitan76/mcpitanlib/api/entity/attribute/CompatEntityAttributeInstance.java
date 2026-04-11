package net.pitan76.mcpitanlib.api.entity.attribute;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

import java.util.function.Consumer;

public class CompatEntityAttributeInstance {
    private final net.minecraft.world.entity.ai.attributes.AttributeInstance raw;

    @Deprecated
    public CompatEntityAttributeInstance(net.minecraft.world.entity.ai.attributes.AttributeInstance instance) {
        this.raw = instance;
    }

    @Deprecated
    public net.minecraft.world.entity.ai.attributes.AttributeInstance raw() {
        return raw;
    }

    @Deprecated
    public static CompatEntityAttributeInstance of(net.minecraft.world.entity.ai.attributes.AttributeInstance instance) {
        return new CompatEntityAttributeInstance(instance);
    }

    public static CompatEntityAttributeInstance create(CompatEntityAttribute type, Consumer<CompatEntityAttributeInstance> updateCallback) {
        return of(new AttributeInstance(type.raw(), modifier -> {
            if (updateCallback != null)
                updateCallback.accept(of(modifier));
        }));
    }

    public static CompatEntityAttributeInstance get(LivingEntity entity, CompatEntityAttribute attribute) {
        return new CompatEntityAttributeInstance(entity.getAttribute(attribute.raw()));
    }

    public static CompatEntityAttributeInstance get(Player player, CompatEntityAttribute attribute) {
        return get(player.getEntity(), attribute);
    }

    public boolean hasModifier(CompatIdentifier id) {
        return raw().hasModifier(id.toMinecraft());
    }

    public double getValue() {
        return raw().getValue();
    }

    public void setBaseValue(double value) {
        raw().setBaseValue(value);
    }

    public void addPersistentModifier(CompatEntityAttributeModifier modifier) {
        raw().addPermanentModifier(modifier.raw());
    }

    public void removeModifier(CompatIdentifier id) {
        raw().removeModifier(id.toMinecraft());
    }

    public CompatEntityAttribute getAttribute() {
        return CompatEntityAttribute.of(raw().getAttribute());
    }

    public boolean isNull() {
        return raw() == null;
    }
}
