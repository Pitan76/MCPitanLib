package net.pitan76.mcpitanlib.api.item.consume;

import net.minecraft.world.item.ItemUseAnimation;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class CompatUseAction implements CompatStringIdentifiable {
    private final ItemUseAnimation useAction;

    public static final CompatUseAction NONE = of(ItemUseAnimation.NONE);
    public static final CompatUseAction EAT = of(ItemUseAnimation.EAT);
    public static final CompatUseAction DRINK = of(ItemUseAnimation.DRINK);
    public static final CompatUseAction BLOCK = of(ItemUseAnimation.BLOCK);
    public static final CompatUseAction BOW = of(ItemUseAnimation.BOW);
    public static final CompatUseAction SPEAR = of(ItemUseAnimation.SPEAR);
    public static final CompatUseAction CROSSBOW = of(ItemUseAnimation.CROSSBOW);
    public static final CompatUseAction SPYGLASS = of(ItemUseAnimation.SPYGLASS);
    public static final CompatUseAction TOOT_HORN = of(ItemUseAnimation.TOOT_HORN);
    public static final CompatUseAction BRUSH = of(ItemUseAnimation.BRUSH);

    public CompatUseAction(ItemUseAnimation useAction) {
        this.useAction = useAction;
    }

    public static CompatUseAction of(ItemUseAnimation useAction) {
        return new CompatUseAction(useAction);
    }

    @Deprecated
    public ItemUseAnimation get() {
        return useAction;
    }

    public int getId() {
        return useAction.getId();
    }

    public String getName() {
        return useAction.getSerializedName();
    }

    @Override
    public String asString_compat() {
        return getName();
    }
}
