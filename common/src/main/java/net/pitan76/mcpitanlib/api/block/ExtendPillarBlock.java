package net.pitan76.mcpitanlib.api.block;

import net.minecraft.block.PillarBlock;

public class ExtendPillarBlock extends PillarBlock implements ExtendBlockProvider {
    public ExtendPillarBlock(Settings settings) {
        super(settings);
    }

    public ExtendPillarBlock(CompatibleBlockSettings settings) {
        this(settings.build());
    }
}
