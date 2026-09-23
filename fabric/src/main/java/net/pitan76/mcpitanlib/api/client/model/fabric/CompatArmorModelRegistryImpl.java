package net.pitan76.mcpitanlib.api.client.model.fabric;

import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderingRegistry;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
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
        Item[] rawItems = Arrays.stream(items).map(ItemWrapper::get).toArray(Item[]::new);
        Identifier textureId = texture.toMinecraft();
        Map<EquipmentSlot, BipedEntityModel<LivingEntity>> models = new EnumMap<>(EquipmentSlot.class);

        ArmorRenderingRegistry.registerModel((entity, stack, slot, defaultModel) -> {
            BipedEntityModel<LivingEntity> model = models.computeIfAbsent(slot, humanoidModel::bakeHumanoid);
            defaultModel.setAttributes(model);
            CompatArmorModelRegistry.applySlotVisibility(model, slot);
            return model;
        }, rawItems);
        ArmorRenderingRegistry.registerTexture((entity, stack, slot, secondLayer, suffix, defaultTexture) -> textureId, rawItems);
    }
}
