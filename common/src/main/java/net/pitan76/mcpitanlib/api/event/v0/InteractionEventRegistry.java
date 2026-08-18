package net.pitan76.mcpitanlib.api.event.v0;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.result.EventResult;
import net.pitan76.mcpitanlib.api.event.v0.event.ClickBlockEvent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;

public class InteractionEventRegistry {
    @ExpectPlatform
    public static void registerRightClickBlock(RightClickBlock rightClickBlock) {

    }

    @ExpectPlatform
    public static void registerLeftClickBlock(LeftClickBlock leftClickBlock) {

    }

    @ExpectPlatform
    public static void registerRightClickItem(RightClickItem rightClickItem) {

    }

    @ExpectPlatform
    public static void registerClientLeftClickAir(ClientLeftClickAir clientLeftClickAir) {

    }

    @ExpectPlatform
    public static void registerClientRightClickAir(ClientRightClickAir clientRightClickAir) {

    }

    @ExpectPlatform
    public static void registerInteractEntity(InteractEntity interactEntity) {

    }

    // ----

    public interface LeftClickBlock {
        EventResult click(ClickBlockEvent event);
    }

    public interface RightClickBlock {
        EventResult click(ClickBlockEvent event);
    }

    public interface RightClickItem {
        default ActionResult click2(PlayerEntity var1, Hand var2) {
            return click(new Player(var1), var2).toActionResult();
        }

        CompatActionResult click(Player player, Hand hand);
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
        @SuppressWarnings("deprecation")
        default ActionResult interact(PlayerEntity var1, Entity var2, Hand var3) {
            return interact(new Player(var1), var2, var3).toActionResult();
        }

        CompatActionResult interact(Player player, Entity entity, Hand hand);
    }
}
