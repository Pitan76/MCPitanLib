package net.pitan76.mcpitanlib.api.util.client.render;

import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class CompatItemDisplayContext implements CompatStringIdentifiable {
    private final ModelTransformationMode context;

    public static final CompatItemDisplayContext NONE = of(ModelTransformationMode.NONE);
    public static final CompatItemDisplayContext THIRD_PERSON_LEFT_HAND = of(ModelTransformationMode.THIRD_PERSON_LEFT_HAND);
    public static final CompatItemDisplayContext THIRD_PERSON_RIGHT_HAND = of(ModelTransformationMode.THIRD_PERSON_RIGHT_HAND);
    public static final CompatItemDisplayContext FIRST_PERSON_LEFT_HAND = of(ModelTransformationMode.FIRST_PERSON_LEFT_HAND);
    public static final CompatItemDisplayContext FIRST_PERSON_RIGHT_HAND = of(ModelTransformationMode.FIRST_PERSON_RIGHT_HAND);
    public static final CompatItemDisplayContext HEAD = of(ModelTransformationMode.HEAD);
    public static final CompatItemDisplayContext GUI = of(ModelTransformationMode.GUI);
    public static final CompatItemDisplayContext GROUND = of(ModelTransformationMode.GROUND);
    public static final CompatItemDisplayContext FIXED = of(ModelTransformationMode.FIXED);

    public CompatItemDisplayContext(ModelTransformationMode context) {
        this.context = context;
    }

    public static CompatItemDisplayContext of(ModelTransformationMode context) {
        return new CompatItemDisplayContext(context);
    }

    public ModelTransformationMode getContext() {
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
