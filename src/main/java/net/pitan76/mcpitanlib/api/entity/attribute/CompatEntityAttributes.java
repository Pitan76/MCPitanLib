package net.pitan76.mcpitanlib.api.entity.attribute;

import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.core.Holder;

public class CompatEntityAttributes {

    public static final CompatEntityAttribute ARMOR = of(Attributes.ARMOR);
    public static final CompatEntityAttribute ARMOR_TOUGHNESS = of(Attributes.ARMOR_TOUGHNESS);
    public static final CompatEntityAttribute ATTACK_DAMAGE = of(Attributes.ATTACK_DAMAGE);
    public static final CompatEntityAttribute ATTACK_KNOCKBACK = of(Attributes.ATTACK_KNOCKBACK);
    public static final CompatEntityAttribute ATTACK_SPEED = of(Attributes.ATTACK_SPEED);
    public static final CompatEntityAttribute FOLLOW_RANGE = of(Attributes.FOLLOW_RANGE);
    public static final CompatEntityAttribute KNOCKBACK_RESISTANCE = of(Attributes.KNOCKBACK_RESISTANCE);
    public static final CompatEntityAttribute LUCK = of(Attributes.LUCK);
    public static final CompatEntityAttribute MOVEMENT_SPEED = of(Attributes.MOVEMENT_SPEED);
    public static final CompatEntityAttribute MAX_HEALTH = of(Attributes.MAX_HEALTH);
    public static final CompatEntityAttribute JUMP_STRENGTH = of(Attributes.JUMP_STRENGTH);
    public static final CompatEntityAttribute GRAVITY = of(Attributes.GRAVITY);
    public static final CompatEntityAttribute FLYING_SPEED = of(Attributes.FLYING_SPEED);
    public static final CompatEntityAttribute BLOCK_BREAK_SPEED = of(Attributes.BLOCK_BREAK_SPEED);
    public static final CompatEntityAttribute BLOCK_INTERACTION_RANGE = of(Attributes.BLOCK_INTERACTION_RANGE);

    public static CompatEntityAttribute of(Holder<Attribute> attribute) {
        return CompatEntityAttribute.of(attribute);
    }
}
