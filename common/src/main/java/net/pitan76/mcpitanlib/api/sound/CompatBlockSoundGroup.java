package net.pitan76.mcpitanlib.api.sound;

import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;

public class CompatBlockSoundGroup {
    public static final CompatBlockSoundGroup INTENTIONALLY_EMPTY = of(BlockSoundGroup.STONE);
    public static final CompatBlockSoundGroup WOOD = of(BlockSoundGroup.WOOD);
    public static final CompatBlockSoundGroup GRAVEL = of(BlockSoundGroup.GRAVEL);
    public static final CompatBlockSoundGroup GRASS = of(BlockSoundGroup.GRASS);
    public static final CompatBlockSoundGroup LILY_PAD = of(BlockSoundGroup.LILY_PAD);
    public static final CompatBlockSoundGroup STONE = of(BlockSoundGroup.STONE);
    public static final CompatBlockSoundGroup METAL = of(BlockSoundGroup.METAL);
    public static final CompatBlockSoundGroup GLASS = of(BlockSoundGroup.GLASS);
    public static final CompatBlockSoundGroup WOOL = of(BlockSoundGroup.WOOL);
    public static final CompatBlockSoundGroup SAND = of(BlockSoundGroup.SAND);
    public static final CompatBlockSoundGroup SNOW = of(BlockSoundGroup.SNOW);
    public static final CompatBlockSoundGroup POWDER_SNOW = of(BlockSoundGroup.POWDER_SNOW);
    public static final CompatBlockSoundGroup LADDER = of(BlockSoundGroup.LADDER);
    public static final CompatBlockSoundGroup ANVIL = of(BlockSoundGroup.ANVIL);
    public static final CompatBlockSoundGroup SLIME = of(BlockSoundGroup.SLIME);
    public static final CompatBlockSoundGroup HONEY = of(BlockSoundGroup.HONEY);
    public static final CompatBlockSoundGroup WET_GRASS = of(BlockSoundGroup.WET_GRASS);
    public static final CompatBlockSoundGroup CORAL = of(BlockSoundGroup.CORAL);
    public static final CompatBlockSoundGroup BAMBOO = of(BlockSoundGroup.BAMBOO);
    public static final CompatBlockSoundGroup BAMBOO_SAPLING = of(BlockSoundGroup.BAMBOO_SAPLING);
    public static final CompatBlockSoundGroup SCAFFOLDING = of(BlockSoundGroup.SCAFFOLDING);
    public static final CompatBlockSoundGroup SWEET_BERRY_BUSH = of(BlockSoundGroup.SWEET_BERRY_BUSH);
    public static final CompatBlockSoundGroup CROP = of(BlockSoundGroup.CROP);
    public static final CompatBlockSoundGroup STEM = of(BlockSoundGroup.STEM);
    public static final CompatBlockSoundGroup VINE = of(BlockSoundGroup.VINE);
    public static final CompatBlockSoundGroup NETHER_WART = of(BlockSoundGroup.NETHER_WART);
    public static final CompatBlockSoundGroup LANTERN = of(BlockSoundGroup.LANTERN);
    public static final CompatBlockSoundGroup NETHER_STEM = of(BlockSoundGroup.NETHER_STEM);
    public static final CompatBlockSoundGroup NYLIUM = of(BlockSoundGroup.NYLIUM);
    public static final CompatBlockSoundGroup FUNGUS = of(BlockSoundGroup.FUNGUS);
    public static final CompatBlockSoundGroup ROOTS = of(BlockSoundGroup.ROOTS);
    public static final CompatBlockSoundGroup SHROOMLIGHT = of(BlockSoundGroup.SHROOMLIGHT);
    public static final CompatBlockSoundGroup WEEPING_VINES = of(BlockSoundGroup.WEEPING_VINES);
    public static final CompatBlockSoundGroup WEEPING_VINES_LOW_PITCH = of(BlockSoundGroup.WEEPING_VINES_LOW_PITCH);
    public static final CompatBlockSoundGroup SOUL_SAND = of(BlockSoundGroup.SOUL_SAND);
    public static final CompatBlockSoundGroup SOUL_SOIL = of(BlockSoundGroup.SOUL_SOIL);
    public static final CompatBlockSoundGroup BASALT = of(BlockSoundGroup.BASALT);
    public static final CompatBlockSoundGroup WART_BLOCK = of(BlockSoundGroup.WART_BLOCK);
    public static final CompatBlockSoundGroup NETHERRACK = of(BlockSoundGroup.NETHERRACK);
    public static final CompatBlockSoundGroup NETHER_BRICKS = of(BlockSoundGroup.NETHER_BRICKS);
    public static final CompatBlockSoundGroup NETHER_SPROUTS = of(BlockSoundGroup.NETHER_SPROUTS);
    public static final CompatBlockSoundGroup NETHER_ORE = of(BlockSoundGroup.NETHER_ORE);
    public static final CompatBlockSoundGroup BONE = of(BlockSoundGroup.BONE);
    public static final CompatBlockSoundGroup NETHERITE = of(BlockSoundGroup.NETHERITE);
    public static final CompatBlockSoundGroup ANCIENT_DEBRIS = of(BlockSoundGroup.ANCIENT_DEBRIS);
    public static final CompatBlockSoundGroup LODESTONE = of(BlockSoundGroup.LODESTONE);
    public static final CompatBlockSoundGroup CHAIN = of(BlockSoundGroup.CHAIN);
    public static final CompatBlockSoundGroup NETHER_GOLD_ORE = of(BlockSoundGroup.NETHER_GOLD_ORE);
    public static final CompatBlockSoundGroup GILDED_BLACKSTONE = of(BlockSoundGroup.GILDED_BLACKSTONE);
    public static final CompatBlockSoundGroup CANDLE = of(BlockSoundGroup.CANDLE);
    public static final CompatBlockSoundGroup AMETHYST_BLOCK = of(BlockSoundGroup.AMETHYST_BLOCK);
    public static final CompatBlockSoundGroup AMETHYST_CLUSTER = of(BlockSoundGroup.AMETHYST_CLUSTER);
    public static final CompatBlockSoundGroup SMALL_AMETHYST_BUD = of(BlockSoundGroup.SMALL_AMETHYST_BUD);
    public static final CompatBlockSoundGroup MEDIUM_AMETHYST_BUD = of(BlockSoundGroup.MEDIUM_AMETHYST_BUD);
    public static final CompatBlockSoundGroup LARGE_AMETHYST_BUD = of(BlockSoundGroup.LARGE_AMETHYST_BUD);
    public static final CompatBlockSoundGroup TUFF = of(BlockSoundGroup.TUFF);
    public static final CompatBlockSoundGroup TUFF_BRICKS = of(BlockSoundGroup.STONE);
    public static final CompatBlockSoundGroup POLISHED_TUFF = of(BlockSoundGroup.STONE);
    public static final CompatBlockSoundGroup CALCITE = of(BlockSoundGroup.CALCITE);
    public static final CompatBlockSoundGroup DRIPSTONE_BLOCK = of(BlockSoundGroup.DRIPSTONE_BLOCK);
    public static final CompatBlockSoundGroup POINTED_DRIPSTONE = of(BlockSoundGroup.POINTED_DRIPSTONE);
    public static final CompatBlockSoundGroup COPPER = of(BlockSoundGroup.COPPER);
    public static final CompatBlockSoundGroup COPPER_BULB = of(BlockSoundGroup.STONE);
    public static final CompatBlockSoundGroup COPPER_GRATE = of(BlockSoundGroup.STONE);
    public static final CompatBlockSoundGroup CAVE_VINES = of(BlockSoundGroup.CAVE_VINES);
    public static final CompatBlockSoundGroup SPORE_BLOSSOM = of(BlockSoundGroup.SPORE_BLOSSOM);
    public static final CompatBlockSoundGroup AZALEA = of(BlockSoundGroup.AZALEA);
    public static final CompatBlockSoundGroup FLOWERING_AZALEA = of(BlockSoundGroup.FLOWERING_AZALEA);
    public static final CompatBlockSoundGroup MOSS_CARPET = of(BlockSoundGroup.MOSS_CARPET);
    public static final CompatBlockSoundGroup PINK_PETALS = of(BlockSoundGroup.STONE);
    public static final CompatBlockSoundGroup MOSS_BLOCK = of(BlockSoundGroup.MOSS_BLOCK);
    public static final CompatBlockSoundGroup BIG_DRIPLEAF = of(BlockSoundGroup.BIG_DRIPLEAF);
    public static final CompatBlockSoundGroup SMALL_DRIPLEAF = of(BlockSoundGroup.SMALL_DRIPLEAF);
    public static final CompatBlockSoundGroup ROOTED_DIRT = of(BlockSoundGroup.ROOTED_DIRT);
    public static final CompatBlockSoundGroup HANGING_ROOTS = of(BlockSoundGroup.HANGING_ROOTS);
    public static final CompatBlockSoundGroup AZALEA_LEAVES = of(BlockSoundGroup.AZALEA_LEAVES);
    public static final CompatBlockSoundGroup SCULK_SENSOR = of(BlockSoundGroup.SCULK_SENSOR);
    public static final CompatBlockSoundGroup SCULK_CATALYST = of(BlockSoundGroup.STONE);
    public static final CompatBlockSoundGroup SCULK = of(BlockSoundGroup.STONE);
    public static final CompatBlockSoundGroup SCULK_VEIN = of(BlockSoundGroup.STONE);
    public static final CompatBlockSoundGroup SCULK_SHRIEKER = of(BlockSoundGroup.STONE);
    public static final CompatBlockSoundGroup GLOW_LICHEN = of(BlockSoundGroup.GLOW_LICHEN);
    public static final CompatBlockSoundGroup DEEPSLATE = of(BlockSoundGroup.DEEPSLATE);
    public static final CompatBlockSoundGroup DEEPSLATE_BRICKS = of(BlockSoundGroup.DEEPSLATE_BRICKS);
    public static final CompatBlockSoundGroup DEEPSLATE_TILES = of(BlockSoundGroup.DEEPSLATE_TILES);
    public static final CompatBlockSoundGroup POLISHED_DEEPSLATE = of(BlockSoundGroup.POLISHED_DEEPSLATE);
    public static final CompatBlockSoundGroup FROGLIGHT = of(BlockSoundGroup.STONE);
    public static final CompatBlockSoundGroup FROGSPAWN = of(BlockSoundGroup.STONE);
    public static final CompatBlockSoundGroup MANGROVE_ROOTS = of(BlockSoundGroup.STONE);
    public static final CompatBlockSoundGroup MUDDY_MANGROVE_ROOTS = of(BlockSoundGroup.STONE);
    public static final CompatBlockSoundGroup MUD = of(BlockSoundGroup.STONE);
    public static final CompatBlockSoundGroup MUD_BRICKS = of(BlockSoundGroup.STONE);
    public static final CompatBlockSoundGroup PACKED_MUD = of(BlockSoundGroup.STONE);
    public static final CompatBlockSoundGroup HANGING_SIGN = of(BlockSoundGroup.WOOD);
    public static final CompatBlockSoundGroup NETHER_WOOD_HANGING_SIGN = of(BlockSoundGroup.WOOD);
    public static final CompatBlockSoundGroup BAMBOO_WOOD_HANGING_SIGN = of(BlockSoundGroup.WOOD);
    public static final CompatBlockSoundGroup BAMBOO_WOOD = of(BlockSoundGroup.WOOD);
    public static final CompatBlockSoundGroup NETHER_WOOD = of(BlockSoundGroup.WOOD);
    public static final CompatBlockSoundGroup CHERRY_WOOD = of(BlockSoundGroup.WOOD);
    public static final CompatBlockSoundGroup CHERRY_SAPLING = of(BlockSoundGroup.GRASS);
    public static final CompatBlockSoundGroup CHERRY_LEAVES = of(BlockSoundGroup.GRASS);
    public static final CompatBlockSoundGroup CHERRY_WOOD_HANGING_SIGN = of(BlockSoundGroup.WOOD);
    public static final CompatBlockSoundGroup CHISELED_BOOKSHELF = of(BlockSoundGroup.WOOD);
    public static final CompatBlockSoundGroup SUSPICIOUS_SAND = of(BlockSoundGroup.SAND);
    public static final CompatBlockSoundGroup SUSPICIOUS_GRAVEL = of(BlockSoundGroup.GRAVEL);
    public static final CompatBlockSoundGroup DECORATED_POT = of(BlockSoundGroup.STONE);
    public static final CompatBlockSoundGroup DECORATED_POT_SHATTER = of(BlockSoundGroup.STONE);
    public static final CompatBlockSoundGroup TRIAL_SPAWNER = of(BlockSoundGroup.STONE);
    public static final CompatBlockSoundGroup SPONGE = of(BlockSoundGroup.GRASS);
    public static final CompatBlockSoundGroup WET_SPONGE = of(BlockSoundGroup.WET_GRASS);
    public static final CompatBlockSoundGroup VAULT = of(BlockSoundGroup.STONE);
    public static final CompatBlockSoundGroup HEAVY_CORE = of(BlockSoundGroup.STONE);
    public static final CompatBlockSoundGroup COBWEB = of(BlockSoundGroup.STONE);

    public BlockSoundGroup blockSoundGroup;

    private final float volume;
    private final float pitch;
    private final SoundEvent breakSound;
    private final SoundEvent stepSound;
    private final SoundEvent placeSound;
    private final SoundEvent hitSound;
    private final SoundEvent fallSound;

    public CompatBlockSoundGroup(BlockSoundGroup blockSoundGroup) {
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
        this.blockSoundGroup = new BlockSoundGroup(volume, pitch, breakSound, stepSound, placeSound, hitSound, fallSound);

        this.volume = volume;
        this.pitch = pitch;
        this.breakSound = breakSound;
        this.stepSound = stepSound;
        this.placeSound = placeSound;
        this.hitSound = hitSound;
        this.fallSound = fallSound;
    }

    public BlockSoundGroup get() {
        if (blockSoundGroup == null) {
            return new BlockSoundGroup(volume, pitch, breakSound, stepSound, placeSound, hitSound, fallSound);
        }
        return blockSoundGroup;
    }

    public static CompatBlockSoundGroup of(BlockSoundGroup blockSoundGroup) {
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
