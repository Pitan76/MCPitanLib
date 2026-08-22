package net.pitan76.mcpitanlib.api.client.render;

import com.mojang.blaze3d.vertex.VertexConsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * 呼び出された頂点操作を記録しておき、あとから本物の{@link VertexConsumer}に流し込むためのプロキシ。
 */
public class RecordingVertexConsumer implements VertexConsumer {

    private final List<Consumer<VertexConsumer>> operations = new ArrayList<>();

    public boolean isEmpty() {
        return operations.isEmpty();
    }

    /**
     * 記録した操作を本物の{@link VertexConsumer}へ順に流し込む。
     * 1フレームに2回流し込まれることは無いので、再生し終えた記録は破棄する。
     */
    public void replay(VertexConsumer consumer) {
        if (consumer == null) return;

        for (Consumer<VertexConsumer> operation : operations) {
            operation.accept(consumer);
        }

        operations.clear();
    }

    @Override
    public VertexConsumer addVertex(float x, float y, float z) {
        operations.add(consumer -> consumer.addVertex(x, y, z));
        return this;
    }

    @Override
    public VertexConsumer setColor(int red, int green, int blue, int alpha) {
        operations.add(consumer -> consumer.setColor(red, green, blue, alpha));
        return this;
    }

    @Override
    public VertexConsumer setColor(int argb) {
        operations.add(consumer -> consumer.setColor(argb));
        return this;
    }

    @Override
    public VertexConsumer setUv(float u, float v) {
        operations.add(consumer -> consumer.setUv(u, v));
        return this;
    }

    @Override
    public VertexConsumer setUv1(int u, int v) {
        operations.add(consumer -> consumer.setUv1(u, v));
        return this;
    }

    @Override
    public VertexConsumer setUv2(int u, int v) {
        operations.add(consumer -> consumer.setUv2(u, v));
        return this;
    }

    @Override
    public VertexConsumer setNormal(float x, float y, float z) {
        operations.add(consumer -> consumer.setNormal(x, y, z));
        return this;
    }

    @Override
    public VertexConsumer setLineWidth(float width) {
        operations.add(consumer -> consumer.setLineWidth(width));
        return this;
    }
}
