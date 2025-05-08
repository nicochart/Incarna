package fr.factionbedrock.incarna.client.render.blockentity;

import fr.factionbedrock.incarna.Incarna;
import fr.factionbedrock.incarna.blockentity.ChoiceBlockEntity;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;
import org.joml.Matrix4f;

import static net.minecraft.client.render.RenderPhase.*;

public class EndGatewayInspiredRenderer<T extends ChoiceBlockEntity> implements BlockEntityRenderer<T>
{
    public static final Identifier CHOICE_BLOCK_OUTER = Incarna.id("textures/block/team_choice_block.png");
    public static final Identifier CHOICE_BLOCK_INNER_BACKGROUND = Incarna.id("textures/entity/choice_block_background.png");
    public static final Identifier CHOICE_BLOCK_INNER = Incarna.id("textures/entity/choice_block.png");

    public EndGatewayInspiredRenderer(BlockEntityRendererFactory.Context ctx) {}

    public void render(T blockEntity, float f, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, int j)
    {
        Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();
        this.renderInnerSides(matrix4f, vertexConsumerProvider.getBuffer(getInnerLayer()));
        this.renderOuterSides(matrix4f, vertexConsumerProvider.getBuffer(getOuterLayer()));
    }

    private void renderOuterSides(Matrix4f matrix, VertexConsumer vertexConsumer)
    {
        this.renderOuterSide(matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, Direction.SOUTH);
        this.renderOuterSide(matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, Direction.NORTH);
        this.renderOuterSide(matrix, vertexConsumer, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.EAST);
        this.renderOuterSide(matrix, vertexConsumer, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 1.0F, 1.0F, 0.0F, Direction.WEST);
        this.renderOuterSide(matrix, vertexConsumer, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, Direction.DOWN);
        this.renderOuterSide(matrix, vertexConsumer, 0.0F, 1.0F, 1.0F, 1.0F, 1.0F, 1.0F, 0.0F, 0.0F, Direction.UP);
    }

    private void renderOuterSide(Matrix4f model, VertexConsumer vertices, float x1, float x2, float y1, float y2, float z1, float z2, float z3, float z4, Direction side)
    {
        vertices.vertex(model, x1, y1, z1).color(Colors.WHITE).texture(0, 1).light(0xF000F0).normal(z2, z3, z4);
        vertices.vertex(model, x2, y1, z2).color(Colors.WHITE).texture(1, 1).light(0xF000F0).normal(z2, z3, z4);
        vertices.vertex(model, x2, y2, z3).color(Colors.WHITE).texture(1, 0).light(0xF000F0).normal(z2, z3, z4);
        vertices.vertex(model, x1, y2, z4).color(Colors.WHITE).texture(0, 0).light(0xF000F0).normal(z2, z3, z4);
    }

    private void renderInnerSides(Matrix4f matrix, VertexConsumer vertexConsumer)
    {
        this.renderInnerSide(matrix, vertexConsumer, 0.15F, 0.85F, 0.15F, 0.85F, 0.85F, 0.85F, 0.85F, 0.85F, Direction.SOUTH);
        this.renderInnerSide(matrix, vertexConsumer, 0.15F, 0.85F, 0.85F, 0.15F, 0.15F, 0.15F, 0.15F, 0.15F, Direction.NORTH);
        this.renderInnerSide(matrix, vertexConsumer, 0.85F, 0.85F, 0.85F, 0.15F, 0.15F, 0.85F, 0.85F, 0.15F, Direction.EAST);
        this.renderInnerSide(matrix, vertexConsumer, 0.15F, 0.15F, 0.15F, 0.85F, 0.15F, 0.85F, 0.85F, 0.15F, Direction.WEST);
        this.renderInnerSide(matrix, vertexConsumer, 0.15F, 0.85F, 0.15F, 0.15F, 0.15F, 0.15F, 0.85F, 0.85F, Direction.DOWN);
        this.renderInnerSide(matrix, vertexConsumer, 0.15F, 0.85F, 0.85F, 0.85F, 0.85F, 0.85F, 0.15F, 0.15F, Direction.UP);
    }

    private void renderInnerSide(Matrix4f model, VertexConsumer vertices, float x1, float x2, float y1, float y2, float z1, float z2, float z3, float z4, Direction side)
    {
        vertices.vertex(model, x1, y1, z1);
        vertices.vertex(model, x2, y1, z2);
        vertices.vertex(model, x2, y2, z3);
        vertices.vertex(model, x1, y2, z4);
    }


    public static final RenderPhase.Texture CHOICE_BLOCK_OUTER_LAYER_RENDER_PHASE = new RenderPhase.Texture(CHOICE_BLOCK_OUTER, false, false);
    protected static RenderLayer getOuterLayer()
    {
        return RenderLayer.of(
                "choice_block_outer_layer",
                VertexFormats.POSITION_COLOR_TEXTURE_LIGHT_NORMAL,
                VertexFormat.DrawMode.QUADS,
                786432,
                true,
                false,
                RenderLayer.MultiPhaseParameters.builder().lightmap(ENABLE_LIGHTMAP).program(CUTOUT_PROGRAM).texture(CHOICE_BLOCK_OUTER_LAYER_RENDER_PHASE).build(true)
        );
    }

    protected static RenderLayer getInnerLayer()
    {
        return RenderLayer.of(
                "choice_block_inner_layer",
                VertexFormats.POSITION,
                VertexFormat.DrawMode.QUADS,
                1536,
                false,
                false,
                RenderLayer.MultiPhaseParameters.builder()
                        .program(RenderPhase.END_GATEWAY_PROGRAM)
                        .texture(
                                RenderPhase.Textures.create()
                                        .add(CHOICE_BLOCK_INNER_BACKGROUND, false, false)
                                        .add(CHOICE_BLOCK_INNER, false, false)
                                        .build()
                        )
                        .build(false)
        );
    }
}
