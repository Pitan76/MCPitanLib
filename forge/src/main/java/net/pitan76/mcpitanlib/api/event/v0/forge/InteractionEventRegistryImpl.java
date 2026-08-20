package net.pitan76.mcpitanlib.api.event.v0.forge;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.event.result.EventResult;
import net.pitan76.mcpitanlib.api.event.v0.InteractionEventRegistry.*;
import net.pitan76.mcpitanlib.api.event.v0.event.ClickBlockEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@EventBusSubscriber(modid = MCPitanLib.MOD_ID)
public class InteractionEventRegistryImpl {

    private static final List<RightClickBlock> rightClickBlockListeners = new CopyOnWriteArrayList<>();
    private static final List<LeftClickBlock> leftClickBlockListeners = new CopyOnWriteArrayList<>();
    private static final List<RightClickItem> rightClickItemListeners = new CopyOnWriteArrayList<>();
    private static final List<ClientLeftClickAir> clientLeftClickAirListeners = new CopyOnWriteArrayList<>();
    private static final List<ClientRightClickAir> clientRightClickAirListeners = new CopyOnWriteArrayList<>();
    private static final List<InteractEntity> interactEntityListeners = new CopyOnWriteArrayList<>();

    public static void registerRightClickBlock(RightClickBlock rightClickBlock) {
        rightClickBlockListeners.add(rightClickBlock);
    }

    public static void registerLeftClickBlock(LeftClickBlock leftClickBlock) {
        leftClickBlockListeners.add(leftClickBlock);
    }

    public static void registerRightClickItem(RightClickItem rightClickItem) {
        rightClickItemListeners.add(rightClickItem);
    }

    public static void registerClientLeftClickAir(ClientLeftClickAir clientLeftClickAir) {
        clientLeftClickAirListeners.add(clientLeftClickAir);
    }

    public static void registerClientRightClickAir(ClientRightClickAir clientRightClickAir) {
        clientRightClickAirListeners.add(clientRightClickAir);
    }

    public static void registerInteractEntity(InteractEntity interactEntity) {
        interactEntityListeners.add(interactEntity);
    }

    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        for (RightClickBlock listener : rightClickBlockListeners) {
            EventResult result = listener.click(new ClickBlockEvent(event.getPlayer(), event.getHand(), event.getPos(), event.getFace()));
            if (result != EventResult.pass()) {
                event.setCanceled(true);
                event.setCancellationResult(result.toActionResult());
                return;
            }
        }
    }

    @SubscribeEvent
    public static void onLeftClickBlock(PlayerInteractEvent.LeftClickBlock event) {
        for (LeftClickBlock listener : leftClickBlockListeners) {
            listener.click(new ClickBlockEvent(event.getPlayer(), event.getHand(), event.getPos(), event.getFace()));
        }
    }

    @SubscribeEvent
    public static void onRightClickItem(PlayerInteractEvent.RightClickItem event) {
        for (RightClickItem listener : rightClickItemListeners) {
            listener.click2(event.getPlayer(), event.getHand());
        }
    }

    @SubscribeEvent
    public static void onClientLeftClickAir(PlayerInteractEvent.LeftClickEmpty event) {
        for (ClientLeftClickAir listener : clientLeftClickAirListeners) {
            listener.click(event.getPlayer(), event.getHand());
        }
    }

    @SubscribeEvent
    public static void onClientRightClickAir(PlayerInteractEvent.RightClickEmpty event) {
        for (ClientRightClickAir listener : clientRightClickAirListeners) {
            listener.click(event.getPlayer(), event.getHand());
        }
    }

    @SubscribeEvent
    public static void onInteractEntity(PlayerInteractEvent.EntityInteract event) {
        for (InteractEntity listener : interactEntityListeners) {
            listener.interact(event.getPlayer(), event.getTarget(), event.getHand());
        }
    }
}

