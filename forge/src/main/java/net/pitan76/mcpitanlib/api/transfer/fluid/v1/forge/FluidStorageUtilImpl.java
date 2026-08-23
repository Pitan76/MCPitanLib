package net.pitan76.mcpitanlib.api.transfer.fluid.v1.forge;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.fluid.Fluid;
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
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiFunction;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.capability.templates.FluidTank;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidStorage;
import net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidVariant;

@EventBusSubscriber(modid = MCPitanLib.MOD_ID)
public class FluidStorageUtilImpl {

    private static final Map<BlockEntityType<?>, BiFunction<BlockEntity, Direction, IFluidStorage>> providers = new ConcurrentHashMap<>();

    public static IFluidStorage withFixedCapacity(long capacity, Runnable onChange) {
        return new ForgeFluidStorage(new FluidTank((int) capacity), onChange);
    }

    public static IFluidVariant getVariant(Fluid fluid) {
        return new ForgeFluidVariant(new FluidStack(fluid, 1));
    }

    public static long bucketAmount() {
        return FluidType.BUCKET_VOLUME;
    }

    @Nullable
    public static net.pitan76.mcpitanlib.api.transfer.fluid.v1.IFluidHandler getFluidHandler(World world, BlockPos pos, @Nullable Direction side) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity == null) return null;

        net.minecraftforge.fluids.capability.IFluidHandler handler =
                blockEntity.getCapability(ForgeCapabilities.FLUID_HANDLER, side).resolve().orElse(null);
        if (handler == null) return null;

        return new ForgeFluidHandler(handler);
    }

    public static void registerFluidStorage(BlockEntityType<?> type, BiFunction<BlockEntity, Direction, IFluidStorage> provider) {
        providers.put(type, provider);
    }

    @SubscribeEvent
    public static void onAttachCapabilities(AttachCapabilitiesEvent<BlockEntity> event) {
        if (providers.isEmpty()) return;

        BlockEntity blockEntity = event.getObject();
        BiFunction<BlockEntity, Direction, IFluidStorage> provider = providers.get(blockEntity.getType());
        if (provider == null) return;

        event.addCapability(CompatIdentifier.of(MCPitanLib.MOD_ID, "fluid").toMinecraft(), new ICapabilityProvider() {
            @NotNull
            @Override
            public <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction side) {
                if (capability != ForgeCapabilities.FLUID_HANDLER) return LazyOptional.empty();

                IFluidStorage storage = provider.apply(blockEntity, side);
                if (!(storage instanceof ForgeFluidStorage)) return LazyOptional.empty();

                return LazyOptional.of(() -> ((ForgeFluidStorage) storage).storage).cast();
            }
        });
    }
}
