package net.pitan76.mcpitanlib.midohra.entity;

import net.minecraft.entity.ExperienceOrbEntity;
import net.pitan76.mcpitanlib.api.util.entity.ExperienceOrbEntityUtil;

public class ExperienceOrbEntityWrapper extends EntityWrapper {
    public ExperienceOrbEntityWrapper(ExperienceOrbEntity entity) {
        super(entity);
    }

    public static ExperienceOrbEntityWrapper of(ExperienceOrbEntity entity) {
        return new ExperienceOrbEntityWrapper(entity);
    }

    @Override
    public ExperienceOrbEntity get() {
        return (ExperienceOrbEntity) super.get();
    }

    public int getExperienceAmount() {
        if (isEmpty()) return 0;

        return ExperienceOrbEntityUtil.getExperienceAmount(get());
    }
}
