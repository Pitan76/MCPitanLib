package net.pitan76.mcpitanlib.api.client.model.fabric;

import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.command.OrderedRenderCommandQueue;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.render.entity.state.BipedEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
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
        ArmorRenderer.register(ctx -> new CustomModelArmorRenderer(humanoidModel, texture), itemConvertibles);
    }

    private static class CustomModelArmorRenderer implements ArmorRenderer {
        // 描画は遅延して行われるため、部位ごとに表示パーツを固定したモデルを別々に持つ
        private final Map<EquipmentSlot, BipedEntityModel<BipedEntityRenderState>> models = new EnumMap<>(EquipmentSlot.class);
        private final RenderLayer renderLayer;

        private CustomModelArmorRenderer(CompatModelBuilder humanoidModel, CompatIdentifier texture) {
            for (EquipmentSlot slot : new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET}) {
                BipedEntityModel<BipedEntityRenderState> model = new BipedEntityModel<>(humanoidModel.bake());
                CompatArmorModelRegistry.applySlotVisibility(model, slot);
                models.put(slot, model);
            }
            this.renderLayer = RenderLayers.armorCutoutNoCull(texture.toMinecraft());
        }

        @Override
        public void render(MatrixStack matrices, OrderedRenderCommandQueue queue, ItemStack stack, BipedEntityRenderState state, EquipmentSlot slot, int light, BipedEntityModel<BipedEntityRenderState> contextModel) {
            BipedEntityModel<BipedEntityRenderState> model = models.get(slot);
            if (model == null) return;

            // 予約されたモデルは描画時に setAngles(state) で姿勢が計算されるため、姿勢のコピーは不要
            queue.submitModel(model, state, matrices, renderLayer, light, OverlayTexture.DEFAULT_UV, state.outlineColor, null);
        }
    }
}
