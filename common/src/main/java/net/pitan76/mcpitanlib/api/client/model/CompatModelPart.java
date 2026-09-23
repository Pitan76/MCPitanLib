package net.pitan76.mcpitanlib.api.client.model;

import net.minecraft.client.model.geom.builders.PartDefinition;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * モデルのパーツ (BlockBench の PartDefinition に相当)
 * バージョン非依存のデータとして保持し、登録時に各バージョンの形式へ変換する
 */
public class CompatModelPart {
    protected final CompatCubes cubes;
    protected final CompatPose pose;
    protected final Map<String, CompatModelPart> children = new LinkedHashMap<>();

    protected CompatModelPart(CompatCubes cubes, CompatPose pose) {
        this.cubes = cubes;
        this.pose = pose;
    }

    /**
     * 子パーツを追加する (同名があれば置き換える)
     * @return 追加した子パーツ
     */
    public CompatModelPart addChild(String name, CompatCubes cubes, CompatPose pose) {
        CompatModelPart child = new CompatModelPart(cubes, pose);
        children.put(name, child);
        return child;
    }

    public CompatModelPart addChild(String name, CompatCubes cubes) {
        return addChild(name, cubes, CompatPose.ZERO);
    }

    public CompatModelPart addChild(String name) {
        return addChild(name, CompatCubes.create());
    }

    public CompatModelPart getChild(String name) {
        return children.get(name);
    }

    public boolean hasChild(String name) {
        return children.containsKey(name);
    }

    public Map<String, CompatModelPart> getChildren() {
        return children;
    }

    public CompatCubes getCubes() {
        return cubes;
    }

    public CompatPose getPose() {
        return pose;
    }

    protected void addChildrenTo(PartDefinition parent) {
        children.forEach((name, child) -> child.addTo(parent, name));
    }

    protected void addTo(PartDefinition parent, String name) {
        PartDefinition part = parent.addOrReplaceChild(name, cubes.toRaw(), pose.toRaw());
        addChildrenTo(part);
    }
}
