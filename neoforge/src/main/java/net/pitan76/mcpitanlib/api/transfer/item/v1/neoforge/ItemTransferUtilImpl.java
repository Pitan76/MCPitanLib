package net.pitan76.mcpitanlib.api.transfer.item.v1.neoforge;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.items.IItemHandler;
import net.neoforged.neoforge.items.ItemHandlerHelper;
import net.neoforged.neoforge.items.wrapper.InvWrapper;
import net.neoforged.neoforge.items.wrapper.SidedInvWrapper;
import net.pitan76.mcpitanlib.api.util.LoggerUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

@EventBusSubscriber(modid = "mcpitanlib")
public class ItemTransferUtilImpl {

    private static final List<Consumer<RegisterCapabilitiesEvent>> registrations = new CopyOnWriteArrayList<>();
    private static boolean registered = false;

    public static int insertTo(World world, BlockPos pos, @Nullable Direction side, ItemStack stack, boolean simulate) {
        if (stack == null || stack.isEmpty()) return 0;

        IItemHandler handler = world.getCapability(Capabilities.ItemHandler.BLOCK, pos, side);
        if (handler == null) return 0;

        // 既存のスロットに寄せてから空きスロットを使うので、バニラのホッパーに近い挙動になる
        ItemStack remainder = ItemHandlerHelper.insertItemStacked(handler, stack, simulate);

        return stack.getCount() - remainder.getCount();
    }

    @Nullable
    public static net.pitan76.mcpitanlib.api.transfer.item.v1.IItemHandler getItemHandler(World world, BlockPos pos, @Nullable Direction side) {
        IItemHandler handler = world.getCapability(Capabilities.ItemHandler.BLOCK, pos, side);
        if (handler == null) return null;

        return new NeoForgeItemHandler(handler);
    }

    public static void registerInventory(BlockEntityType<?> type) {
        if (registered) {
            LoggerUtil.warn(LoggerUtil.getLogger(ItemTransferUtilImpl.class),
                    "registerInventory was called after RegisterCapabilitiesEvent. The registration is ignored: " + type);
            return;
        }

        registrations.add(event -> event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, type, (blockEntity, direction) -> {
            if (!(blockEntity instanceof Inventory)) return null;

            // SidedInventoryなら面ごとのスロット制限をそのまま使う
            if (direction != null && blockEntity instanceof SidedInventory)
                return new SidedInvWrapper((SidedInventory) blockEntity, direction);

            return new InvWrapper((Inventory) blockEntity);
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
