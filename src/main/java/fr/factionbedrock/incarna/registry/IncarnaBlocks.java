package fr.factionbedrock.incarna.registry;

import fr.factionbedrock.incarna.Incarna;
import fr.factionbedrock.incarna.block.TeamChoiceBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class IncarnaBlocks
{
    public static final TeamChoiceBlock TEAM_CHOICE_BLOCK = register("team_choice_block", new TeamChoiceBlock(AbstractBlock.Settings.create()));

    public static <T extends Block> T register(String name, T block) {return Registry.register(Registries.BLOCK, Incarna.id(name), block);}

    public static void load() {}
}
