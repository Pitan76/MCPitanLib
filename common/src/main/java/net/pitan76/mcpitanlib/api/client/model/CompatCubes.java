package net.pitan76.mcpitanlib.api.client.model;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelPartBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * パーツが持つキューブの一覧 (BlockBench の CubeListBuilder に相当)
 * <pre>{@code
 * CompatCubes.create()
 *     .texOffs(0, 15).addBox(-4, -8, -4, 8, 8, 8)
 *     .texOffs(0, 0).addBox(-4.5f, -9, -4.5f, 9, 6, 9, 0.5f)
 * }</pre>
 */
public class CompatCubes {
    protected final List<Cube> cubes = new ArrayList<>();
    protected int u, v;
    protected boolean mirror;

    public static CompatCubes create() {
        return new CompatCubes();
    }

    public CompatCubes texOffs(int u, int v) {
        this.u = u;
        this.v = v;
        return this;
    }

    public CompatCubes mirror() {
        return mirror(true);
    }

    public CompatCubes mirror(boolean mirror) {
        this.mirror = mirror;
        return this;
    }

    public CompatCubes addBox(float x, float y, float z, float width, float height, float depth) {
        return addBox(x, y, z, width, height, depth, 0.0F);
    }

    /**
     * @param inflate CubeDeformation (キューブを外側に膨らませる量)
     */
    public CompatCubes addBox(float x, float y, float z, float width, float height, float depth, float inflate) {
        cubes.add(new Cube(u, v, x, y, z, width, height, depth, inflate, mirror));
        return this;
    }

    public List<Cube> getCubes() {
        return cubes;
    }

    public ModelPartBuilder toRaw() {
        ModelPartBuilder builder = ModelPartBuilder.create();
        for (Cube cube : cubes) {
            builder.uv(cube.u, cube.v).mirrored(cube.mirror)
                    .cuboid(cube.x, cube.y, cube.z, cube.width, cube.height, cube.depth, new Dilation(cube.inflate));
        }
        return builder;
    }

    public static class Cube {
        public final int u, v;
        public final float x, y, z;
        public final float width, height, depth;
        public final float inflate;
        public final boolean mirror;

        public Cube(int u, int v, float x, float y, float z, float width, float height, float depth, float inflate, boolean mirror) {
            this.u = u;
            this.v = v;
            this.x = x;
            this.y = y;
            this.z = z;
            this.width = width;
            this.height = height;
            this.depth = depth;
            this.inflate = inflate;
            this.mirror = mirror;
        }
    }
}
