package net.pitan76.mcpitanlib.api.transfer.item.v1.forge;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.wrapper.InvWrapper;
import net.minecraftforge.items.wrapper.SidedInvWrapper;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@EventBusSubscriber(modid = MCPitanLib.MOD_ID)
public class ItemTransferUtilImpl {

    private static final Set<BlockEntityType<?>> inventoryTypes = ConcurrentHashMap.newKeySet();

    public static int insertTo(World world, BlockPos pos, @Nullable Direction side, ItemStack stack, boolean simulate) {
        if (stack == null || stack.isEmpty()) return 0;

        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity == null) return 0;

        IItemHandler handler = blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side).resolve().orElse(null);
        if (handler == null) return 0;

        // 既存のスロットに寄せてから空きスロットを使うので、バニラのホッパーに近い挙動になる
        ItemStack remainder = ItemHandlerHelper.insertItemStacked(handler, stack, simulate);

        return stack.getCount() - remainder.getCount();
    }

    @Nullable
    public static net.pitan76.mcpitanlib.api.transfer.item.v1.IItemHandler getItemHandler(World world, BlockPos pos, @Nullable Direction side) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity == null) return null;

        IItemHandler handler = blockEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, side).resolve().orElse(null);
        if (handler == null) return null;

        return new ForgeItemHandler(handler);
    }

    public static void registerInventory(BlockEntityType<?> type) {
        inventoryTypes.add(type);
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<BlockEntity> event) {
        if (inventoryTypes.isEmpty()) return;

        BlockEntity blockEntity = event.getObject();
        if (!inventoryTypes.contains(blockEntity.getType())) return;
        if (!(blockEntity instanceof Inventory)) return;

        event.addCapability(CompatIdentifier.of(MCPitanLib.MOD_ID, "item").toMinecraft(), new ICapabilityProvider() {
            @NotNull
            @Override
            public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side) {
                if (capability != CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) return LazyOptional.empty();

                // SidedInventoryなら面ごとのスロット制限をそのまま使う
                if (side != null && blockEntity instanceof SidedInventory)
                    return LazyOptional.of(() -> new SidedInvWrapper((SidedInventory) blockEntity, side)).cast();

                return LazyOptional.of(() -> new InvWrapper((Inventory) blockEntity)).cast();
            }
        });
    }
}
