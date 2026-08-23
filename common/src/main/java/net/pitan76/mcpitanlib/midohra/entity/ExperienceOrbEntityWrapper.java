package net.pitan76.mcpitanlib.midohra.entity;

import net.minecraft.world.entity.ExperienceOrb;
import net.pitan76.mcpitanlib.api.util.entity.ExperienceOrbEntityUtil;

public class ExperienceOrbEntityWrapper extends EntityWrapper {
    public ExperienceOrbEntityWrapper(ExperienceOrb entity) {
        super(entity);
    }

    public static ExperienceOrbEntityWrapper of(ExperienceOrb entity) {
        return new ExperienceOrbEntityWrapper(entity);
    }

    @Override
    public ExperienceOrb get() {
        return (ExperienceOrb) super.get();
    }

    /**
     * @return the amount of experience this orb gives
     */
    public int getExperienceAmount() {
        if (isEmpty()) return 0;

        return ExperienceOrbEntityUtil.getExperienceAmount(get());
    }
}
