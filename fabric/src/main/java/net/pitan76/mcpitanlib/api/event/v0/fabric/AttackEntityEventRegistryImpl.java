package net.pitan76.mcpitanlib.api.event.v0.fabric;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.v0.AttackEntityEventRegistry;

public class AttackEntityEventRegistryImpl {
    public static void register(final AttackEntityEventRegistry.AttackEntity attackEntity) {
        AttackEntityCallback.EVENT.register(new AttackEntityCallback() {
            @Override
            public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult hitResult) {
                return attackEntity.attack(player, world, entity, hand, hitResult);
            }
        });
    }
}
