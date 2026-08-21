package net.pitan76.mcpitanlib.api.transfer.energy.v1.neoforge;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage;
import net.pitan76.mcpitanlib.api.util.LoggerUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiFunction;
import java.util.function.Consumer;

/**
 * NeoForgeではCapabilityでエネルギーを扱うため、常に利用できる。
 */
@EventBusSubscriber(modid = "mcpitanlib", bus = EventBusSubscriber.Bus.MOD)
public class EnergyStorageUtilImpl {

    private static final List<Consumer<RegisterCapabilitiesEvent>> registrations = new CopyOnWriteArrayList<>();

    /**
     * RegisterCapabilitiesEventが発火済みかどうか。
     * 発火後に登録要求が来た場合、そのままでは黙って無視されるため警告を出す。
     */
    private static boolean registered = false;

    public static boolean isSupported() {
        return true;
    }

    public static IEnergyStorage create(long capacity, long maxInsert, long maxExtract) {
        return new NeoForgeEnergyStorage(new net.neoforged.neoforge.energy.EnergyStorage(toInt(capacity), toInt(maxInsert), toInt(maxExtract)));
    }

    @Nullable
    public static IEnergyStorage getEnergyStorage(World world, BlockPos pos, @Nullable Direction side) {
        net.neoforged.neoforge.energy.IEnergyStorage handler = world.getCapability(Capabilities.EnergyStorage.BLOCK, pos, side);
        if (handler == null) return null;

        return new NeoForgeEnergyStorage(handler);
    }

    public static void registerEnergyStorage(BlockEntityType<?> type, BiFunction<BlockEntity, Direction, IEnergyStorage> provider) {
        if (registered) {
            LoggerUtil.warn(LoggerUtil.getLogger(EnergyStorageUtilImpl.class),
                    "registerEnergyStorage was called after RegisterCapabilitiesEvent. The registration is ignored: " + type);
            return;
        }

        registrations.add(event -> event.registerBlockEntity(Capabilities.EnergyStorage.BLOCK, type, (blockEntity, direction) -> {
            IEnergyStorage storage = provider.apply(blockEntity, direction);
            if (storage == null) return null;

            return toRaw(storage);
        }));
    }

    public static long addEnergyToForeignTile(BlockEntity blockEntity, long amount, @Nullable Direction side) {
        if (blockEntity == null || blockEntity.getWorld() == null) return 0;

        IEnergyStorage storage = getEnergyStorage(blockEntity.getWorld(), blockEntity.getPos(), side);
        if (storage == null || !storage.supportsInsertion()) return 0;

        return storage.insert(amount);
    }

    /**
     * MCPitanLibのIEnergyStorageをNeoForgeのIEnergyStorageに変換する。
     * ラッパー越しの二重変換を避けるため、元がNeoForgeEnergyStorageならそのまま取り出す。
     */
    public static net.neoforged.neoforge.energy.IEnergyStorage toRaw(IEnergyStorage storage) {
        if (storage instanceof NeoForgeEnergyStorage)
            return ((NeoForgeEnergyStorage) storage).getRaw();

        return new NeoForgeWrappedEnergyHandler(storage);
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        for (Consumer<RegisterCapabilitiesEvent> registration : registrations) {
            registration.accept(event);
        }
        registered = true;
    }

    private static int toInt(long amount) {
        if (amount > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (amount < 0) return 0;

        return (int) amount;
    }
}
