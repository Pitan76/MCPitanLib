package net.pitan76.mcpitanlib.api.client.registry.forge;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.client.registry.ArchRegistryClient;
import net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = MCPitanLib.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class CompatRegistryClientImpl {

    private static final List<Runnable> screens = new CopyOnWriteArrayList<Runnable>();
    private static volatile boolean clientSetupDone = false;

    private static final List<Runnable> particles = new CopyOnWriteArrayList<Runnable>();

    public static <H extends ScreenHandler, S extends Screen & ScreenHandlerProvider<H>> void registerScreen(String modId, final Supplier<ScreenHandlerType<? extends H>> type, final CompatRegistryClient.ScreenFactory<H, S> factory) {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                HandledScreens.register(type.get(), factory::create);
            }
        };

        if (clientSetupDone) {
            task.run();
            return;
        }

        screens.add(task);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        // 他modもFMLClientSetupEventの中でregisterScreenを呼ぶため、
        // リスナー内でリストを消化すると後から追加された分を取りこぼす。
        // enqueueWorkはイベント配信が終わってから走るので、その中で走査する。
        event.enqueueWork(new Runnable() {
            @Override
            public void run() {
                for (Runnable screen : screens) {
                    screen.run();
                }

                screens.clear();
                clientSetupDone = true;
            }
        });
    }

    public static <T extends ParticleEffect> void registerParticle(final ParticleType<T> type, final ParticleFactory<T> factory) {
        particles.add(new Runnable() {
            @Override
            public void run() {
                MinecraftClient.getInstance().particleManager.registerFactory(type, factory);
            }
        });
    }

    public static <T extends ParticleEffect> void registerParticle(final ParticleType<T> type, final ArchRegistryClient.DeferredParticleProvider<T> provider) {
        particles.add(new Runnable() {
            @Override
            public void run() {
                MinecraftClient.getInstance().particleManager.registerFactory(type, new net.minecraft.client.particle.ParticleManager.SpriteAwareFactory<T>() {
                    @Override
                    public ParticleFactory<T> create(final SpriteProvider spriteSet) {
                        return provider.create(new ArchRegistryClient.ExtendedSpriteSet() {
                            @Override
                            public SpriteAtlasTexture getAtlas() {
                                return null;
                            }

                            @Override
                            public List<Sprite> getSprites() {
                                return Collections.emptyList();
                            }

                            @Override
                            public Sprite getSprite(int age, int maxAge) {
                                return spriteSet.getSprite(age, maxAge);
                            }

                            @Override
                            public Sprite getSprite(Random random) {
                                return spriteSet.getSprite(random);
                            }
                        });
                    }
                });
            }
        });
    }

    @SubscribeEvent
    public static void onRegisterParticleFactories(ParticleFactoryRegisterEvent event) {
        for (Runnable particle : particles) {
            particle.run();
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Entity> void registerEntityRenderer(Supplier<? extends EntityType<? extends T>> type, final Function<EntityRenderDispatcher, EntityRenderer<T>> factory) {
        RenderingRegistry.registerEntityRenderingHandler((EntityType<T>) type.get(), new IRenderFactory<T>() {
            @Override
            public EntityRenderer<? super T> createRenderFor(EntityRenderDispatcher dispatcher) {
                return factory.apply(dispatcher);
            }
        });
    }

    public static <T extends BlockEntity> void registerBlockEntityRenderer(Supplier<BlockEntityType<T>> type, final CompatRegistryClient.BlockEntityRendererFactory<T> provider) {
        ClientRegistry.bindTileEntityRenderer(type.get(), new Function<BlockEntityRenderDispatcher, BlockEntityRenderer<? super T>>() {
            @Override
            public BlockEntityRenderer<? super T> apply(BlockEntityRenderDispatcher dispatcher) {
                return provider.create(new CompatRegistryClient.BlockEntityRendererFactory.Context(dispatcher));
            }
        });
    }

    public static void registerRenderTypeBlock(RenderLayer layer, Block block) {
        RenderLayers.setRenderLayer(block, layer);
    }

    public static void registerRenderTypeFluid(RenderLayer layer, Fluid fluid) {
        RenderLayers.setRenderLayer(fluid, layer);
    }
}
