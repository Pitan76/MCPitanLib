package net.pitan76.mcpitanlib.api.client.model.neoforge;

import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.render.entity.equipment.EquipmentModel;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.util.Identifier;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.component.type.EquippableComponent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.ClientHooks;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;
import net.pitan76.mcpitanlib.api.client.model.CompatArmorModelRegistry;
import net.pitan76.mcpitanlib.api.client.model.CompatModelBuilder;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

@EventBusSubscriber(modid = "mcpitanlib", value = Dist.CLIENT)
public class CompatArmorModelRegistryImpl {
    private static final List<Consumer<RegisterClientExtensionsEvent>> registrations = new CopyOnWriteArrayList<>();

    public static void registerImpl(CompatModelBuilder humanoidModel, CompatIdentifier texture, ItemWrapper... items) {
        // アイテムの登録は遅延されるため、イベント発火時に解決する
        registrations.add(event -> event.registerItem(new CustomModelArmorExtensions(humanoidModel, texture),
                Arrays.stream(items).map(ItemWrapper::get).toArray(Item[]::new)));
    }

    @SubscribeEvent
    public static void onRegisterClientExtensions(RegisterClientExtensionsEvent event) {
        for (Consumer<RegisterClientExtensionsEvent> reg : registrations) {
            reg.accept(event);
        }
    }

    private static class CustomModelArmorExtensions implements IClientItemExtensions {
        private final CompatModelBuilder humanoidModel;
        private final Identifier texture;
        // 描画は遅延して行われるため、部位ごとに別のモデルを持つ (ベイクは初回使用時)
        private final Map<EquipmentSlot, BipedEntityModel<?>> models = new EnumMap<>(EquipmentSlot.class);

        private CustomModelArmorExtensions(CompatModelBuilder humanoidModel, CompatIdentifier texture) {
            this.humanoidModel = humanoidModel;
            this.texture = texture.toMinecraft();
        }

        private static EquipmentSlot getSlot(ItemStack stack, EquipmentModel.LayerType layerType) {
            EquippableComponent equippable = stack.get(DataComponentTypes.EQUIPPABLE);
            if (equippable != null) return equippable.slot();
            return layerType == EquipmentModel.LayerType.HUMANOID_LEGGINGS ? EquipmentSlot.LEGS : EquipmentSlot.CHEST;
        }

        @Override
        public Model getGenericArmorModel(ItemStack stack, EquipmentModel.LayerType layerType, Model original) {
            EquipmentSlot slot = getSlot(stack, layerType);
            BipedEntityModel<?> model = models.computeIfAbsent(slot, s -> new BipedEntityModel<>(humanoidModel.bake()));

            if (original instanceof BipedEntityModel<?> originalHumanoid)
                ClientHooks.copyModelProperties(originalHumanoid, model);

            // copyModelProperties は visible もコピーするため、部位ごとの表示を設定し直す
            CompatArmorModelRegistry.applySlotVisibility(model, slot);
            return model;
        }

        @Override
        public Identifier getArmorTexture(ItemStack stack, EquipmentModel.LayerType layerType, EquipmentModel.Layer layer, Identifier _default) {
            return texture;
        }
    }
}
