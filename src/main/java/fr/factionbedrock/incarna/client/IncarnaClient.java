package fr.factionbedrock.incarna.client;

import fr.factionbedrock.incarna.client.render.blockentity.ChoiceBlockRenderer;
import fr.factionbedrock.incarna.registry.IncarnaBlockEntities;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

public class IncarnaClient
{
    public static void registerBlockEntityRenderers()
    {
        BlockEntityRenderers.register(IncarnaBlockEntities.CHOICE_BLOCK, ChoiceBlockRenderer::new);
    }
}
