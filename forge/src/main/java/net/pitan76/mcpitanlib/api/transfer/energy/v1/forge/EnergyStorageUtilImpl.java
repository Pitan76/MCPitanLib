package net.pitan76.mcpitanlib.api.transfer.energy.v1.forge;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.transfer.energy.v1.IEnergyStorage;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;

/**
 * ForgeではCapabilityでエネルギーを扱うため、常に利用できる。
 * <p>
 * ForgeにはNeoForgeの {@code RegisterCapabilitiesEvent#registerBlockEntity} が無いので、
 * {@link AttachCapabilitiesEvent} でBlockEntityごとにプロバイダを付ける。
 */
@EventBusSubscriber(modid = MCPitanLib.MOD_ID)
public class EnergyStorageUtilImpl {

    private static final Map<BlockEntityType<?>, BiFunction<BlockEntity, Direction, IEnergyStorage>> providers = new ConcurrentHashMap<>();

    public static boolean isSupported() {
        return true;
    }

    public static IEnergyStorage create(long capacity, long maxInsert, long maxExtract) {
        return new ForgeEnergyStorage(new net.minecraftforge.energy.EnergyStorage(toInt(capacity), toInt(maxInsert), toInt(maxExtract)));
    }

    @Nullable
    public static IEnergyStorage getEnergyStorage(World world, BlockPos pos, @Nullable Direction side) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity == null) return null;

        net.minecraftforge.energy.IEnergyStorage handler = blockEntity.getCapability(ForgeCapabilities.ENERGY, side).resolve().orElse(null);
        if (handler == null) return null;

        return new ForgeEnergyStorage(handler);
    }

    public static void registerEnergyStorage(BlockEntityType<?> type, BiFunction<BlockEntity, Direction, IEnergyStorage> provider) {
        providers.put(type, provider);
    }

    public static long addEnergyToForeignTile(BlockEntity blockEntity, long amount, @Nullable Direction side) {
        if (blockEntity == null || blockEntity.getWorld() == null) return 0;

        IEnergyStorage storage = getEnergyStorage(blockEntity.getWorld(), blockEntity.getPos(), side);
        if (storage == null || !storage.supportsInsertion()) return 0;

        return storage.insert(amount);
    }

    /**
     * MCPitanLibのIEnergyStorageをForgeのIEnergyStorageに変換する。
     * ラッパー越しの二重変換を避けるため、元がForgeEnergyStorageならそのまま取り出す。
     */
    public static net.minecraftforge.energy.IEnergyStorage toRaw(IEnergyStorage storage) {
        if (storage instanceof ForgeEnergyStorage)
            return ((ForgeEnergyStorage) storage).getRaw();

        return new ForgeWrappedEnergyStorage(storage);
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<BlockEntity> event) {
        if (providers.isEmpty()) return;

        BlockEntity blockEntity = event.getObject();
        BiFunction<BlockEntity, Direction, IEnergyStorage> provider = providers.get(blockEntity.getType());
        if (provider == null) return;

        event.addCapability(CompatIdentifier.of(MCPitanLib.MOD_ID, "energy").toMinecraft(), new ICapabilityProvider() {
            @NotNull
            @Override
            public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side) {
                if (capability != ForgeCapabilities.ENERGY) return LazyOptional.empty();

                IEnergyStorage storage = provider.apply(blockEntity, side);
                if (storage == null) return LazyOptional.empty();

                return LazyOptional.of(() -> toRaw(storage)).cast();
            }
        });
    }

    private static int toInt(long amount) {
        if (amount > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (amount < 0) return 0;

        return (int) amount;
    }
}
