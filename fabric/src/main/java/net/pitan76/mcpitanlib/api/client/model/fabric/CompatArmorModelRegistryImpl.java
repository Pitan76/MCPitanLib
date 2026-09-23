package net.pitan76.mcpitanlib.api.client.model.fabric;

import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.util.Identifier;
import net.pitan76.mcpitanlib.api.client.model.CompatArmorModelRegistry;
import net.pitan76.mcpitanlib.api.client.model.CompatModelBuilder;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class CompatArmorModelRegistryImpl {

    public static void registerImpl(CompatModelBuilder humanoidModel, CompatIdentifier texture, ItemWrapper... items) {
        ItemConvertible[] itemConvertibles = Arrays.stream(items).map(ItemWrapper::get).toArray(ItemConvertible[]::new);
        Identifier textureId = texture.toMinecraft();
        Map<EquipmentSlot, BipedEntityModel<LivingEntity>> models = new EnumMap<>(EquipmentSlot.class);

        ArmorRenderer.register((matrices, vertexConsumers, stack, entity, slot, light, contextModel) -> {
            BipedEntityModel<LivingEntity> model = models.computeIfAbsent(slot, s -> new BipedEntityModel<>(humanoidModel.bake()));
            contextModel.setAttributes(model);
            CompatArmorModelRegistry.applySlotVisibility(model, slot);
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, textureId);
        }, itemConvertibles);
    }
}
