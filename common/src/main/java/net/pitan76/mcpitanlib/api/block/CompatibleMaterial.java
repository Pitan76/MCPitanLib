package net.pitan76.mcpitanlib.api.block;

import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public final class CompatibleMaterial {
    public static final CompatibleMaterial AIR;
    public static final CompatibleMaterial STRUCTURE_VOID;
    public static final CompatibleMaterial PORTAL;
    public static final CompatibleMaterial CARPET;
    public static final CompatibleMaterial PLANT;
    public static final CompatibleMaterial UNDERWATER_PLANT;
    public static final CompatibleMaterial REPLACEABLE_PLANT;
    public static final CompatibleMaterial NETHER_SHOOTS;
    public static final CompatibleMaterial REPLACEABLE_UNDERWATER_PLANT;
    public static final CompatibleMaterial WATER;
    public static final CompatibleMaterial BUBBLE_COLUMN;
    public static final CompatibleMaterial LAVA;
    public static final CompatibleMaterial SNOW_LAYER;
    public static final CompatibleMaterial FIRE;
    public static final CompatibleMaterial DECORATION;
    public static final CompatibleMaterial COBWEB;
    public static final CompatibleMaterial SCULK;
    public static final CompatibleMaterial REDSTONE_LAMP;
    public static final CompatibleMaterial ORGANIC_PRODUCT;
    public static final CompatibleMaterial SOIL;
    public static final CompatibleMaterial SOLID_ORGANIC;
    public static final CompatibleMaterial DENSE_ICE;
    public static final CompatibleMaterial AGGREGATE;
    public static final CompatibleMaterial SPONGE;
    public static final CompatibleMaterial SHULKER_BOX;
    public static final CompatibleMaterial WOOD;
    public static final CompatibleMaterial NETHER_WOOD;
    public static final CompatibleMaterial BAMBOO_SAPLING;
    public static final CompatibleMaterial BAMBOO;
    public static final CompatibleMaterial WOOL;
    public static final CompatibleMaterial TNT;
    public static final CompatibleMaterial LEAVES;
    public static final CompatibleMaterial GLASS;
    public static final CompatibleMaterial ICE;
    public static final CompatibleMaterial CACTUS;
    public static final CompatibleMaterial STONE;
    public static final CompatibleMaterial METAL;
    public static final CompatibleMaterial SNOW_BLOCK;
    public static final CompatibleMaterial REPAIR_STATION;
    public static final CompatibleMaterial BARRIER;
    public static final CompatibleMaterial PISTON;
    public static final CompatibleMaterial MOSS_BLOCK;
    public static final CompatibleMaterial GOURD;
    public static final CompatibleMaterial EGG;
    public static final CompatibleMaterial CAKE;
    public static final CompatibleMaterial AMETHYST;
    public static final CompatibleMaterial POWDER_SNOW;
    private final MapColor color;
    private final PushReaction pistonBehavior;
    private final boolean burnable;
    private final boolean liquid;
    private final boolean replaceable;
    private final boolean solid;

    public CompatibleMaterial(MapColor color, boolean liquid, boolean solid, boolean burnable, boolean replaceable, PushReaction pistonBehavior) {
        this.color = color;
        this.liquid = liquid;
        this.solid = solid;
        this.burnable = burnable;
        this.replaceable = replaceable;
        this.pistonBehavior = pistonBehavior;
    }

    public boolean isLiquid() {
        return this.liquid;
    }

    public boolean isSolid() {
        return this.solid;
    }

    public boolean isBurnable() {
        return this.burnable;
    }

    public boolean isReplaceable() {
        return this.replaceable;
    }

    public PushReaction getPistonBehavior() {
        return this.pistonBehavior;
    }

    public MapColor getColor() {
        return this.color;
    }

    static {
        AIR = (new Builder(MapColor.NONE)).allowsMovement().lightPassesThrough().notSolid().replaceable().build();
        STRUCTURE_VOID = (new Builder(MapColor.NONE)).allowsMovement().lightPassesThrough().notSolid().replaceable().build();
        PORTAL = (new Builder(MapColor.NONE)).allowsMovement().lightPassesThrough().notSolid().blocksPistons().build();
        CARPET = (new Builder(MapColor.WOOL)).allowsMovement().lightPassesThrough().notSolid().burnable().build();
        PLANT = (new Builder(MapColor.PLANT)).allowsMovement().lightPassesThrough().notSolid().destroyedByPiston().build();
        UNDERWATER_PLANT = (new Builder(MapColor.WATER)).allowsMovement().lightPassesThrough().notSolid().destroyedByPiston().build();
        REPLACEABLE_PLANT = (new Builder(MapColor.PLANT)).allowsMovement().lightPassesThrough().notSolid().destroyedByPiston().replaceable().burnable().build();
        NETHER_SHOOTS = (new Builder(MapColor.PLANT)).allowsMovement().lightPassesThrough().notSolid().destroyedByPiston().replaceable().build();
        REPLACEABLE_UNDERWATER_PLANT = (new Builder(MapColor.WATER)).allowsMovement().lightPassesThrough().notSolid().destroyedByPiston().replaceable().build();
        WATER = (new Builder(MapColor.WATER)).allowsMovement().lightPassesThrough().notSolid().destroyedByPiston().replaceable().liquid().build();
        BUBBLE_COLUMN = (new Builder(MapColor.WATER)).allowsMovement().lightPassesThrough().notSolid().destroyedByPiston().replaceable().liquid().build();
        LAVA = (new Builder(MapColor.FIRE)).allowsMovement().lightPassesThrough().notSolid().destroyedByPiston().replaceable().liquid().build();
        SNOW_LAYER = (new Builder(MapColor.SNOW)).allowsMovement().lightPassesThrough().notSolid().destroyedByPiston().replaceable().build();
        FIRE = (new Builder(MapColor.NONE)).allowsMovement().lightPassesThrough().notSolid().destroyedByPiston().replaceable().build();
        DECORATION = (new Builder(MapColor.NONE)).allowsMovement().lightPassesThrough().notSolid().destroyedByPiston().build();
        COBWEB = (new Builder(MapColor.WOOL)).allowsMovement().lightPassesThrough().destroyedByPiston().build();
        SCULK = (new Builder(MapColor.COLOR_BLACK)).build();
        REDSTONE_LAMP = (new Builder(MapColor.NONE)).build();
        ORGANIC_PRODUCT = (new Builder(MapColor.CLAY)).build();
        SOIL = (new Builder(MapColor.DIRT)).build();
        SOLID_ORGANIC = (new Builder(MapColor.GRASS)).build();
        DENSE_ICE = (new Builder(MapColor.ICE)).build();
        AGGREGATE = (new Builder(MapColor.SAND)).build();
        SPONGE = (new Builder(MapColor.COLOR_YELLOW)).build();
        SHULKER_BOX = (new Builder(MapColor.COLOR_PURPLE)).build();
        WOOD = (new Builder(MapColor.WOOD)).burnable().build();
        NETHER_WOOD = (new Builder(MapColor.WOOD)).build();
        BAMBOO_SAPLING = (new Builder(MapColor.WOOD)).burnable().destroyedByPiston().allowsMovement().build();
        BAMBOO = (new Builder(MapColor.WOOD)).burnable().destroyedByPiston().build();
        WOOL = (new Builder(MapColor.WOOL)).burnable().build();
        TNT = (new Builder(MapColor.FIRE)).burnable().lightPassesThrough().build();
        LEAVES = (new Builder(MapColor.PLANT)).burnable().lightPassesThrough().destroyedByPiston().build();
        GLASS = (new Builder(MapColor.NONE)).lightPassesThrough().build();
        ICE = (new Builder(MapColor.ICE)).lightPassesThrough().build();
        CACTUS = (new Builder(MapColor.PLANT)).lightPassesThrough().destroyedByPiston().build();
        STONE = (new Builder(MapColor.STONE)).build();
        METAL = (new Builder(MapColor.METAL)).build();
        SNOW_BLOCK = (new Builder(MapColor.SNOW)).build();
        REPAIR_STATION = (new Builder(MapColor.METAL)).blocksPistons().build();
        BARRIER = (new Builder(MapColor.NONE)).blocksPistons().build();
        PISTON = (new Builder(MapColor.STONE)).blocksPistons().build();
        MOSS_BLOCK = (new Builder(MapColor.PLANT)).destroyedByPiston().build();
        GOURD = (new Builder(MapColor.PLANT)).destroyedByPiston().build();
        EGG = (new Builder(MapColor.PLANT)).destroyedByPiston().build();
        CAKE = (new Builder(MapColor.NONE)).destroyedByPiston().build();
        AMETHYST = (new Builder(MapColor.COLOR_PURPLE)).build();
        POWDER_SNOW = (new Builder(MapColor.SNOW)).notSolid().allowsMovement().build();
    }

    public static class Builder {
        private PushReaction pistonBehavior;
        private boolean blocksMovement;
        private boolean burnable;
        private boolean liquid;
        private boolean replaceable;
        private boolean solid;
        private final MapColor color;
        private boolean blocksLight;

        public Builder(MapColor color) {
            this.pistonBehavior = PushReaction.NORMAL;
            this.blocksMovement = true;
            this.solid = true;
            this.blocksLight = true;
            this.color = color;
        }

        public Builder liquid() {
            this.liquid = true;
            return this;
        }

        public Builder notSolid() {
            this.solid = false;
            return this;
        }

        public Builder allowsMovement() {
            this.blocksMovement = false;
            return this;
        }

        Builder lightPassesThrough() {
            this.blocksLight = false;
            return this;
        }

        protected Builder burnable() {
            this.burnable = true;
            return this;
        }

        public Builder replaceable() {
            this.replaceable = true;
            return this;
        }

        protected Builder destroyedByPiston() {
            this.pistonBehavior = PushReaction.DESTROY;
            return this;
        }

        protected Builder blocksPistons() {
            this.pistonBehavior = PushReaction.BLOCK;
            return this;
        }

        public CompatibleMaterial build() {
            return new CompatibleMaterial(this.color, this.liquid, this.solid, this.burnable, this.replaceable, this.pistonBehavior);
        }
    }
}
