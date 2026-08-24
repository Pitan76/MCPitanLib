package net.pitan76.mcpitanlib.api.item.v3;

import net.minecraft.block.Block;
import net.minecraft.item.ToolMaterial;
import net.minecraft.registry.tag.TagKey;
import net.pitan76.mcpitanlib.api.tag.item.RepairIngredientTag;

public class VanillaCompatToolMaterial implements CompatToolMaterial {
    private final ToolMaterial material;

    public static final VanillaCompatToolMaterial WOOD = of(ToolMaterial.WOOD);
    public static final VanillaCompatToolMaterial STONE = of(ToolMaterial.STONE);
    public static final VanillaCompatToolMaterial IRON = of(ToolMaterial.IRON);
    public static final VanillaCompatToolMaterial GOLD = of(ToolMaterial.GOLD);
    public static final VanillaCompatToolMaterial DIAMOND = of(ToolMaterial.DIAMOND);
    public static final VanillaCompatToolMaterial NETHERITE = of(ToolMaterial.NETHERITE);

    protected VanillaCompatToolMaterial(ToolMaterial material) {
        this.material = material;
    }

    private static VanillaCompatToolMaterial of(ToolMaterial material) {
        return new VanillaCompatToolMaterial(material);
    }

    @Override
    public RepairIngredientTag getRepairIngredientTag() {
        return new RepairIngredientTag(material.repairItems());
    }

    @Override
    public int getCompatDurability() {
        return material.durability();
    }

    @Override
    public float getCompatMiningSpeedMultiplier() {
        return material.speed();
    }

    @Override
    public float getCompatAttackDamage() {
        return material.attackDamageBonus();
    }

    @Override
    public int getCompatMiningLevel() {
        TagKey<Block> tag = material.incorrectBlocksForDrops();
        if (tag == ToolMaterial.WOOD.incorrectBlocksForDrops()) return 0;
        if (tag == ToolMaterial.STONE.incorrectBlocksForDrops()) return 1;
        if (tag == ToolMaterial.IRON.incorrectBlocksForDrops()) return 2;
        if (tag == ToolMaterial.GOLD.incorrectBlocksForDrops()) return 0;
        if (tag == ToolMaterial.DIAMOND.incorrectBlocksForDrops()) return 3;
        if (tag == ToolMaterial.NETHERITE.incorrectBlocksForDrops()) return 4;
        return -1;
    }

    @Override
    public int getCompatEnchantability() {
        return material.enchantmentValue();
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
