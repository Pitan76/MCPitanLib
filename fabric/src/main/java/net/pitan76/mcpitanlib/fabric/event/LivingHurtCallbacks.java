package net.pitan76.mcpitanlib.fabric.event;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Fabric APIの1.16.5版にはServerLivingEntityEvents.ALLOW_DAMAGEが無いため、
 * LivingEntityMixinから発火する自前のディスパッチャで代替する。
 */
public class LivingHurtCallbacks {
    private static final List<AllowDamage> HANDLERS = new CopyOnWriteArrayList<AllowDamage>();

    public static void register(AllowDamage handler) {
        HANDLERS.add(handler);
    }

    /**
     * @return falseならダメージをキャンセルする
     */
    public static boolean allowDamage(LivingEntity entity, DamageSource source, float amount) {
        for (AllowDamage handler : HANDLERS) {
            if (!handler.allowDamage(entity, source, amount)) return false;
        }

        return true;
    }

    public interface AllowDamage {
        boolean allowDamage(LivingEntity entity, DamageSource source, float amount);
    }
}
