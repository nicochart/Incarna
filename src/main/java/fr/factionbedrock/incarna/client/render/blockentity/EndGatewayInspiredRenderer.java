package fr.factionbedrock.incarna.client.render.blockentity;

import fr.factionbedrock.incarna.blockentity.ChoiceBlockEntity;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Direction;
import org.joml.Matrix4f;

public class EndGatewayInspiredRenderer<T extends ChoiceBlockEntity> implements BlockEntityRenderer<T>
{
    public EndGatewayInspiredRenderer(BlockEntityRendererFactory.Context ctx) {}

    public void render(T blockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j)
    {
        Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();
        this.renderSides(blockEntity, matrix4f, vertexConsumerProvider.getBuffer(this.getLayer()));
    }

    private void renderSides(T entity, Matrix4f matrix, VertexConsumer vertexConsumer)
    {
        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, Direction.SOUTH);
        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, Direction.NORTH);
        this.renderSide(entity, matrix, vertexConsumer, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.EAST);
        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.WEST);
        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, Direction.DOWN);
        this.renderSide(entity, matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, Direction.UP);
    }

    private void renderSide(T entity, Matrix4f model, VertexConsumer vertices, float x1, float x2, float y1, float y2, float z1, float z2, float z3, float z4, Direction side)
    {
        vertices.vertex(model, x1, y1, z1);
        vertices.vertex(model, x2, y1, z2);
        vertices.vertex(model, x2, y2, z3);
        vertices.vertex(model, x1, y2, z4);
    }

    protected RenderLayer getLayer() {return RenderLayer.getEndPortal();}
}
