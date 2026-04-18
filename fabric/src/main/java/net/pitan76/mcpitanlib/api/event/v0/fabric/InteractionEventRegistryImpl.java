package net.pitan76.mcpitanlib.api.event.v0.fabric;

import net.fabricmc.fabric.api.event.client.player.ClientPlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.client.player.ClientPreAttackCallback;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.pitan76.mcpitanlib.api.event.v0.InteractionEventRegistry.*;
import net.pitan76.mcpitanlib.api.event.v0.event.ClickBlockEvent;

public class InteractionEventRegistryImpl {
    public static void registerRightClickBlock(RightClickBlock rightClickBlock) {
        UseBlockCallback.EVENT.register((player, world, hand, result) -> rightClickBlock.click(new ClickBlockEvent(player, hand, result.getBlockPos(), result.getDirection())).toActionResult());
    }

    public static void registerLeftClickBlock(LeftClickBlock leftClickBlock) {
        AttackBlockCallback.EVENT.register((player, world, hand, pos, dir) -> leftClickBlock.click(new ClickBlockEvent(player, hand, pos, dir)).toActionResult());
    }

    public static void registerRightClickItem(RightClickItem rightClickItem) {
        UseItemCallback.EVENT.register((player, _, hand)
                -> rightClickItem.click2(player, hand));
    }

    public static void registerClientLeftClickAir(ClientLeftClickAir clientLeftClickAir) {
        ClientPreAttackCallback.EVENT.register((_, player, _) -> {
            clientLeftClickAir.click(player, player.getUsedItemHand());
            return false;
        });
    }

    public static void registerClientRightClickAir(ClientRightClickAir clientRightClickAir) {
        ClientPlayerBlockBreakEvents.AFTER.register((_, player, _, _)
                -> clientRightClickAir.click(player, player.getUsedItemHand()));
    }

    public static void registerInteractEntity(InteractEntity interactEntity) {
        UseEntityCallback.EVENT.register((player, _, hand, entity, _)
                -> interactEntity.interact(player, entity, hand));
    }
}
