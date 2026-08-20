package net.pitan76.mcpitanlib.api.event.v0;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.pitan76.mcpitanlib.api.entity.Player;
import net.pitan76.mcpitanlib.api.event.result.EventResult;
import net.pitan76.mcpitanlib.api.event.v0.event.ClickBlockEvent;
import net.pitan76.mcpitanlib.api.util.CompatActionResult;
import net.pitan76.mcpitanlib.api.util.StackActionResult;

public class InteractionEventRegistry {
    @ExpectPlatform
    public static void registerRightClickBlock(RightClickBlock rightClickBlock) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerLeftClickBlock(LeftClickBlock leftClickBlock) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerRightClickItem(RightClickItem rightClickItem) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerClientLeftClickAir(ClientLeftClickAir clientLeftClickAir) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerClientRightClickAir(ClientRightClickAir clientRightClickAir) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerInteractEntity(InteractEntity interactEntity) {
        throw new AssertionError();
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
            CompatActionResult result = click(new Player(var1), var2);
            if (result instanceof StackActionResult) {
                return ((StackActionResult) result).toTypedActionResult();
            } else {
                ItemStack stack = var1.getStackInHand(var2);
                return StackActionResult.create(result, stack).toTypedActionResult();
            }
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
        default ActionResult interact(PlayerEntity var1, Entity var2, Hand var3) {
            return interact(new Player(var1), var2, var3).toActionResult();
        }

        CompatActionResult interact(Player player, Entity entity, Hand hand);
    }
}
