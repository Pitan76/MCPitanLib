package net.pitan76.mcpitanlib.api.event.v0.fabric;

import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.pitan76.mcpitanlib.api.event.v0.InteractionEventRegistry;

public class InteractionEventRegistryImpl {

    public static void registerRightClickBlock(final InteractionEventRegistry.RightClickBlock rightClickBlock) {
        UseBlockCallback.EVENT.register(new UseBlockCallback() {
            @Override
            public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockHitResult hitResult) {
                return rightClickBlock.click(new net.pitan76.mcpitanlib.api.event.v0.event.ClickBlockEvent(player, hand, hitResult.getBlockPos(), hitResult.getSide())).toActionResult();
            }
        });
    }

    public static void registerLeftClickBlock(final InteractionEventRegistry.LeftClickBlock leftClickBlock) {
        AttackBlockCallback.EVENT.register(new AttackBlockCallback() {
            @Override
            public ActionResult interact(PlayerEntity player, World world, Hand hand, BlockPos pos, Direction direction) {
                return leftClickBlock.click(new net.pitan76.mcpitanlib.api.event.v0.event.ClickBlockEvent(player, hand, pos, direction)).toActionResult();
            }
        });
    }

    public static void registerRightClickItem(final InteractionEventRegistry.RightClickItem rightClickItem) {
        UseItemCallback.EVENT.register(new UseItemCallback() {
            @Override
            public TypedActionResult<ItemStack> interact(PlayerEntity player, World world, Hand hand) {
                return rightClickItem.click(player, hand);
            }
        });
    }

    // Fabric APIの1.16.5版には空振りのイベントが無いため未対応
    public static void registerClientLeftClickAir(InteractionEventRegistry.ClientLeftClickAir clientLeftClickAir) {
        // TODO: Not supported on fabric 1.16.5
    }

    public static void registerClientRightClickAir(InteractionEventRegistry.ClientRightClickAir clientRightClickAir) {
        // TODO: Not supported on fabric 1.16.5
    }

    public static void registerInteractEntity(final InteractionEventRegistry.InteractEntity interactEntity) {
        UseEntityCallback.EVENT.register(new UseEntityCallback() {
            @Override
            public ActionResult interact(PlayerEntity player, World world, Hand hand, Entity entity, EntityHitResult hitResult) {
                return interactEntity.interact(player, entity, hand);
            }
        });
    }
}
