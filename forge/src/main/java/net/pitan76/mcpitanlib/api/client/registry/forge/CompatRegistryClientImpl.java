package net.pitan76.mcpitanlib.api.client.registry.forge;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.fluid.Fluid;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import java.util.Random;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.client.event.TextureStitchEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;

@EventBusSubscriber(modid = MCPitanLib.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class CompatRegistryClientImpl {

    public static Map<BlockColorProvider, Supplier<Block[]>> blockColorProviders = new ConcurrentHashMap<>();

    public static void registerColorProviderBlock(BlockColorProvider provider, Supplier<Block[]> blocks) {
        blockColorProviders.put(provider, blocks);
    }

    @SubscribeEvent
    public static void registerBlockColors(ColorHandlerEvent.Block event){
        if (blockColorProviders.isEmpty()) return;

        for (Map.Entry<BlockColorProvider, Supplier<Block[]>> entry : blockColorProviders.entrySet()) {
            BlockColorProvider provider = entry.getKey();
            Block[] blocks = entry.getValue().get();

            if (blocks == null || blocks.length == 0) {
                event.getBlockColors().registerColorProvider(provider);
            } else {
                event.getBlockColors().registerColorProvider(provider, blocks);
            }
        }
    }

    // ---- Screen ----

    private static final List<Runnable> screens = new CopyOnWriteArrayList<>();
    private static volatile boolean clientSetupDone = false;

    public static <H extends ScreenHandler, S extends Screen & ScreenHandlerProvider<H>> void registerScreen(String modId, Supplier<ScreenHandlerType<? extends H>> type, CompatRegistryClient.ScreenFactory<H, S> factory) {
        Runnable task = () -> HandledScreens.register(type.get(), factory::create);

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
        event.enqueueWork(() -> {
            for (Runnable screen : screens) {
                screen.run();
            }

            screens.clear();
            clientSetupDone = true;
        });
    }

    // ---- Particle ----

    private static final List<Runnable> particles = new CopyOnWriteArrayList<>();

    public static <T extends ParticleEffect> void registerParticle(Supplier<ParticleType<T>> type, ParticleFactory<T> factory) {
        particles.add(() -> MinecraftClient.getInstance().particleManager.registerFactory(type.get(), factory));
    }

    public static <T extends ParticleEffect> void registerParticle(Supplier<ParticleType<T>> type, CompatRegistryClient.DeferredParticleProvider<T> provider) {
        particles.add(() -> MinecraftClient.getInstance().particleManager.registerFactory(type.get(), spriteSet -> provider.create(wrap(spriteSet))));
    }

    private static CompatRegistryClient.ExtendedSpriteSet wrap(SpriteProvider spriteSet) {
        return new CompatRegistryClient.ExtendedSpriteSet() {
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
        };
    }

    @SubscribeEvent
    public static void onRegisterParticleProviders(ParticleFactoryRegisterEvent event) {
        for (Runnable particle : particles) {
            particle.run();
        }
    }

    // ---- EntityModelLayer ----

    private static final Map<EntityModelLayer, Supplier<TexturedModelData>> modelLayers = new ConcurrentHashMap<>();

    public static void registerEntityModelLayer(EntityModelLayer layer, Supplier<TexturedModelData> supplier) {
        modelLayers.put(layer, supplier);
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        for (Map.Entry<EntityModelLayer, Supplier<TexturedModelData>> entry : modelLayers.entrySet()) {
            event.registerLayerDefinition(entry.getKey(), entry.getValue()::get);
        }
    }

    // ---- Renderer ----

    private static final List<Consumer<EntityRenderersEvent.RegisterRenderers>> renderers = new CopyOnWriteArrayList<>();

    public static <T extends Entity> void registerEntityRenderer(Supplier<? extends EntityType<? extends T>> type, EntityRendererFactory<T> provider) {
        renderers.add(event -> event.registerEntityRenderer(type.get(), provider));
    }

    public static <T extends BlockEntity> void registerBlockEntityRendererRaw(Supplier<BlockEntityType<T>> type, BlockEntityRendererFactory<T> factory) {
        renderers.add(event -> event.registerBlockEntityRenderer(type.get(), factory));
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        for (Consumer<EntityRenderersEvent.RegisterRenderers> renderer : renderers) {
            renderer.accept(event);
        }
    }

    // ---- RenderLayer ----

    public static void registerRenderTypeBlock(RenderLayer layer, Block block) {
        RenderLayers.setRenderLayer(block, layer);
    }

    public static void registerRenderTypeFluid(RenderLayer layer, Fluid fluid) {
        RenderLayers.setRenderLayer(fluid, layer);
    }

    private static final Map<Identifier, List<Identifier>> sprites = new ConcurrentHashMap<>();

    public static void registryClientSprite(Identifier atlasId, Identifier identifier) {
        sprites.computeIfAbsent(atlasId, k -> new CopyOnWriteArrayList<>()).add(identifier);
    }

    public static void registryClientSprite(Identifier atlasId, Sprite sprite) {
        registryClientSprite(atlasId, sprite.getId());
    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
        List<Identifier> ids = sprites.get(event.getAtlas().getId());
        if (ids == null) return;

        for (Identifier id : ids) {
            event.addSprite(id);
        }
    }
}
