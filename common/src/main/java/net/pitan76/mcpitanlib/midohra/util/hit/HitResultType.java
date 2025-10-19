package net.pitan76.mcpitanlib.midohra.util.hit;

import net.minecraft.util.hit.HitResult;

public enum HitResultType {
    MISS(HitResult.Type.MISS),
    BLOCK(HitResult.Type.BLOCK),
    ENTITY(HitResult.Type.ENTITY);

    private final HitResult.Type raw;

    HitResultType(HitResult.Type raw) {
        this.raw = raw;
    }

    @Deprecated
    public HitResult.Type getRaw() {
        return raw;
    }

    public static HitResultType from(HitResult.Type raw) {
        switch (raw) {
            case MISS:
                return MISS;
            case BLOCK:
                return BLOCK;
            case ENTITY:
                return ENTITY;
        }
        throw new IllegalArgumentException("Unknown HitResult.Type: " + raw);
    }

    public static HitResultType of(net.pitan76.mcpitanlib.midohra.util.hit.HitResult result) {
        return from(result.getRawType());
    }
}
