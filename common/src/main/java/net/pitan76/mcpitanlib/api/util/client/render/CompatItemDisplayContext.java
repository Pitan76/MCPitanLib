package net.pitan76.mcpitanlib.api.util.client.render;

import net.minecraft.client.render.model.json.ModelTransformation;
import net.pitan76.mcpitanlib.api.util.CompatStringIdentifiable;

public class CompatItemDisplayContext implements CompatStringIdentifiable {
    private final ModelTransformation.Mode context;

    public static final CompatItemDisplayContext NONE = of(ModelTransformation.Mode.NONE);
    public static final CompatItemDisplayContext THIRD_PERSON_LEFT_HAND = of(ModelTransformation.Mode.THIRD_PERSON_LEFT_HAND);
    public static final CompatItemDisplayContext THIRD_PERSON_RIGHT_HAND = of(ModelTransformation.Mode.THIRD_PERSON_RIGHT_HAND);
    public static final CompatItemDisplayContext FIRST_PERSON_LEFT_HAND = of(ModelTransformation.Mode.FIRST_PERSON_LEFT_HAND);
    public static final CompatItemDisplayContext FIRST_PERSON_RIGHT_HAND = of(ModelTransformation.Mode.FIRST_PERSON_RIGHT_HAND);
    public static final CompatItemDisplayContext HEAD = of(ModelTransformation.Mode.HEAD);
    public static final CompatItemDisplayContext GUI = of(ModelTransformation.Mode.GUI);
    public static final CompatItemDisplayContext GROUND = of(ModelTransformation.Mode.GROUND);
    public static final CompatItemDisplayContext FIXED = of(ModelTransformation.Mode.FIXED);

    public CompatItemDisplayContext(ModelTransformation.Mode context) {
        this.context = context;
    }

    public static CompatItemDisplayContext of(ModelTransformation.Mode context) {
        return new CompatItemDisplayContext(context);
    }

    public ModelTransformation.Mode getContext() {
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
