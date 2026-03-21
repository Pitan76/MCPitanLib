package net.pitan76.mcpitanlib.api.util.client.render;

import net.minecraft.item.ItemDisplayContext;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class CompatItemDisplayContext implements CompatStringIdentifiable {
    private final ItemDisplayContext context;

    public static final CompatItemDisplayContext NONE = of(ItemDisplayContext.NONE);
    public static final CompatItemDisplayContext THIRD_PERSON_LEFT_HAND = of(ItemDisplayContext.THIRD_PERSON_LEFT_HAND);
    public static final CompatItemDisplayContext THIRD_PERSON_RIGHT_HAND = of(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND);
    public static final CompatItemDisplayContext FIRST_PERSON_LEFT_HAND = of(ItemDisplayContext.FIRST_PERSON_LEFT_HAND);
    public static final CompatItemDisplayContext FIRST_PERSON_RIGHT_HAND = of(ItemDisplayContext.FIRST_PERSON_RIGHT_HAND);
    public static final CompatItemDisplayContext HEAD = of(ItemDisplayContext.HEAD);
    public static final CompatItemDisplayContext GUI = of(ItemDisplayContext.GUI);
    public static final CompatItemDisplayContext GROUND = of(ItemDisplayContext.GROUND);
    public static final CompatItemDisplayContext FIXED = of(ItemDisplayContext.FIXED);
    public static final CompatItemDisplayContext ON_SHELF = of(ItemDisplayContext.ON_SHELF);

    public CompatItemDisplayContext(ItemDisplayContext context) {
        this.context = context;
    }

    public static CompatItemDisplayContext of(ItemDisplayContext context) {
        return new CompatItemDisplayContext(context);
    }

    public ItemDisplayContext getContext() {
        return context;
    }

    public String getName() {
        return context.name();
    }

    @Override
    public String asString_compat() {
        return getName();
    }
}
