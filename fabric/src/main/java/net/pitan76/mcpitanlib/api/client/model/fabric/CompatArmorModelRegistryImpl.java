package net.pitan76.mcpitanlib.api.client.model.fabric;

import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.entity.EquipmentSlot;
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
        Map<EquipmentSlot, BipedEntityModel<BipedEntityRenderState>> models = new EnumMap<>(EquipmentSlot.class);

        ArmorRenderer.register((matrices, vertexConsumers, stack, state, slot, light, contextModel) -> {
            BipedEntityModel<BipedEntityRenderState> model = models.computeIfAbsent(slot, s -> {
                BipedEntityModel<BipedEntityRenderState> m = new BipedEntityModel<>(humanoidModel.bake());
                CompatArmorModelRegistry.applySlotVisibility(m, s);
                return m;
            });
            contextModel.copyTransforms(model);
            ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, textureId);
        }, itemConvertibles);
    }
}
