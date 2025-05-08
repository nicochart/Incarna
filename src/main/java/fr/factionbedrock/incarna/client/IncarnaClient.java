package fr.factionbedrock.incarna.client;

import fr.factionbedrock.incarna.client.render.blockentity.ChoiceBlockRenderer;
import fr.factionbedrock.incarna.registry.IncarnaBlockEntities;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class IncarnaClient
{
    public static void registerBlockEntityRenderers()
    {
        BlockEntityRendererFactories.register(IncarnaBlockEntities.CHOICE_BLOCK, ChoiceBlockRenderer::new);
    }
}
