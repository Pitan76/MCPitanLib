package net.pitan76.mcpitanlib.api.transfer.fluid.v1.neoforge;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;
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
    public static IFluidHandler getFluidHandler(Level world, BlockPos pos, @Nullable Direction side) {
        ResourceHandler<FluidResource> handler = world.getCapability(Capabilities.Fluid.BLOCK, pos, side);
        if (handler == null) return null;

        return new NeoForgeFluidHandler(handler);
    }

    public static void registerFluidStorage(BlockEntityType<?> type, BiFunction<BlockEntity, Direction, IFluidStorage> provider) {
        if (registered) {
            LoggerUtil.warn(LoggerUtil.getLogger(FluidStorageUtilImpl.class),
                    "registerFluidStorage was called after RegisterCapabilitiesEvent. The registration is ignored: " + type);
            return;
        }

        registrations.add(event -> event.registerBlockEntity(Capabilities.Fluid.BLOCK, type, (blockEntity, direction) -> {
            IFluidStorage storage = provider.apply(blockEntity, direction);
            if (!(storage instanceof NeoForgeFluidStorage)) return null;

            return ((NeoForgeFluidStorage) storage).handler;
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
