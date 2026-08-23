package net.pitan76.mcpitanlib.api.transfer.item.v1.neoforge;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.world.World;
import net.minecraft.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.transfer.item.VanillaContainerWrapper;
import net.neoforged.neoforge.transfer.item.WorldlyContainerWrapper;
import net.pitan76.mcpitanlib.api.transfer.item.v1.IItemHandler;
import net.pitan76.mcpitanlib.api.util.LoggerUtil;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.ResourceHandlerUtil;
import net.neoforged.neoforge.transfer.item.ItemResource;
import net.neoforged.neoforge.transfer.transaction.Transaction;
import org.jetbrains.annotations.Nullable;

@EventBusSubscriber(modid = "mcpitanlib")
public class ItemTransferUtilImpl {

    private static final List<Consumer<RegisterCapabilitiesEvent>> registrations = new CopyOnWriteArrayList<>();
    private static boolean registered = false;

    public static int insertTo(World world, BlockPos pos, @Nullable Direction side, ItemStack stack, boolean simulate) {
        if (stack == null || stack.isEmpty()) return 0;

        ResourceHandler<ItemResource> handler = world.getCapability(Capabilities.Item.BLOCK, pos, side);
        if (handler == null) return 0;

        try (Transaction transaction = Transaction.open(null)) {
            // 既存のスロットに寄せてから空きスロットを使うので、バニラのホッパーに近い挙動になる
            int inserted = ResourceHandlerUtil.insertStacking(handler, ItemResource.of(stack), stack.getCount(), transaction);
            if (!simulate) transaction.commit();

            return inserted;
        }
    }

    @Nullable
    public static IItemHandler getItemHandler(World world, BlockPos pos, @Nullable Direction side) {
        ResourceHandler<ItemResource> handler = world.getCapability(Capabilities.Item.BLOCK, pos, side);
        if (handler == null) return null;

        return new NeoForgeItemHandler(handler);
    }

    public static void registerInventory(BlockEntityType<?> type) {
        if (registered) {
            LoggerUtil.warn(LoggerUtil.getLogger(ItemTransferUtilImpl.class),
                    "registerInventory was called after RegisterCapabilitiesEvent. The registration is ignored: " + type);
            return;
        }

        registrations.add(event -> event.registerBlockEntity(Capabilities.Item.BLOCK, type, (blockEntity, direction) -> {
            if (!(blockEntity instanceof Inventory)) return null;

            // SidedInventoryなら面ごとのスロット制限をそのまま使う
            if (direction != null && blockEntity instanceof SidedInventory)
                return new WorldlyContainerWrapper((SidedInventory) blockEntity, direction);

            return VanillaContainerWrapper.of((Inventory) blockEntity);
        }));
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        for (Consumer<RegisterCapabilitiesEvent> registration : registrations) {
            registration.accept(event);
        }
        registered = true;
    }
}
