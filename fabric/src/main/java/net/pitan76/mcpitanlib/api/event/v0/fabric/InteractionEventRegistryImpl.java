package net.pitan76.mcpitanlib.api.event.v0.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.pitan76.mcpitanlib.api.event.v0.InteractionEventRegistry.*;
import net.pitan76.mcpitanlib.api.event.v0.event.ClickBlockEvent;

public class InteractionEventRegistryImpl {
    public static void registerRightClickBlock(RightClickBlock rightClickBlock) {
        UseBlockCallback.EVENT.register((player, world, hand, result) -> rightClickBlock.click(new ClickBlockEvent(player, hand, result.getBlockPos(), result.getSide())).toActionResult());
    }

    public static void registerLeftClickBlock(LeftClickBlock leftClickBlock) {
        AttackBlockCallback.EVENT.register((player, world, hand, pos, dir) -> leftClickBlock.click(new ClickBlockEvent(player, hand, pos, dir)).toActionResult());
    }

    public static void registerRightClickItem(RightClickItem rightClickItem) {
        UseItemCallback.EVENT.register((player, world, hand) -> rightClickItem.click2(player, hand));
    }

    @Environment(EnvType.CLIENT)
    public static void registerClientLeftClickAir(ClientLeftClickAir clientLeftClickAir) {
        ClientInteractionEventRegistry.registerClientLeftClickAir(clientLeftClickAir);
    }

    @Environment(EnvType.CLIENT)
    public static void registerClientRightClickAir(ClientRightClickAir clientRightClickAir) {
        ClientInteractionEventRegistry.registerClientRightClickAir(clientRightClickAir);
    }

    public static void registerInteractEntity(InteractEntity interactEntity) {
        UseEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> interactEntity.interact(player, entity, hand));
    }
}
