package net.pitan76.mcpitanlib.api.entity.attribute;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;

import java.util.function.Consumer;

public class CompatEntityAttributeInstance {
    private final net.minecraft.entity.attribute.EntityAttributeInstance raw;

    @Deprecated
    public CompatEntityAttributeInstance(net.minecraft.entity.attribute.EntityAttributeInstance instance) {
        this.raw = instance;
    }

    @Deprecated
    public net.minecraft.entity.attribute.EntityAttributeInstance raw() {
        return raw;
    }

    @Deprecated
    public static CompatEntityAttributeInstance of(net.minecraft.entity.attribute.EntityAttributeInstance instance) {
        return new CompatEntityAttributeInstance(instance);
    }

    public static CompatEntityAttributeInstance create(CompatEntityAttribute type, Consumer<CompatEntityAttributeInstance> updateCallback) {
        return of(new EntityAttributeInstance(type.raw(), modifier -> {
            if (updateCallback != null)
                updateCallback.accept(of(modifier));
        }));
    }

    public static CompatEntityAttributeInstance get(LivingEntity entity, CompatEntityAttribute attribute) {
        return new CompatEntityAttributeInstance(entity.getAttributeInstance(attribute.raw()));
    }

    public static CompatEntityAttributeInstance get(Player player, CompatEntityAttribute attribute) {
        return get(player.getEntity(), attribute);
    }

    // TODO: Implement this method properly
    public boolean hasModifier(CompatIdentifier id) {
        return false; //raw().hasModifier();
    }

    public double getValue() {
        return raw().getValue();
    }

    public void setBaseValue(double value) {
        raw().setBaseValue(value);
    }

    public void addPersistentModifier(CompatEntityAttributeModifier modifier) {
        raw().addPersistentModifier(modifier.raw());
    }

    // TODO: Implement this method properly
    public void removeModifier(CompatIdentifier id) {

    }

    public CompatEntityAttribute getAttribute() {
        return CompatEntityAttribute.of(raw().getAttribute());
    }

    public boolean isNull() {
        return raw() == null;
    }
}
