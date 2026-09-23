package net.pitan76.mcpitanlib.api.client.model;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.world.entity.EquipmentSlot;
import net.pitan76.mcpitanlib.api.util.CompatIdentifier;
import net.pitan76.mcpitanlib.midohra.item.ItemWrapper;

/**
 * 防具を独自モデル (BlockBench などで作ったもの) で描画する
 * <pre>{@code
 * CompatArmorModelRegistry.register(RedMatterArmorModel.create(),
 *         CompatIdentifier.of("itemalchemy", "textures/models/armor/red_matter_armor.png"),
 *         Items.RED_MATTER_HELMET, Items.RED_MATTER_CHESTPLATE, Items.RED_MATTER_LEGGINGS, Items.RED_MATTER_BOOTS);
 * }</pre>
 * モデルのパーツ名は BlockBench のもの (Head, Body, RightArm, LeftArm, RightLeg, LeftLeg) でもバニラのもの (head, body, right_arm...) でもよい。
 * 装備部位ごとに表示するパーツは自動で切り替わる (頭: head / 胴: body, 両腕 / 脚・足: 両脚)。
 * クライアントの初期化時に呼ぶこと。
 */
public class CompatArmorModelRegistry {

    public static void register(CompatModelBuilder model, CompatIdentifier texture, ItemWrapper... items) {
        registerImpl(model.toHumanoid(), texture, items);
    }

    @ExpectPlatform
    public static void registerImpl(CompatModelBuilder humanoidModel, CompatIdentifier texture, ItemWrapper... items) {
        throw new AssertionError();
    }

    /**
     * 装備部位に応じて表示するパーツを切り替える (プラットフォーム実装用)
     */
    public static void applySlotVisibility(HumanoidModel<?> model, EquipmentSlot slot) {
        boolean head = slot == EquipmentSlot.HEAD;
        boolean chest = slot == EquipmentSlot.CHEST;
        boolean legs = slot == EquipmentSlot.LEGS || slot == EquipmentSlot.FEET;

        model.head.visible = head;
        model.hat.visible = head;
        model.body.visible = chest;
        model.rightArm.visible = chest;
        model.leftArm.visible = chest;
        model.rightLeg.visible = legs;
        model.leftLeg.visible = legs;
    }
}
