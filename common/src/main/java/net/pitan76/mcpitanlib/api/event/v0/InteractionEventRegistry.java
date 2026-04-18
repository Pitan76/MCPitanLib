package net.pitan76.mcpitanlib.api.event.v0;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
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
