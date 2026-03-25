package net.pitan76.mcpitanlib.api.sound;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.sounds.SoundEvent;

public class CompatBlockSoundGroup {
    public static final CompatBlockSoundGroup INTENTIONALLY_EMPTY = of(SoundType.EMPTY);
    public static final CompatBlockSoundGroup WOOD = of(SoundType.WOOD);
    public static final CompatBlockSoundGroup GRAVEL = of(SoundType.GRAVEL);
    public static final CompatBlockSoundGroup GRASS = of(SoundType.GRASS);
    public static final CompatBlockSoundGroup LILY_PAD = of(SoundType.LILY_PAD);
    public static final CompatBlockSoundGroup STONE = of(SoundType.STONE);
    public static final CompatBlockSoundGroup METAL = of(SoundType.METAL);
    public static final CompatBlockSoundGroup GLASS = of(SoundType.GLASS);
    public static final CompatBlockSoundGroup WOOL = of(SoundType.WOOL);
    public static final CompatBlockSoundGroup SAND = of(SoundType.SAND);
    public static final CompatBlockSoundGroup SNOW = of(SoundType.SNOW);
    public static final CompatBlockSoundGroup POWDER_SNOW = of(SoundType.POWDER_SNOW);
    public static final CompatBlockSoundGroup LADDER = of(SoundType.LADDER);
    public static final CompatBlockSoundGroup ANVIL = of(SoundType.ANVIL);
    public static final CompatBlockSoundGroup SLIME = of(SoundType.SLIME_BLOCK);
    public static final CompatBlockSoundGroup HONEY = of(SoundType.HONEY_BLOCK);
    public static final CompatBlockSoundGroup WET_GRASS = of(SoundType.WET_GRASS);
    public static final CompatBlockSoundGroup CORAL = of(SoundType.CORAL_BLOCK);
    public static final CompatBlockSoundGroup BAMBOO = of(SoundType.BAMBOO);
    public static final CompatBlockSoundGroup BAMBOO_SAPLING = of(SoundType.BAMBOO_SAPLING);
    public static final CompatBlockSoundGroup SCAFFOLDING = of(SoundType.SCAFFOLDING);
    public static final CompatBlockSoundGroup SWEET_BERRY_BUSH = of(SoundType.SWEET_BERRY_BUSH);
    public static final CompatBlockSoundGroup CROP = of(SoundType.CROP);
    public static final CompatBlockSoundGroup STEM = of(SoundType.HARD_CROP);
    public static final CompatBlockSoundGroup VINE = of(SoundType.VINE);
    public static final CompatBlockSoundGroup NETHER_WART = of(SoundType.NETHER_WART);
    public static final CompatBlockSoundGroup LANTERN = of(SoundType.LANTERN);
    public static final CompatBlockSoundGroup NETHER_STEM = of(SoundType.STEM);
    public static final CompatBlockSoundGroup NYLIUM = of(SoundType.NYLIUM);
    public static final CompatBlockSoundGroup FUNGUS = of(SoundType.FUNGUS);
    public static final CompatBlockSoundGroup ROOTS = of(SoundType.ROOTS);
    public static final CompatBlockSoundGroup SHROOMLIGHT = of(SoundType.SHROOMLIGHT);
    public static final CompatBlockSoundGroup WEEPING_VINES = of(SoundType.WEEPING_VINES);
    public static final CompatBlockSoundGroup WEEPING_VINES_LOW_PITCH = of(SoundType.TWISTING_VINES);
    public static final CompatBlockSoundGroup SOUL_SAND = of(SoundType.SOUL_SAND);
    public static final CompatBlockSoundGroup SOUL_SOIL = of(SoundType.SOUL_SOIL);
    public static final CompatBlockSoundGroup BASALT = of(SoundType.BASALT);
    public static final CompatBlockSoundGroup WART_BLOCK = of(SoundType.WART_BLOCK);
    public static final CompatBlockSoundGroup NETHERRACK = of(SoundType.NETHERRACK);
    public static final CompatBlockSoundGroup NETHER_BRICKS = of(SoundType.NETHER_BRICKS);
    public static final CompatBlockSoundGroup NETHER_SPROUTS = of(SoundType.NETHER_SPROUTS);
    public static final CompatBlockSoundGroup NETHER_ORE = of(SoundType.NETHER_ORE);
    public static final CompatBlockSoundGroup BONE = of(SoundType.BONE_BLOCK);
    public static final CompatBlockSoundGroup NETHERITE = of(SoundType.NETHERITE_BLOCK);
    public static final CompatBlockSoundGroup ANCIENT_DEBRIS = of(SoundType.ANCIENT_DEBRIS);
    public static final CompatBlockSoundGroup LODESTONE = of(SoundType.LODESTONE);
    public static final CompatBlockSoundGroup CHAIN = of(SoundType.CHAIN);
    public static final CompatBlockSoundGroup NETHER_GOLD_ORE = of(SoundType.NETHER_GOLD_ORE);
    public static final CompatBlockSoundGroup GILDED_BLACKSTONE = of(SoundType.GILDED_BLACKSTONE);
    public static final CompatBlockSoundGroup CANDLE = of(SoundType.CANDLE);
    public static final CompatBlockSoundGroup AMETHYST_BLOCK = of(SoundType.AMETHYST);
    public static final CompatBlockSoundGroup AMETHYST_CLUSTER = of(SoundType.AMETHYST_CLUSTER);
    public static final CompatBlockSoundGroup SMALL_AMETHYST_BUD = of(SoundType.SMALL_AMETHYST_BUD);
    public static final CompatBlockSoundGroup MEDIUM_AMETHYST_BUD = of(SoundType.MEDIUM_AMETHYST_BUD);
    public static final CompatBlockSoundGroup LARGE_AMETHYST_BUD = of(SoundType.LARGE_AMETHYST_BUD);
    public static final CompatBlockSoundGroup TUFF = of(SoundType.TUFF);
    public static final CompatBlockSoundGroup TUFF_BRICKS = of(SoundType.TUFF_BRICKS);
    public static final CompatBlockSoundGroup POLISHED_TUFF = of(SoundType.POLISHED_TUFF);
    public static final CompatBlockSoundGroup CALCITE = of(SoundType.CALCITE);
    public static final CompatBlockSoundGroup DRIPSTONE_BLOCK = of(SoundType.DRIPSTONE_BLOCK);
    public static final CompatBlockSoundGroup POINTED_DRIPSTONE = of(SoundType.POINTED_DRIPSTONE);
    public static final CompatBlockSoundGroup COPPER = of(SoundType.COPPER);
    public static final CompatBlockSoundGroup COPPER_BULB = of(SoundType.COPPER_BULB);
    public static final CompatBlockSoundGroup COPPER_GRATE = of(SoundType.COPPER_GRATE);
    public static final CompatBlockSoundGroup CAVE_VINES = of(SoundType.CAVE_VINES);
    public static final CompatBlockSoundGroup SPORE_BLOSSOM = of(SoundType.SPORE_BLOSSOM);
    public static final CompatBlockSoundGroup AZALEA = of(SoundType.AZALEA);
    public static final CompatBlockSoundGroup FLOWERING_AZALEA = of(SoundType.FLOWERING_AZALEA);
    public static final CompatBlockSoundGroup MOSS_CARPET = of(SoundType.MOSS_CARPET);
    public static final CompatBlockSoundGroup PINK_PETALS = of(SoundType.CHERRY_LEAVES);
    public static final CompatBlockSoundGroup MOSS_BLOCK = of(SoundType.MOSS);
    public static final CompatBlockSoundGroup BIG_DRIPLEAF = of(SoundType.BIG_DRIPLEAF);
    public static final CompatBlockSoundGroup SMALL_DRIPLEAF = of(SoundType.SMALL_DRIPLEAF);
    public static final CompatBlockSoundGroup ROOTED_DIRT = of(SoundType.ROOTED_DIRT);
    public static final CompatBlockSoundGroup HANGING_ROOTS = of(SoundType.HANGING_ROOTS);
    public static final CompatBlockSoundGroup AZALEA_LEAVES = of(SoundType.AZALEA_LEAVES);
    public static final CompatBlockSoundGroup SCULK_SENSOR = of(SoundType.SCULK_SENSOR);
    public static final CompatBlockSoundGroup SCULK_CATALYST = of(SoundType.SCULK_CATALYST);
    public static final CompatBlockSoundGroup SCULK = of(SoundType.SCULK);
    public static final CompatBlockSoundGroup SCULK_VEIN = of(SoundType.SCULK_VEIN);
    public static final CompatBlockSoundGroup SCULK_SHRIEKER = of(SoundType.SCULK_SHRIEKER);
    public static final CompatBlockSoundGroup GLOW_LICHEN = of(SoundType.GLOW_LICHEN);
    public static final CompatBlockSoundGroup DEEPSLATE = of(SoundType.DEEPSLATE);
    public static final CompatBlockSoundGroup DEEPSLATE_BRICKS = of(SoundType.DEEPSLATE_BRICKS);
    public static final CompatBlockSoundGroup DEEPSLATE_TILES = of(SoundType.DEEPSLATE_TILES);
    public static final CompatBlockSoundGroup POLISHED_DEEPSLATE = of(SoundType.POLISHED_DEEPSLATE);
    public static final CompatBlockSoundGroup FROGLIGHT = of(SoundType.FROGLIGHT);
    public static final CompatBlockSoundGroup FROGSPAWN = of(SoundType.FROGSPAWN);
    public static final CompatBlockSoundGroup MANGROVE_ROOTS = of(SoundType.MANGROVE_ROOTS);
    public static final CompatBlockSoundGroup MUDDY_MANGROVE_ROOTS = of(SoundType.MUDDY_MANGROVE_ROOTS);
    public static final CompatBlockSoundGroup MUD = of(SoundType.MUD);
    public static final CompatBlockSoundGroup MUD_BRICKS = of(SoundType.MUD_BRICKS);
    public static final CompatBlockSoundGroup PACKED_MUD = of(SoundType.PACKED_MUD);
    public static final CompatBlockSoundGroup HANGING_SIGN = of(SoundType.HANGING_SIGN);
    public static final CompatBlockSoundGroup NETHER_WOOD_HANGING_SIGN = of(SoundType.NETHER_WOOD_HANGING_SIGN);
    public static final CompatBlockSoundGroup BAMBOO_WOOD_HANGING_SIGN = of(SoundType.BAMBOO_WOOD_HANGING_SIGN);
    public static final CompatBlockSoundGroup BAMBOO_WOOD = of(SoundType.BAMBOO_WOOD);
    public static final CompatBlockSoundGroup NETHER_WOOD = of(SoundType.NETHER_WOOD);
    public static final CompatBlockSoundGroup CHERRY_WOOD = of(SoundType.CHERRY_WOOD);
    public static final CompatBlockSoundGroup CHERRY_SAPLING = of(SoundType.CHERRY_SAPLING);
    public static final CompatBlockSoundGroup CHERRY_LEAVES = of(SoundType.CHERRY_LEAVES);
    public static final CompatBlockSoundGroup CHERRY_WOOD_HANGING_SIGN = of(SoundType.CHERRY_WOOD_HANGING_SIGN);
    public static final CompatBlockSoundGroup CHISELED_BOOKSHELF = of(SoundType.CHISELED_BOOKSHELF);
    public static final CompatBlockSoundGroup SUSPICIOUS_SAND = of(SoundType.SUSPICIOUS_SAND);
    public static final CompatBlockSoundGroup SUSPICIOUS_GRAVEL = of(SoundType.SUSPICIOUS_GRAVEL);
    public static final CompatBlockSoundGroup DECORATED_POT = of(SoundType.DECORATED_POT);
    public static final CompatBlockSoundGroup DECORATED_POT_SHATTER = of(SoundType.DECORATED_POT_CRACKED);
    public static final CompatBlockSoundGroup TRIAL_SPAWNER = of(SoundType.TRIAL_SPAWNER);
    public static final CompatBlockSoundGroup SPONGE = of(SoundType.SPONGE);
    public static final CompatBlockSoundGroup WET_SPONGE = of(SoundType.WET_SPONGE);
    public static final CompatBlockSoundGroup VAULT = of(SoundType.VAULT);
    public static final CompatBlockSoundGroup HEAVY_CORE = of(SoundType.HEAVY_CORE);
    public static final CompatBlockSoundGroup COBWEB = of(SoundType.COBWEB);

    public SoundType blockSoundGroup;

    private final float volume;
    private final float pitch;
    private final SoundEvent breakSound;
    private final SoundEvent stepSound;
    private final SoundEvent placeSound;
    private final SoundEvent hitSound;
    private final SoundEvent fallSound;

    public CompatBlockSoundGroup(SoundType blockSoundGroup) {
        this.blockSoundGroup = blockSoundGroup;

        this.volume = blockSoundGroup.getVolume();
        this.pitch = blockSoundGroup.getPitch();
        this.breakSound = blockSoundGroup.getBreakSound();
        this.stepSound = blockSoundGroup.getStepSound();
        this.placeSound = blockSoundGroup.getPlaceSound();
        this.hitSound = blockSoundGroup.getHitSound();
        this.fallSound = blockSoundGroup.getFallSound();
    }

    public CompatBlockSoundGroup(float volume, float pitch, SoundEvent breakSound, SoundEvent stepSound, SoundEvent placeSound, SoundEvent hitSound, SoundEvent fallSound) {
        this.blockSoundGroup = new SoundType(volume, pitch, breakSound, stepSound, placeSound, hitSound, fallSound);

        this.volume = volume;
        this.pitch = pitch;
        this.breakSound = breakSound;
        this.stepSound = stepSound;
        this.placeSound = placeSound;
        this.hitSound = hitSound;
        this.fallSound = fallSound;
    }

    public SoundType get() {
        if (blockSoundGroup == null) {
            return new SoundType(volume, pitch, breakSound, stepSound, placeSound, hitSound, fallSound);
        }
        return blockSoundGroup;
    }

    public static CompatBlockSoundGroup of(SoundType blockSoundGroup) {
        return new CompatBlockSoundGroup(blockSoundGroup);
    }

    public static CompatBlockSoundGroup of(float volume, float pitch, SoundEvent breakSound, SoundEvent stepSound, SoundEvent placeSound, SoundEvent hitSound, SoundEvent fallSound) {
        return new CompatBlockSoundGroup(volume, pitch, breakSound, stepSound, placeSound, hitSound, fallSound);
    }

    public static CompatBlockSoundGroup of(float volume, float pitch, CompatSoundEvent breakSound, CompatSoundEvent stepSound, CompatSoundEvent placeSound, CompatSoundEvent hitSound, CompatSoundEvent fallSound) {
        return new CompatBlockSoundGroup(volume, pitch, breakSound.get(), stepSound.get(), placeSound.get(), hitSound.get(), fallSound.get());
    }

    public float getVolume() {
        return volume;
    }

    public float getPitch() {
        return pitch;
    }

    public SoundEvent getRawBreakSound() {
        return breakSound;
    }

    public SoundEvent getRawStepSound() {
        return stepSound;
    }

    public SoundEvent getRawPlaceSound() {
        return placeSound;
    }

    public SoundEvent getRawHitSound() {
        return hitSound;
    }

    public SoundEvent getRawFallSound() {
        return fallSound;
    }

    public CompatSoundEvent getBreakSound() {
        return CompatSoundEvent.of(breakSound);
    }

    public CompatSoundEvent getStepSound() {
        return CompatSoundEvent.of(stepSound);
    }

    public CompatSoundEvent getPlaceSound() {
        return CompatSoundEvent.of(placeSound);
    }

    public CompatSoundEvent getHitSound() {
        return CompatSoundEvent.of(hitSound);
    }

    public CompatSoundEvent getFallSound() {
        return CompatSoundEvent.of(fallSound);
    }
}
