package net.pitan76.mcpitanlib.api.entity.attribute;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.entry.RegistryEntry;

public class CompatEntityAttributes {

    public static final CompatEntityAttribute ARMOR = of(EntityAttributes.GENERIC_ARMOR);
    public static final CompatEntityAttribute ARMOR_TOUGHNESS = of(EntityAttributes.GENERIC_ARMOR_TOUGHNESS);
    public static final CompatEntityAttribute ATTACK_DAMAGE = of(EntityAttributes.GENERIC_ATTACK_DAMAGE);
    public static final CompatEntityAttribute ATTACK_KNOCKBACK = of(EntityAttributes.GENERIC_ATTACK_KNOCKBACK);
    public static final CompatEntityAttribute ATTACK_SPEED = of(EntityAttributes.GENERIC_ATTACK_SPEED);
    public static final CompatEntityAttribute FOLLOW_RANGE = of(EntityAttributes.GENERIC_FOLLOW_RANGE);
    public static final CompatEntityAttribute KNOCKBACK_RESISTANCE = of(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE);
    public static final CompatEntityAttribute LUCK = of(EntityAttributes.GENERIC_LUCK);
    public static final CompatEntityAttribute MOVEMENT_SPEED = of(EntityAttributes.GENERIC_MOVEMENT_SPEED);
    public static final CompatEntityAttribute MAX_HEALTH = of(EntityAttributes.GENERIC_MAX_HEALTH);
    public static final CompatEntityAttribute JUMP_STRENGTH = of(EntityAttributes.GENERIC_JUMP_STRENGTH);
    public static final CompatEntityAttribute GRAVITY = of(EntityAttributes.GENERIC_GRAVITY);
    public static final CompatEntityAttribute FLYING_SPEED = of(EntityAttributes.GENERIC_FLYING_SPEED);
    public static final CompatEntityAttribute BLOCK_BREAK_SPEED = of(EntityAttributes.PLAYER_BLOCK_BREAK_SPEED);
    public static final CompatEntityAttribute BLOCK_INTERACTION_RANGE = of(EntityAttributes.PLAYER_BLOCK_INTERACTION_RANGE);

    public static CompatEntityAttribute of(RegistryEntry<EntityAttribute> attribute) {
        return CompatEntityAttribute.of(attribute);
    }
}
