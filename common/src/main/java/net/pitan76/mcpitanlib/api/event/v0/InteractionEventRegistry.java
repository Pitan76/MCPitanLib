package net.pitan76.mcpitanlib.api.event.v0;

import net.fabricmc.fabric.api.event.client.player.ClientPlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.client.player.ClientPreAttackCallback;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.result.EventResult;
import net.pitan76.mcpitanlib.api.event.v0.event.ClickBlockEvent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;

public class InteractionEventRegistry {
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

    // ----

    public interface LeftClickBlock {
        EventResult click(ClickBlockEvent event);
    }

    public interface RightClickBlock {
        EventResult click(ClickBlockEvent event);
    }

    public interface RightClickItem {
        default InteractionResult click2(net.minecraft.world.entity.player.Player var1, InteractionHand var2) {
            return click(new Player(var1), var2).toActionResult();
        }

        CompatActionResult click(Player player, InteractionHand hand);
    }

    public interface ClientLeftClickAir {
        default void click(net.minecraft.world.entity.player.Player var1, InteractionHand var2) {
            click(new Player(var1), var2);
        }

        void click(Player player, InteractionHand hand);
    }

    public interface ClientRightClickAir {
        default void click(net.minecraft.world.entity.player.Player var1, InteractionHand var2) {
            click(new Player(var1), var2);
        }

        void click(Player player, InteractionHand hand);
    }

    public interface InteractEntity {
        @SuppressWarnings("deprecation")
        default InteractionResult interact(net.minecraft.world.entity.player.Player var1, Entity var2, InteractionHand var3) {
            return interact(new Player(var1), var2, var3).toActionResult();
        }

        CompatActionResult interact(Player player, Entity entity, InteractionHand hand);
    }
}
