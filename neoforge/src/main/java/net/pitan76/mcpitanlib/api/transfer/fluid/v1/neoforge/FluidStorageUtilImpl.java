package net.pitan76.mcpitanlib.api.transfer.fluid.v1.neoforge;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.fluid.Fluid;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.transfer.ResourceHandler;
import net.neoforged.neoforge.transfer.fluid.FluidResource;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidHandler;
import net.pitan76.mcpitanlib.api.util.LoggerUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import java.util.function.Consumer;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.transfer.fluid.FluidStacksResourceHandler;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidStorage;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidVariant;

@EventBusSubscriber(modid = "mcpitanlib")
public class FluidStorageUtilImpl {

    private static final List<Consumer<RegisterCapabilitiesEvent>> registrations = new CopyOnWriteArrayList<>();
    private static boolean registered = false;

    public static IFluidStorage withFixedCapacity(long capacity, Runnable onChange) {
        return new NeoForgeFluidStorage(new FluidStacksResourceHandler(1, (int) capacity), capacity, onChange);
    }

    public static IFluidVariant getVariant(Fluid fluid) {
        return new NeoForgeFluidVariant(new FluidStack(fluid, 1));
    }

    public static long bucketAmount() {
        return FluidType.BUCKET_VOLUME;
    }

    @Nullable
    public static IFluidHandler getFluidHandler(World world, BlockPos pos, @Nullable Direction side) {
        ResourceHandler<FluidResource> handler = world.getCapability(Capabilities.Fluid.BLOCK, pos, side);
        if (handler == null) return null;

        return new NeoForgeFluidHandler(handler);
    }

    public static void registerFluidStorage(BlockEntityType<?> type, BiFunction<BlockEntity, Direction, IFluidStorage> provider) {
        if (type == null) {
            LoggerUtil.warn(LoggerUtil.getLogger(FluidStorageUtilImpl.class),
                    "registerFluidStorage was called with a null BlockEntityType. "
                            + "On NeoForge the BlockEntityType is not resolved yet during mod initialization; "
                            + "use registerFluidStorageLazy (or FluidLookup#registerForBlockEntity with a RegistryResult) instead.");
            return;
        }

        registerFluidStorageLazy(() -> type, provider);
    }

    public static void registerFluidStorageLazy(Supplier<BlockEntityType<?>> typeSupplier, BiFunction<BlockEntity, Direction, IFluidStorage> provider) {
        if (registered) {
            LoggerUtil.warn(LoggerUtil.getLogger(FluidStorageUtilImpl.class),
                    "registerFluidStorage was called after RegisterCapabilitiesEvent. The registration is ignored.");
            return;
        }

        registrations.add(event -> {
            BlockEntityType<?> type = typeSupplier.get();
            if (type == null) {
                LoggerUtil.warn(LoggerUtil.getLogger(FluidStorageUtilImpl.class),
                        "The BlockEntityType is still unresolved at RegisterCapabilitiesEvent. The registration is ignored.");
                return;
            }

            event.registerBlockEntity(Capabilities.Fluid.BLOCK, type, (blockEntity, direction) -> {
                IFluidStorage storage = provider.apply(blockEntity, direction);
                if (!(storage instanceof NeoForgeFluidStorage)) return null;

                return ((NeoForgeFluidStorage) storage).handler;
            });
        });
    }

    @SubscribeEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        for (Consumer<RegisterCapabilitiesEvent> registration : registrations) {
            registration.accept(event);
        }
        registered = true;
    }
}
