package net.pitan76.mcpitanlib.api.client.registry.neoforge;

import net.minecraft.client.color.block.BlockTintSource;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;
import net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient;
import net.pitan76.mcpitanlib.api.client.render.EntityModelLayerContext;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

@EventBusSubscriber(modid = "mcpitanlib", value = Dist.CLIENT)
public class CompatRegistryClientImpl {
    private static final List<Consumer<RegisterMenuScreensEvent>> screenRegistrations = new ArrayList<>();
    private static final List<Consumer<RegisterParticleProvidersEvent>> particleRegistrations = new ArrayList<>();
    private static final List<Consumer<EntityRenderersEvent.RegisterLayerDefinitions>> layerRegistrations = new ArrayList<>();
    private static final List<Consumer<EntityRenderersEvent.RegisterRenderers>> rendererRegistrations = new ArrayList<>();

    public static Map<List<BlockTintSource>, Supplier<Block[]>> blockColorProviders = new HashMap<>();

    public static <H extends AbstractContainerMenu, S extends Screen & MenuAccess<H>> void registerScreen(String modId, Supplier<MenuType<? extends H>> type, CompatRegistryClient.ScreenFactory<H, S> factory) {
        // イベント発火時に解決する (登録時点ではまだ生成されていない)
        screenRegistrations.add(event -> event.register(type.get(), factory::create));
    }

    public static <T extends ParticleOptions> void registerParticle(Supplier<ParticleType<T>> type, ParticleProvider<T> factory) {
        particleRegistrations.add(event -> event.registerSpecial(type.get(), factory));
    }

    public static <T extends ParticleOptions> void registerParticle(Supplier<ParticleType<T>> type, CompatRegistryClient.DeferredParticleProvider<T> provider) {
        particleRegistrations.add(event -> event.registerSpriteSet(type.get(), spriteSet -> provider.create(new CompatRegistryClient.ExtendedSpriteSet() {
            @Override
            public TextureAtlas getAtlas() { return null; }
            @Override
            public List<TextureAtlasSprite> getSprites() { return Collections.emptyList(); }
            @Override
            public TextureAtlasSprite get(int age, int maxAge) { return spriteSet.get(age, maxAge); }
            @Override
            public TextureAtlasSprite get(RandomSource random) { return spriteSet.get(random); }
            @Override
            public TextureAtlasSprite first() { return spriteSet.first(); }
        })));
    }

    public static void registerEntityModelLayer(ModelLayerLocation layer, EntityModelLayerContext context) {
        layerRegistrations.add(event -> event.registerLayerDefinition(layer, () -> LayerDefinition.create(context.getData(), context.getWidth(), context.getHeight())));
    }

    public static <T extends Entity> void registerEntityRenderer(Supplier<? extends EntityType<? extends T>> type, EntityRendererProvider<T> provider) {
        rendererRegistrations.add(event -> event.registerEntityRenderer(type.get(), provider));
    }

    public static <T extends BlockEntity> void registerBlockEntityRenderer(Supplier<BlockEntityType<T>> type, CompatRegistryClient.BlockEntityRendererFactory<T, BlockEntityRenderState> provider) {
        rendererRegistrations.add(event -> event.registerBlockEntityRenderer(type.get(), ctx -> provider.create(new CompatRegistryClient.BlockEntityRendererFactory.Context(
                ctx.blockEntityRenderDispatcher(), ctx.blockModelResolver(), ctx.itemModelResolver(), ctx.entityRenderer(), ctx.entityModelSet(), ctx.font(), ctx.sprites(), ctx.playerSkinRenderCache()
        ))));
    }

    public static <T extends BlockEntity> void registerCompatBlockEntityRenderer(Supplier<BlockEntityType<T>> type, CompatRegistryClient.BlockEntityRendererFactory<T, BlockEntityRenderState> provider) {
        registerBlockEntityRenderer(type, provider);
    }

    public static void registerColorProviderBlock(List<BlockTintSource> provider, Supplier<Block[]> blocks) {
        blockColorProviders.put(provider, blocks);
    }

    @SubscribeEvent
    public static void onRegisterMenuScreens(RegisterMenuScreensEvent event) {
        for (Consumer<RegisterMenuScreensEvent> reg : screenRegistrations) {
            reg.accept(event);
        }
    }

    @SubscribeEvent
    public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
        for (Consumer<RegisterParticleProvidersEvent> reg : particleRegistrations) {
            reg.accept(event);
        }
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        for (Consumer<EntityRenderersEvent.RegisterLayerDefinitions> reg : layerRegistrations) {
            reg.accept(event);
        }
    }

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        for (Consumer<EntityRenderersEvent.RegisterRenderers> reg : rendererRegistrations) {
            reg.accept(event);
        }
    }

    @SubscribeEvent
    public static void onRegisterBlockColors(RegisterColorHandlersEvent.BlockTintSources event){
        if (blockColorProviders.isEmpty()) return;

        for (Map.Entry<List<BlockTintSource>, Supplier<Block[]>> entry : blockColorProviders.entrySet()) {
            List<BlockTintSource> provider = entry.getKey();
            Block[] blocks = entry.getValue().get();

            if (blocks == null || blocks.length == 0) {
                event.register(provider);
            } else {
                event.register(provider, blocks);
            }
        }
    }
}
