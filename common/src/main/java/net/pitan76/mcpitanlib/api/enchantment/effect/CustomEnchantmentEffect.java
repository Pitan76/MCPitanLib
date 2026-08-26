package net.pitan76.mcpitanlib.api.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

/**
 * JSONからは {@code {"type": "mcpitanlib:custom", "id": "<ハンドラのID>"}} として参照される効果。
 * <p>
 * エンチャント本体はデータパックレジストリなのでコードから登録できないが、
 * 効果の型は静的レジストリなので、この1種類だけ登録しておけば
 * 中身のIDでJava側のハンドラを引ける。
 */
public class CustomEnchantmentEffect implements EnchantmentEntityEffect {

    public static final MapCodec<CustomEnchantmentEffect> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Identifier.CODEC.fieldOf("id").forGetter(effect -> effect.id)
    ).apply(instance, CustomEnchantmentEffect::new));

    public final Identifier id;

    public CustomEnchantmentEffect(Identifier id) {
        this.id = id;
    }

    @Override
    public void apply(ServerLevel world, int level, EnchantedItemInUse context, Entity target, Vec3 pos) {
        EnchantmentEffectHandler handler = EnchantmentEffects.get(id);
        if (handler == null) return;

        handler.apply(new EnchantmentEffectEvent(world, level, context, target, pos));
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
