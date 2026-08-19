package net.pitan76.mcpitanlib.api.client.registry.fabric;

import net.fabricmc.fabric.api.client.particle.v1.FabricSpriteProvider;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.particle.ParticleFactory;
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
import net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient;

import java.util.List;
import java.util.function.Supplier;

public class CompatRegistryClientImpl {
    public static <H extends ScreenHandler, S extends Screen & ScreenHandlerProvider<H>> void registerScreen(String modId, Supplier<ScreenHandlerType<? extends H>> type, CompatRegistryClient.ScreenFactory<H, S> factory) {
        HandledScreens.register(type.get(), factory::create);
    }

    public static void registerColorProviderBlock(BlockColorProvider provider, Supplier<Block[]> blocks) {
        Block[] array = blocks.get();
        if (array == null || array.length == 0) {
            ColorProviderRegistry.BLOCK.register(provider);
        } else {
            ColorProviderRegistry.BLOCK.register(provider, array);
        }
    }

    public static <T extends ParticleEffect> void registerParticle(Supplier<ParticleType<T>> type, ParticleFactory<T> factory) {
        ParticleFactoryRegistry.getInstance().register(type.get(), factory);
    }

    public static <T extends ParticleEffect> void registerParticle(Supplier<ParticleType<T>> type, CompatRegistryClient.DeferredParticleProvider<T> provider) {
        ParticleFactoryRegistry.getInstance().register(type.get(), (FabricSpriteProvider spriteSet) -> provider.create(new CompatRegistryClient.ExtendedSpriteSet() {
            @Override
            public SpriteAtlasTexture getAtlas() {
                return spriteSet.getAtlas();
            }

            @Override
            public List<Sprite> getSprites() {
                return spriteSet.getSprites();
            }

            @Override
            public Sprite getSprite(int age, int maxAge) {
                return spriteSet.getSprite(age, maxAge);
            }

            @Override
            public Sprite getSprite(Random random) {
                return spriteSet.getSprite(random);
            }

        }));
    }

    public static void registerEntityModelLayer(EntityModelLayer layer, Supplier<TexturedModelData> supplier) {
        EntityModelLayerRegistry.registerModelLayer(layer, supplier::get);
    }

    public static <T extends Entity> void registerEntityRenderer(Supplier<? extends EntityType<? extends T>> type, EntityRendererFactory<T> provider) {
        EntityRendererRegistry.register(type.get(), provider);
    }

    public static <T extends BlockEntity> void registerBlockEntityRendererRaw(Supplier<BlockEntityType<T>> type, BlockEntityRendererFactory<T> factory) {
        BlockEntityRendererRegistry.register(type.get(), factory);
    }

    public static void registerRenderTypeBlock(RenderLayer layer, Block block) {
        BlockRenderLayerMap.INSTANCE.putBlock(block, layer);
    }

    public static void registerRenderTypeFluid(RenderLayer layer, Fluid fluid) {
        BlockRenderLayerMap.INSTANCE.putFluid(fluid, layer);
    }
}
