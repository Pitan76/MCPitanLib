package net.pitan76.mcpitanlib.api.item.consume;

import net.minecraft.util.UseAction;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class CompatUseAction implements CompatStringIdentifiable {
    private final UseAction useAction;

    public static final CompatUseAction NONE = of(UseAction.NONE);
    public static final CompatUseAction EAT = of(UseAction.EAT);
    public static final CompatUseAction DRINK = of(UseAction.DRINK);
    public static final CompatUseAction BLOCK = of(UseAction.BLOCK);
    public static final CompatUseAction BOW = of(UseAction.BOW);
    public static final CompatUseAction SPEAR = of(UseAction.SPEAR);
    public static final CompatUseAction CROSSBOW = of(UseAction.CROSSBOW);
    public static final CompatUseAction SPYGLASS = of(UseAction.SPYGLASS);
    public static final CompatUseAction TOOT_HORN = of(UseAction.TOOT_HORN);
    public static final CompatUseAction BRUSH = of(UseAction.BRUSH);

    public CompatUseAction(UseAction useAction) {
        this.useAction = useAction;
    }

    public static CompatUseAction of(UseAction useAction) {
        return new CompatUseAction(useAction);
    }

    @Deprecated
    public UseAction getUseAction() {
        return useAction;
    }

    public int getId() {
        return -1;
    }

    public String getName() {
        return useAction.name();
    }

    @Override
    public String asString_compat() {
        return getName();
    }
}
