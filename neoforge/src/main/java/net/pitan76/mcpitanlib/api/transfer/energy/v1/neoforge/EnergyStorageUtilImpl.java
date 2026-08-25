package net.pitan76.mcpitanlib.api.transfer.energy.v1.neoforge;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.transfer.energy.EnergyHandler;
import net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage;
import net.pitan76.mcpitanlib.api.util.LoggerUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.function.Consumer;

/**
 * NeoForgeではCapabilityでエネルギーを扱うため、常に利用できる。
 */
@EventBusSubscriber(modid = "mcpitanlib")
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
        return new NeoForgeEnergyStorage(new net.neoforged.neoforge.transfer.energy.SimpleEnergyHandler(toInt(capacity), toInt(maxInsert), toInt(maxExtract)));
    }

    @Nullable
    public static IEnergyStorage getEnergyStorage(Level world, BlockPos pos, @Nullable Direction side) {
        EnergyHandler handler = world.getCapability(Capabilities.Energy.BLOCK, pos, side);
        if (handler == null) return null;

        return fromRaw(handler);
    }

    public static void registerEnergyStorage(BlockEntityType<?> type, BiFunction<BlockEntity, Direction, IEnergyStorage> provider) {
        if (type == null) {
            LoggerUtil.warn(LoggerUtil.getLogger(EnergyStorageUtilImpl.class),
                    "registerEnergyStorage was called with a null BlockEntityType. "
                            + "On NeoForge the BlockEntityType is not resolved yet during mod initialization; "
                            + "use registerEnergyStorageLazy (or EnergyLookup#registerForBlockEntity with a RegistryResult) instead.");
            return;
        }

        registerEnergyStorageLazy(() -> type, provider);
    }

    public static void registerEnergyStorageLazy(Supplier<BlockEntityType<?>> typeSupplier, BiFunction<BlockEntity, Direction, IEnergyStorage> provider) {
        if (registered) {
            LoggerUtil.warn(LoggerUtil.getLogger(EnergyStorageUtilImpl.class),
                    "registerEnergyStorage was called after RegisterCapabilitiesEvent. The registration is ignored.");
            return;
        }

        registrations.add(event -> {
            BlockEntityType<?> type = typeSupplier.get();
            if (type == null) {
                LoggerUtil.warn(LoggerUtil.getLogger(EnergyStorageUtilImpl.class),
                        "The BlockEntityType is still unresolved at RegisterCapabilitiesEvent. The registration is ignored.");
                return;
            }

            event.registerBlockEntity(Capabilities.Energy.BLOCK, type, (blockEntity, direction) -> {
                IEnergyStorage storage = provider.apply(blockEntity, direction);
                if (storage == null) return null;

                return toRaw(storage);
            });
        });
    }

    public static long addEnergyToForeignTile(BlockEntity blockEntity, long amount, @Nullable Direction side) {
        if (blockEntity == null || blockEntity.getLevel() == null) return 0;

        IEnergyStorage storage = getEnergyStorage(blockEntity.getLevel(), blockEntity.getBlockPos(), side);
        if (storage == null || !storage.canInsertEnergy()) return 0;

        return storage.insertEnergy(amount);
    }

    /**
     * MCPitanLibを使うMODが登録したストレージは、往復変換で情報が落ちないよう中身をそのまま返す。
     */
    public static IEnergyStorage fromRaw(EnergyHandler handler) {
        if (handler instanceof NeoForgeWrappedEnergyHandler)
            return ((NeoForgeWrappedEnergyHandler) handler).storage;

        return new NeoForgeEnergyStorage(handler);
    }

    /**
     * ラッパー越しの二重変換を避けるため、元がNeoForgeEnergyStorageならそのまま取り出す。
     */
    public static EnergyHandler toRaw(IEnergyStorage storage) {
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
