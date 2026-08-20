package net.pitan76.mcpitanlib.api.client.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.ingame.ScreenHandlerProvider;
import net.minecraft.client.particle.ParticleFactory;
import net.minecraft.client.particle.SpriteProvider;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.texture.SpriteAtlasTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleType;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.MCPitanLib;
import net.pitan76.mcpitanlib.api.client.color.CompatBlockColorProvider;
import net.pitan76.mcpitanlib.api.client.gui.screen.SimpleHandledScreen;
import net.pitan76.mcpitanlib.api.client.render.CompatRenderLayer;
import net.pitan76.mcpitanlib.api.client.render.EntityModelLayerContext;
import net.pitan76.mcpitanlib.api.util.client.ClientUtil;
import net.pitan76.mcpitanlib.api.gui.SimpleScreenHandler;
import net.pitan76.mcpitanlib.api.text.TextComponent;
import net.pitan76.mcpitanlib.api.util.inventory.CompatPlayerInventory;

import java.util.List;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class CompatRegistryClient {
    public static <H extends ScreenHandler, S extends Screen & ScreenHandlerProvider<H>> void registerScreen(ScreenHandlerType<? extends H> type, ScreenFactory<H, S> factory) {
        registerScreen(MCPitanLib.MOD_ID, type, factory);
    }

    public static <H extends SimpleScreenHandler, S extends SimpleHandledScreen<H> & ScreenHandlerProvider<H>> void registerScreen(ScreenHandlerType<? extends H> type, ScreenFactory2<H, S> factory) {
        registerScreen(MCPitanLib.MOD_ID, type, factory);
    }

    public static <H extends ScreenHandler, S extends Screen & ScreenHandlerProvider<H>> void registerScreen(String modId, ScreenHandlerType<? extends H> type, ScreenFactory<H, S> factory) {
        registerScreen(modId, () -> type, factory);
    }

    @ExpectPlatform
    public static <H extends ScreenHandler, S extends Screen & ScreenHandlerProvider<H>> void registerScreen(String modId, Supplier<ScreenHandlerType<? extends H>> type, ScreenFactory<H, S> factory) {
        throw new AssertionError();
    }

    public static <H extends SimpleScreenHandler, S extends SimpleHandledScreen<H> & ScreenHandlerProvider<H>> void registerScreen(String modId, ScreenHandlerType<? extends H> type, ScreenFactory2<H, S> factory) {
        registerScreen(modId, type, (ScreenFactory<H, S>) factory);
    }

    public interface ScreenFactory<H extends ScreenHandler, S extends Screen & ScreenHandlerProvider<H>> {
        S create(H handler, PlayerInventory inventory, Text text);
    }

    public interface ScreenFactory2<H extends SimpleScreenHandler, S extends SimpleHandledScreen<H> & ScreenHandlerProvider<H>> extends ScreenFactory<H, S> {
        @Override
        default S create(H handler, PlayerInventory inventory, Text text) {
            return create(handler, new CompatPlayerInventory(inventory), new TextComponent(text));
        }

        S create(H handler, CompatPlayerInventory inventory, TextComponent text);
    }

    @ExpectPlatform
    public static <T extends ParticleEffect> void registerParticle(ParticleType<T> type, ParticleFactory<T> factory) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends ParticleEffect> void registerParticle(ParticleType<T> type, ArchRegistryClient.DeferredParticleProvider<T> provider) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends Entity> void registerEntityRenderer(Supplier<? extends EntityType<? extends T>> type, Function<EntityRenderDispatcher, EntityRenderer<T>> factory) {
        throw new AssertionError();
    }

    @FunctionalInterface
    public interface DeferredParticleProvider<T extends ParticleEffect> {
        ParticleFactory<T> create(ExtendedSpriteSet spriteSet);
    }

    public interface ExtendedSpriteSet extends SpriteProvider {
        SpriteAtlasTexture getAtlas();

        List<Sprite> getSprites();
    }

    public static void registryClientSpriteAtlasTexture(Identifier identifier) {
        //registryClientSprite(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, identifier);
    }

    public static void registryClientSpriteAtlasTexture(Sprite sprite) {
        //registryClientSprite(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE, sprite);
    }

    public static void registryClientSprite(Identifier atlasId, Identifier identifier) {
        // ～1.19.2
    }

    public static void registryClientSprite(Identifier atlasId, Sprite sprite) {
        // ～1.19.2
    }

    public static <T extends BlockEntity> void registerBlockEntityRenderer(BlockEntityType<T> type, BlockEntityRendererFactory<T> provider) {
        registerBlockEntityRenderer(() -> type, provider);
    }

    @ExpectPlatform
    public static <T extends BlockEntity> void registerBlockEntityRenderer(Supplier<BlockEntityType<T>> type, BlockEntityRendererFactory<T> provider) {
        throw new AssertionError();
    }

    @FunctionalInterface
    public interface BlockEntityRendererFactory<T extends BlockEntity> {
        BlockEntityRenderer<T> create(BlockEntityRendererFactory.Context ctx);

        class Context {
            private final BlockEntityRenderDispatcher renderDispatcher;

            public Context(BlockEntityRenderDispatcher renderDispatcher) {
                this.renderDispatcher = renderDispatcher;
            }

            public BlockEntityRenderDispatcher getRenderDispatcher() {
                return this.renderDispatcher;
            }
        }
    }

    @ExpectPlatform
    public static void registerRenderTypeBlock(RenderLayer layer, Block block) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerRenderTypeFluid(RenderLayer layer, Fluid fluid) {
        throw new AssertionError();
    }

    public static void registerCutoutBlock(Block block) {
        registerRenderTypeBlock(RenderLayer.getCutout(), block);
    }

    public static <T extends BlockEntity> void registerCompatBlockEntityRenderer(BlockEntityType<T> type, BlockEntityRendererFactory<T> provider) {
        registerBlockEntityRenderer(type, provider);
    }

    public static void registerRenderTypeBlock(CompatRenderLayer layer, Block block) {
        registerRenderTypeBlock(layer.layer, block);
    }

    public static void registerRenderTypeFluid(CompatRenderLayer layer, Fluid fluid) {
        registerRenderTypeFluid(layer.layer, fluid);
    }

    @ExpectPlatform
    public static void registerColorProviderBlock(BlockColorProvider provider, Block... blocks) {
        throw new AssertionError("This method should be replaced by the platform implementation");
    }

    public static void registerColorProviderBlock(CompatBlockColorProvider provider, Block... blocks) {
        registerColorProviderBlock((BlockColorProvider) provider, blocks);
    }
}
