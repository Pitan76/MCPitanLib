package net.pitan76.mcpitanlib.api.enchantment.effect;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;

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
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity target, Vec3d pos) {
        EnchantmentEffectHandler handler = EnchantmentEffects.get(id);
        if (handler == null) return;

        handler.apply(new EnchantmentEffectEvent(world, level, context, target, pos));
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
