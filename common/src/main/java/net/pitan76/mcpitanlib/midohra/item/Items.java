package net.pitan76.mcpitanlib.midohra.item;

public class Items {

    public static ItemWrapper AIR = of(net.minecraft.item.Items.AIR);
    public static ItemWrapper DIRT = of(net.minecraft.item.Items.DIRT);
    public static ItemWrapper GRASS_BLOCK = of(net.minecraft.item.Items.GRASS_BLOCK);
    public static ItemWrapper STONE = of(net.minecraft.item.Items.STONE);
    public static ItemWrapper SAND = of(net.minecraft.item.Items.SAND);
    public static ItemWrapper COBBLESTONE = of(net.minecraft.item.Items.COBBLESTONE);
    public static ItemWrapper OAK_LOG = of(net.minecraft.item.Items.OAK_LOG);
    public static ItemWrapper OAK_PLANKS = of(net.minecraft.item.Items.OAK_PLANKS);
    public static ItemWrapper OAK_LEAVES = of(net.minecraft.item.Items.OAK_LEAVES);
    public static ItemWrapper WHITE_WOOL = of(net.minecraft.item.Items.WHITE_WOOL);
    public static ItemWrapper GLASS = of(net.minecraft.item.Items.GLASS);

    public static ItemWrapper of(net.minecraft.item.Item item) {
        return ItemWrapper.of(item);
    }
}
