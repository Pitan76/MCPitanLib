package net.pitan76.mcpitanlib.api.client.registry.neoforge;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.render.RenderLayer;
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
import net.minecraft.util.math.random.Random;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ConcurrentHashMap;

@EventBusSubscriber(modid = MCPitanLib.MOD_ID, value = Dist.CLIENT)
public class CompatRegistryClientImpl {

    public static Map<BlockColorProvider, Supplier<Block[]>> blockColorProviders = new ConcurrentHashMap<>();

    public static void registerColorProviderBlock(BlockColorProvider provider, Supplier<Block[]> blocks) {
        blockColorProviders.put(provider, blocks);
    }

    @SubscribeEvent
    public static void registerBlockColors(RegisterColorHandlersEvent.Block event) {
        if (blockColorProviders.isEmpty()) return;

        for (Map.Entry<BlockColorProvider, Supplier<Block[]>> entry : blockColorProviders.entrySet()) {
            BlockColorProvider provider = entry.getKey();
            Block[] blocks = entry.getValue().get();

            if (blocks == null || blocks.length == 0) {
                event.register(provider);
            } else {
                event.register(provider, blocks);
            }
        }
    }

    // ---- Screen ----

    private static final List<Consumer<RegisterMenuScreensEvent>> screens = new CopyOnWriteArrayList<>();

    public static <H extends ScreenHandler, S extends Screen & ScreenHandlerProvider<H>> void registerScreen(String modId, Supplier<ScreenHandlerType<? extends H>> type, CompatRegistryClient.ScreenFactory<H, S> factory) {
        // イベント発火時に解決する (登録時点ではまだ生成されていない)
        screens.add(event -> event.register(type.get(), factory::create));
    }

    @SubscribeEvent
    public static void onRegisterMenuScreens(RegisterMenuScreensEvent event) {
        for (Consumer<RegisterMenuScreensEvent> screen : screens) {
            screen.accept(event);
        }
    }

    // ---- Particle ----

    private static final List<Consumer<RegisterParticleProvidersEvent>> particles = new CopyOnWriteArrayList<>();

    public static <T extends ParticleEffect> void registerParticle(Supplier<ParticleType<T>> type, ParticleFactory<T> factory) {
        particles.add(event -> event.registerSpecial(type.get(), factory));
    }

    public static <T extends ParticleEffect> void registerParticle(Supplier<ParticleType<T>> type, CompatRegistryClient.DeferredParticleProvider<T> provider) {
        particles.add(event -> event.registerSpriteSet(type.get(), spriteSet -> provider.create(wrap(spriteSet))));
    }

    /**
     * NeoForgeのスプライトセットはアトラスやスプライト一覧を公開していないため、
     * getAtlas()はnull、getSprites()は空リストを返す。
     */
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
    public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
        for (Consumer<RegisterParticleProvidersEvent> particle : particles) {
            particle.accept(event);
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
        // 1.21.5+ NeoForgeではモデルJSONまたはクライアントセットアップで制御
    }

    public static void registerRenderTypeFluid(RenderLayer layer, Fluid fluid) {
        // 1.21.5+ NeoForgeではモデルJSONまたはクライアントセットアップで制御
    }
}
