package net.pitan76.mcpitanlib.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.entity.PlayerTickEvent;
import net.pitan76.mcpitanlib.api.event.item.BonusAttackDamageArgs;
import net.pitan76.mcpitanlib.api.event.v2.EntityEventRegistry;
import net.pitan76.mcpitanlib.api.event.v2.listener.PlayerTickTask;
import net.pitan76.mcpitanlib.api.item.ExtendItemProvider;
import net.pitan76.mcpitanlib.api.util.DamageSourceUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {

    @ModifyVariable(
            method = "attack",
            at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/enchantment/EnchantmentHelper;getAttackDamage(Lnet/minecraft/item/ItemStack;Lnet/minecraft/entity/EntityGroup;)F"),
            ordinal = 1
    )
    private float mcpitanlib$getBonusAttackDamage(float bonusDamage, Entity target) {
        PlayerEntity player = (PlayerEntity) (Object) this;
        ItemStack stack = player.getMainHandStack();
        Item item = stack.getItem();

        if (!(item instanceof ExtendItemProvider)) return bonusDamage;

        ExtendItemProvider provider = (ExtendItemProvider) item;
        ExtendItemProvider.Options options = new ExtendItemProvider.Options();
        float returnValue = provider.getBonusAttackDamage(
                new BonusAttackDamageArgs(target, bonusDamage, DamageSourceUtil.playerAttack(new Player(player)), stack), options);

        if (!options.cancel) return bonusDamage;

        return bonusDamage + returnValue;
    }

    @Inject(method = "tick", at = @At("TAIL"))
    private void mcpitanlib$tick(CallbackInfo ci) {
        if (EntityEventRegistry.PLAYER_TICK.isEmpty()) return;

        PlayerTickEvent event = new PlayerTickEvent((PlayerEntity) (Object) this);
        int maxPriority = EntityEventRegistry.PLAYER_TICK.getMaxPriority();
        for (int p = maxPriority; p >= 0; p--) {
            for (PlayerTickTask listener : EntityEventRegistry.PLAYER_TICK.getListenersAsList(p)) {
                listener.playerTick(event);
            }
        }
    }
}
