package net.pitan76.mcpitanlib.api.event.v0.forge;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.event.v0.InteractionEventRegistry;
import net.pitan76.mcpitanlib.api.event.v0.event.ClickBlockEvent;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Mod.EventBusSubscriber(modid = MCPitanLib.MOD_ID)
public class InteractionEventRegistryImpl {
    private static final List<InteractionEventRegistry.RightClickBlock> rightClickBlocks = new CopyOnWriteArrayList<InteractionEventRegistry.RightClickBlock>();
    private static final List<InteractionEventRegistry.LeftClickBlock> leftClickBlocks = new CopyOnWriteArrayList<InteractionEventRegistry.LeftClickBlock>();
    private static final List<InteractionEventRegistry.RightClickItem> rightClickItems = new CopyOnWriteArrayList<InteractionEventRegistry.RightClickItem>();
    private static final List<InteractionEventRegistry.ClientLeftClickAir> clientLeftClickAirs = new CopyOnWriteArrayList<InteractionEventRegistry.ClientLeftClickAir>();
    private static final List<InteractionEventRegistry.ClientRightClickAir> clientRightClickAirs = new CopyOnWriteArrayList<InteractionEventRegistry.ClientRightClickAir>();
    private static final List<InteractionEventRegistry.InteractEntity> interactEntities = new CopyOnWriteArrayList<InteractionEventRegistry.InteractEntity>();

    public static void registerRightClickBlock(InteractionEventRegistry.RightClickBlock rightClickBlock) {
        rightClickBlocks.add(rightClickBlock);
    }

    public static void registerLeftClickBlock(InteractionEventRegistry.LeftClickBlock leftClickBlock) {
        leftClickBlocks.add(leftClickBlock);
    }

    public static void registerRightClickItem(InteractionEventRegistry.RightClickItem rightClickItem) {
        rightClickItems.add(rightClickItem);
    }

    public static void registerClientLeftClickAir(InteractionEventRegistry.ClientLeftClickAir clientLeftClickAir) {
        clientLeftClickAirs.add(clientLeftClickAir);
    }

    public static void registerClientRightClickAir(InteractionEventRegistry.ClientRightClickAir clientRightClickAir) {
        clientRightClickAirs.add(clientRightClickAir);
    }

    public static void registerInteractEntity(InteractionEventRegistry.InteractEntity interactEntity) {
        interactEntities.add(interactEntity);
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        for (InteractionEventRegistry.RightClickBlock handler : rightClickBlocks) {
            ActionResult result = handler.click(new ClickBlockEvent(event.getPlayer(), event.getHand(), event.getPos(), event.getFace())).toActionResult();
            if (result != ActionResult.PASS) {
                event.setCanceled(true);
                event.setCancellationResult(result);
                return;
            }
        }
    }

    @SubscribeEvent
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        for (InteractionEventRegistry.LeftClickBlock handler : leftClickBlocks) {
            ActionResult result = handler.click(new ClickBlockEvent(event.getPlayer(), event.getHand(), event.getPos(), event.getFace())).toActionResult();
            if (result != ActionResult.PASS) {
                event.setCanceled(true);
                event.setCancellationResult(result);
                return;
            }
        }
    }

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        PlayerEntity player = event.getPlayer();

        for (InteractionEventRegistry.RightClickItem handler : rightClickItems) {
            TypedActionResult<ItemStack> result = handler.click(player, event.getHand());
            if (result.getResult() != ActionResult.PASS) {
                event.setCanceled(true);
                event.setCancellationResult(result.getResult());
                return;
            }
        }
    }

    @SubscribeEvent
    public static void onLeftClickEmpty(PlayerInteractEvent.LeftClickEmpty event) {
        for (InteractionEventRegistry.ClientLeftClickAir handler : clientLeftClickAirs) {
            handler.click(event.getPlayer(), event.getHand());
        }
    }

    @SubscribeEvent
    public static void onRightClickEmpty(PlayerInteractEvent.RightClickEmpty event) {
        for (InteractionEventRegistry.ClientRightClickAir handler : clientRightClickAirs) {
            handler.click(event.getPlayer(), event.getHand());
        }
    }

    @SubscribeEvent
    public static void onEntityInteract(PlayerInteractEvent.EntityInteract event) {
        for (InteractionEventRegistry.InteractEntity handler : interactEntities) {
            ActionResult result = handler.interact(event.getPlayer(), event.getTarget(), event.getHand());
            if (result != ActionResult.PASS) {
                event.setCanceled(true);
                event.setCancellationResult(result);
                return;
            }
        }
    }
}
