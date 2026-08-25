package net.pitan76.mcpitanlib.api.transfer.fluid.v1.forge;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.BiFunction;
import java.util.function.Supplier;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidStorage;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidVariant;

@EventBusSubscriber(modid = MCPitanLib.MOD_ID)
public class FluidStorageUtilImpl {

    private static final Map<BlockEntityType<?>, BiFunction<BlockEntity, Direction, IFluidStorage>> providers = new ConcurrentHashMap<>();

    private static final List<Object[]> pending = new CopyOnWriteArrayList<>();

    public static IFluidStorage withFixedCapacity(long capacity, Runnable onChange) {
        return new ForgeFluidStorage(new FluidTank((int) capacity), onChange);
    }

    public static IFluidVariant getVariant(Fluid fluid) {
        return new ForgeFluidVariant(new FluidStack(fluid, 1));
    }

    public static long bucketAmount() {
        return FluidAttributes.BUCKET_VOLUME;
    }

    @Nullable
    public static net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidHandler getFluidHandler(World world, BlockPos pos, @Nullable Direction side) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity == null) return null;

        net.minecraftforge.fluids.capability.IFluidHandler handler =
                blockEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY, side).resolve().orElse(null);
        if (handler == null) return null;

        return new ForgeFluidHandler(handler);
    }

    public static void registerFluidStorage(BlockEntityType<?> type, BiFunction<BlockEntity, Direction, IFluidStorage> provider) {
        if (type == null) return;

        providers.put(type, provider);
    }

    public static void registerFluidStorageLazy(Supplier<BlockEntityType<?>> typeSupplier, BiFunction<BlockEntity, Direction, IFluidStorage> provider) {
        BlockEntityType<?> type = typeSupplier.get();
        if (type != null) {
            registerFluidStorage(type, provider);
            return;
        }

        pending.add(new Object[]{typeSupplier, provider});
    }

    @SuppressWarnings("unchecked")
    private static void flushPending() {
        if (pending.isEmpty()) return;

        for (Object[] entry : pending) {
            BlockEntityType<?> type = ((Supplier<BlockEntityType<?>>) entry[0]).get();
            if (type == null) continue;

            pending.remove(entry);
            providers.put(type, (BiFunction<BlockEntity, Direction, IFluidStorage>) entry[1]);
        }
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<BlockEntity> event) {
        flushPending();
        if (providers.isEmpty()) return;

        BlockEntity blockEntity = event.getObject();
        BiFunction<BlockEntity, Direction, IFluidStorage> provider = providers.get(blockEntity.getType());
        if (provider == null) return;

        event.addCapability(CompatIdentifier.of(MCPitanLib.MOD_ID, "fluid").toMinecraft(), new ICapabilityProvider() {
            @NotNull
            @Override
            public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side) {
                if (capability != CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) return LazyOptional.empty();

                IFluidStorage storage = provider.apply(blockEntity, side);
                if (!(storage instanceof ForgeFluidStorage)) return LazyOptional.empty();

                return LazyOptional.of(() -> ((ForgeFluidStorage) storage).storage).cast();
            }
        });
    }
}
