package net.pitan76.mcpitanlib.api.client.model;

import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.TexturedModelData;
import net.pitan76.mcpitanlib.api.client.render.EntityModelLayerContext;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * バージョン非依存のモデルビルダー
 * BlockBench の "Modded Entity" 形式の書き出しをほぼそのまま置き換えられる
 * <pre>{@code
 * CompatModelBuilder model = CompatModelBuilder.create(128, 128);
 * CompatModelPart head = model.root().addChild("Head",
 *         CompatCubes.create().texOffs(0, 15).addBox(-4, -8, -4, 8, 8, 8),
 *         CompatPose.offset(0, 0, 0));
 * head.addChild("cube_r1", CompatCubes.create()...,
 *         CompatPose.offsetAndRotation(0, 0, 0, -0.6545f, 0, 0));
 * }</pre>
 */
public class CompatModelBuilder {
    public static final String HEAD = "head";
    public static final String HAT = "hat";
    public static final String BODY = "body";
    public static final String RIGHT_ARM = "right_arm";
    public static final String LEFT_ARM = "left_arm";
    public static final String RIGHT_LEG = "right_leg";
    public static final String LEFT_LEG = "left_leg";

    public static final String[] HUMANOID_PARTS = {HEAD, HAT, BODY, RIGHT_ARM, LEFT_ARM, RIGHT_LEG, LEFT_LEG};

    protected final int textureWidth;
    protected final int textureHeight;
    protected final CompatModelPart root;

    protected CompatModelBuilder(int textureWidth, int textureHeight, CompatModelPart root) {
        this.textureWidth = textureWidth;
        this.textureHeight = textureHeight;
        this.root = root;
    }

    public static CompatModelBuilder create(int textureWidth, int textureHeight) {
        return new CompatModelBuilder(textureWidth, textureHeight, new CompatModelPart(CompatCubes.create(), CompatPose.ZERO));
    }

    public CompatModelPart root() {
        return root;
    }

    public int getTextureWidth() {
        return textureWidth;
    }

    public int getTextureHeight() {
        return textureHeight;
    }

    /**
     * 人型モデル (防具など) 用に変換したビルダーを返す。
     * BlockBench のパーツ名 (Head, Body, RightArm...) をバニラの名前 (head, body, right_arm...) に揃え、
     * 足りないパーツは空で補う。
     */
    public CompatModelBuilder toHumanoid() {
        CompatModelPart newRoot = new CompatModelPart(root.cubes, root.pose);
        root.children.forEach((name, child) -> newRoot.children.put(normalizeHumanoidName(name), child));

        // このバージョンでは hat はルート直下のパーツ
        for (String name : HUMANOID_PARTS) {
            if (!newRoot.hasChild(name))
                newRoot.addChild(name);
        }

        return new CompatModelBuilder(textureWidth, textureHeight, newRoot);
    }

    private static final Map<String, String> HUMANOID_ALIASES = new HashMap<>();

    static {
        for (String name : HUMANOID_PARTS)
            HUMANOID_ALIASES.put(name.replace("_", ""), name);
    }

    protected static String normalizeHumanoidName(String name) {
        String key = name.replace("_", "").toLowerCase(Locale.ROOT);
        return HUMANOID_ALIASES.getOrDefault(key, name);
    }

    public ModelData toMesh() {
        ModelData mesh = new ModelData();
        root.addChildrenTo(mesh.getRoot());
        return mesh;
    }

    public TexturedModelData toLayerDefinition() {
        return TexturedModelData.of(toMesh(), textureWidth, textureHeight);
    }

    public ModelPart bake() {
        return toLayerDefinition().createModel();
    }

    /**
     * {@link net.pitan76.mcpitanlib.api.client.registry.CompatRegistryClient#registerEntityModelLayer} に渡せる形にする
     */
    public EntityModelLayerContext build() {
        return new EntityModelLayerContext(toMesh(), textureWidth, textureHeight);
    }
}
