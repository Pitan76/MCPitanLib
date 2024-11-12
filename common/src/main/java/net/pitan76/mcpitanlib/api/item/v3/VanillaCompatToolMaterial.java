package net.pitan76.mcpitanlib.api.item.v3;

import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.pitan76.mcpitanlib.api.tag.item.RepairIngredientTag;

public class VanillaCompatToolMaterial implements CompatToolMaterial {
    private final ToolMaterial material;

    public static final VanillaCompatToolMaterial WOOD = of(ToolMaterials.WOOD);
    public static final VanillaCompatToolMaterial STONE = of(ToolMaterials.STONE);
    public static final VanillaCompatToolMaterial IRON = of(ToolMaterials.IRON);
    public static final VanillaCompatToolMaterial GOLD = of(ToolMaterials.GOLD);
    public static final VanillaCompatToolMaterial DIAMOND = of(ToolMaterials.DIAMOND);
    public static final VanillaCompatToolMaterial NETHERITE = of(ToolMaterials.NETHERITE);

    protected VanillaCompatToolMaterial(ToolMaterial material) {
        this.material = material;
    }

    private static VanillaCompatToolMaterial of(ToolMaterial material) {
        return new VanillaCompatToolMaterial(material);
    }

    @Override
    public RepairIngredientTag getRepairIngredientTag() {
        if (material == ToolMaterials.STONE) return RepairIngredientTag.STONE_TOOL_MATERIALS;
        if (material == ToolMaterials.IRON) return RepairIngredientTag.IRON_TOOL_MATERIALS;
        if (material == ToolMaterials.GOLD) return RepairIngredientTag.GOLDEN_TOOL_MATERIALS;
        if (material == ToolMaterials.DIAMOND) return RepairIngredientTag.DIAMOND_TOOL_MATERIALS;
        if (material == ToolMaterials.NETHERITE) return RepairIngredientTag.NETHERITE_TOOL_MATERIALS;
        return RepairIngredientTag.WOODEN_TOOL_MATERIALS;
    }

    @Override
    public int getCompatDurability() {
        return material.getDurability();
    }

    @Override
    public float getCompatMiningSpeedMultiplier() {
        return material.getMiningSpeedMultiplier();
    }

    @Override
    public float getCompatAttackDamage() {
        return material.getAttackDamage();
    }

    @Override
    public int getCompatMiningLevel() {
        return material.getMiningLevel();
    }

    @Override
    public int getCompatEnchantability() {
        return material.getEnchantability();
    }

    @Deprecated
    public ToolMaterial toMinecraft() {
        return material;
    }

    @Override
    public ToolMaterial build() {
        return material;
    }
}
