package fr.factionbedrock.incarna.client.render.blockentity;

import fr.factionbedrock.incarna.Incarna;
import fr.factionbedrock.incarna.blockentity.ChoiceBlockEntity;
import fr.factionbedrock.incarna.registry.IncarnaBlocks;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.CommonColors;
import org.joml.Matrix4f;

import static net.minecraft.client.renderer.RenderStateShard.*;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.VertexFormat;

public class ChoiceBlockRenderer<T extends ChoiceBlockEntity> implements BlockEntityRenderer<T>
{
    public static final ResourceLocation TEAM_CHOICE_BLOCK_OUTER = Incarna.id("textures/block/team_choice_block.png");
    public static final ResourceLocation SPECIES_CHOICE_BLOCK_OUTER = Incarna.id("textures/block/species_choice_block.png");
    public static final ResourceLocation CHOICE_BLOCK_INNER_BACKGROUND = Incarna.id("textures/entity/choice_block_background.png");
    public static final ResourceLocation CHOICE_BLOCK_INNER = Incarna.id("textures/entity/choice_block.png");

    public ChoiceBlockRenderer(BlockEntityRendererProvider.Context ctx) {}

    @Override public void render(T blockEntity, float tickDelta, PoseStack matrixStack, MultiBufferSource vertexConsumerProvider, int light, int overlay)
    {
        ResourceLocation outerTexture = blockEntity.getBlockState().is(IncarnaBlocks.TEAM_CHOICE_BLOCK) ? TEAM_CHOICE_BLOCK_OUTER : SPECIES_CHOICE_BLOCK_OUTER;
        Matrix4f matrix4f = matrixStack.last().pose();
        this.renderInnerSides(matrix4f, vertexConsumerProvider.getBuffer(getInnerLayer()));
        this.renderOuterSides(matrix4f, vertexConsumerProvider.getBuffer(getOuterLayer(outerTexture)));
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
        vertices.addVertex(model, x1, y1, z1).setColor(CommonColors.WHITE).setUv(0, 1).setLight(0xF000F0).setNormal(z2, z3, z4);
        vertices.addVertex(model, x2, y1, z2).setColor(CommonColors.WHITE).setUv(1, 1).setLight(0xF000F0).setNormal(z2, z3, z4);
        vertices.addVertex(model, x2, y2, z3).setColor(CommonColors.WHITE).setUv(1, 0).setLight(0xF000F0).setNormal(z2, z3, z4);
        vertices.addVertex(model, x1, y2, z4).setColor(CommonColors.WHITE).setUv(0, 0).setLight(0xF000F0).setNormal(z2, z3, z4);
    }

    private void renderInnerSides(Matrix4f matrix, VertexConsumer vertexConsumer)
    {
        this.renderInnerSide(matrix, vertexConsumer, 0.02F, 0.98F, 0.02F, 0.98F, 0.98F, 0.98F, 0.98F, 0.98F, Direction.SOUTH);
        this.renderInnerSide(matrix, vertexConsumer, 0.02F, 0.98F, 0.98F, 0.02F, 0.02F, 0.02F, 0.02F, 0.02F, Direction.NORTH);
        this.renderInnerSide(matrix, vertexConsumer, 0.98F, 0.98F, 0.98F, 0.02F, 0.02F, 0.98F, 0.98F, 0.02F, Direction.EAST);
        this.renderInnerSide(matrix, vertexConsumer, 0.02F, 0.02F, 0.02F, 0.98F, 0.02F, 0.98F, 0.98F, 0.02F, Direction.WEST);
        this.renderInnerSide(matrix, vertexConsumer, 0.02F, 0.98F, 0.02F, 0.02F, 0.02F, 0.02F, 0.98F, 0.98F, Direction.DOWN);
        this.renderInnerSide(matrix, vertexConsumer, 0.02F, 0.98F, 0.98F, 0.98F, 0.98F, 0.98F, 0.02F, 0.02F, Direction.UP);
    }

    private void renderInnerSide(Matrix4f model, VertexConsumer vertices, float x1, float x2, float y1, float y2, float z1, float z2, float z3, float z4, Direction side)
    {
        vertices.addVertex(model, x1, y1, z1);
        vertices.addVertex(model, x2, y1, z2);
        vertices.addVertex(model, x2, y2, z3);
        vertices.addVertex(model, x1, y2, z4);
    }

    //inspired of CUTOUT RenderLayer
    protected static RenderType getOuterLayer(ResourceLocation textureIdentifier)
    {
        return RenderType.create(
                "choice_block_outer_layer",
                DefaultVertexFormat.BLOCK,
                VertexFormat.Mode.QUADS,
                786432,
                true,
                false,
                RenderType.CompositeState.builder().setLightmapState(LIGHTMAP).setShaderState(RENDERTYPE_CUTOUT_SHADER).setTextureState(getOuterLayerRenderPhaseTexture(textureIdentifier)).createCompositeState(true)
        );
    }

    public static RenderStateShard.TextureStateShard getOuterLayerRenderPhaseTexture(ResourceLocation textureIdentifier)
    {
        return new RenderStateShard.TextureStateShard(textureIdentifier, false, false);
    }

    //inspired of RenderLayer.END_GATEWAY
    protected static RenderType getInnerLayer()
    {
        return RenderType.create(
                "choice_block_inner_layer",
                DefaultVertexFormat.POSITION,
                VertexFormat.Mode.QUADS,
                1536,
                false,
                false,
                RenderType.CompositeState.builder()
                        .setShaderState(RenderStateShard.RENDERTYPE_END_GATEWAY_SHADER)
                        .setTextureState(
                                RenderStateShard.MultiTextureStateShard.builder()
                                        .add(CHOICE_BLOCK_INNER_BACKGROUND, false, false)
                                        .add(CHOICE_BLOCK_INNER, false, false)
                                        .build()
                        )
                        .createCompositeState(false)
        );
    }
}
