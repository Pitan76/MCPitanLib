package net.pitan76.mcpitanlib.api.entity.attribute;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.entry.RegistryEntry;

public class CompatEntityAttributes {

    public static final CompatEntityAttribute ARMOR = of(EntityAttributes.ARMOR);
    public static final CompatEntityAttribute ARMOR_TOUGHNESS = of(EntityAttributes.ARMOR_TOUGHNESS);
    public static final CompatEntityAttribute ATTACK_DAMAGE = of(EntityAttributes.ATTACK_DAMAGE);
    public static final CompatEntityAttribute ATTACK_KNOCKBACK = of(EntityAttributes.ATTACK_KNOCKBACK);
    public static final CompatEntityAttribute ATTACK_SPEED = of(EntityAttributes.ATTACK_SPEED);
    public static final CompatEntityAttribute FOLLOW_RANGE = of(EntityAttributes.FOLLOW_RANGE);
    public static final CompatEntityAttribute KNOCKBACK_RESISTANCE = of(EntityAttributes.KNOCKBACK_RESISTANCE);
    public static final CompatEntityAttribute LUCK = of(EntityAttributes.LUCK);
    public static final CompatEntityAttribute MOVEMENT_SPEED = of(EntityAttributes.MOVEMENT_SPEED);
    public static final CompatEntityAttribute MAX_HEALTH = of(EntityAttributes.MAX_HEALTH);
    public static final CompatEntityAttribute JUMP_STRENGTH = of(EntityAttributes.JUMP_STRENGTH);
    public static final CompatEntityAttribute GRAVITY = of(EntityAttributes.GRAVITY);
    public static final CompatEntityAttribute FLYING_SPEED = of(EntityAttributes.FLYING_SPEED);
    public static final CompatEntityAttribute BLOCK_BREAK_SPEED = of(EntityAttributes.BLOCK_BREAK_SPEED);
    public static final CompatEntityAttribute BLOCK_INTERACTION_RANGE = of(EntityAttributes.BLOCK_INTERACTION_RANGE);

    public static CompatEntityAttribute of(RegistryEntry<EntityAttribute> attribute) {
        return CompatEntityAttribute.of(attribute);
    }
}
