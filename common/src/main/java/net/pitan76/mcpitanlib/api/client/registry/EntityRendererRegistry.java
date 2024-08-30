package net.pitan76.mcpitanlib.api.client.registry;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.EntityType;

import java.util.function.Supplier;

public class EntityRendererRegistry {
    public static void registerEntityRendererAsFlyingItem(Supplier<EntityType<?>> entityType) {
        CompatRegistryClient.registerEntityRenderer(entityType, dispatcher -> (EntityRenderer) new FlyingItemEntityRenderer<>(dispatcher, MinecraftClient.getInstance().getItemRenderer()));
    }
}
