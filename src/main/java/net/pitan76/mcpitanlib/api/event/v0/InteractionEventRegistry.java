package net.pitan76.mcpitanlib.api.event.v0;

import dev.architectury.event.events.common.InteractionEvent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.result.EventResult;
import net.pitan76.mcpitanlib.api.event.v0.event.ClickBlockEvent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;

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
        default InteractionResult click(Player var1, InteractionHand var2) {
            return click(new Player(var1), var2).toActionResult();
        }

        CompatActionResult click(Player player, InteractionHand hand);
    }

    public interface ClientLeftClickAir {
        default void click(Player var1, InteractionHand var2) {
            click(new Player(var1), var2);
        }

        void click(Player player, InteractionHand hand);
    }

    public interface ClientRightClickAir {
        default void click(Player var1, InteractionHand var2) {
            click(new Player(var1), var2);
        }

        void click(Player player, InteractionHand hand);
    }

    public interface InteractEntity {
        @SuppressWarnings("deprecation")
        default dev.architectury.event.EventResult interact(Player var1, Entity var2, InteractionHand var3) {
            return interact(new Player(var1), var2, var3).toEventResult().getResult();
        }

        CompatActionResult interact(Player player, Entity entity, InteractionHand hand);
    }
}
