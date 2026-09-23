package net.pitan76.mcpitanlib.api.client.model.fabric;

import com.mojang.blaze3d.vertex.PoseStack;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.pitan76.mcpitanlib.api.client.model.CompatArmorModelRegistry;
import net.pitan76.mcpitanlib.api.client.model.CompatModelBuilder;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class CompatArmorModelRegistryImpl {

    public static void registerImpl(CompatModelBuilder humanoidModel, CompatIdentifier texture, ItemWrapper... items) {
        ItemLike[] itemLikes = Arrays.stream(items).map(ItemWrapper::get).toArray(ItemLike[]::new);
        ArmorRenderer.register(ctx -> new CustomModelArmorRenderer(humanoidModel, texture), itemLikes);
    }

    private static class CustomModelArmorRenderer implements ArmorRenderer {
        // 描画は遅延して行われるため、部位ごとに表示パーツを固定したモデルを別々に持つ
        private final Map<EquipmentSlot, HumanoidModel<HumanoidRenderState>> models = new EnumMap<>(EquipmentSlot.class);
        private final RenderType renderType;

        private CustomModelArmorRenderer(CompatModelBuilder humanoidModel, CompatIdentifier texture) {
            for (EquipmentSlot slot : new EquipmentSlot[]{EquipmentSlot.HEAD, EquipmentSlot.CHEST, EquipmentSlot.LEGS, EquipmentSlot.FEET}) {
                HumanoidModel<HumanoidRenderState> model = new HumanoidModel<>(humanoidModel.bake());
                CompatArmorModelRegistry.applySlotVisibility(model, slot);
                models.put(slot, model);
            }
            this.renderType = RenderTypes.armorCutoutNoCull(texture.toMinecraft());
        }

        @Override
        public void render(PoseStack matrices, SubmitNodeCollector collector, ItemStack stack, HumanoidRenderState state, EquipmentSlot slot, int light, HumanoidModel<HumanoidRenderState> contextModel) {
            HumanoidModel<HumanoidRenderState> model = models.get(slot);
            if (model == null) return;

            ArmorRenderer.submitTransformCopyingModel(contextModel, state, model, state, false, collector, matrices, renderType, light, OverlayTexture.NO_OVERLAY, -1, null, state.outlineColor, null);
        }
    }
}
