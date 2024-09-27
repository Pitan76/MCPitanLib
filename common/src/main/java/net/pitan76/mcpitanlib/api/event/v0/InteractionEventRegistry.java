package net.pitan76.mcpitanlib.api.event.v0;

import me.shedaniel.architectury.event.events.InteractionEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.result.EventResult;
import net.pitan76.mcpitanlib.api.event.result.TypedEventResult;
import net.pitan76.mcpitanlib.api.event.v0.event.ClickBlockEvent;

public class InteractionEventRegistry {
    public static void registerRightClickBlock(RightClickBlock rightClickBlock) {
        InteractionEvent.RIGHT_CLICK_BLOCK.register((player, hand, pos, direction) -> rightClickBlock.click(new ClickBlockEvent(player, hand, pos, direction)).toActionResult());
    }

    public static void registerLeftClickBlock(LeftClickBlock leftClickBlock) {
        InteractionEvent.LEFT_CLICK_BLOCK.register((player, hand, pos, direction) -> leftClickBlock.click(new ClickBlockEvent(player, hand, pos, direction)).toActionResult());
    }

    public static void registerRightClickItem(RightClickItem rightClickItem) {
        InteractionEvent.RIGHT_CLICK_ITEM.register(rightClickItem::click);
    }

    public static void registerClientLeftClickAir(ClientLeftClickAir clientLeftClickAir) {
        InteractionEvent.CLIENT_LEFT_CLICK_AIR.register(clientLeftClickAir::click);
    }

    public static void registerClientRightClickAir(ClientRightClickAir clientRightClickAir) {
        InteractionEvent.CLIENT_RIGHT_CLICK_AIR.register(clientRightClickAir::click);
    }

    public static void registerInteractEntity(InteractEntity interactEntity) {
        InteractionEvent.INTERACT_ENTITY.register(interactEntity::interact);
    }

    // ----

    public interface LeftClickBlock {
        EventResult click(ClickBlockEvent event);
    }

    public interface RightClickBlock {
        EventResult click(ClickBlockEvent event);
    }

    public interface RightClickItem {
        default TypedActionResult<ItemStack> click(PlayerEntity var1, Hand var2) {
            return click(new Player(var1), var2).toTypedActionResult();
        }

        TypedEventResult<ItemStack> click(Player player, Hand hand);
    }

    public interface ClientLeftClickAir {
        default void click(PlayerEntity var1, Hand var2) {
            click(new Player(var1), var2);
        }

        void click(Player player, Hand hand);
    }

    public interface ClientRightClickAir {
        default void click(PlayerEntity var1, Hand var2) {
            click(new Player(var1), var2);
        }

        void click(Player player, Hand hand);
    }

    public interface InteractEntity {
        default ActionResult interact(PlayerEntity var1, Entity var2, Hand var3) {
            return interact(new Player(var1), var2, var3).toActionResult();
        }

        EventResult interact(Player player, Entity entity, Hand hand);
    }
}
