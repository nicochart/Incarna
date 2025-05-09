package fr.factionbedrock.incarna.registry;

import fr.factionbedrock.incarna.Incarna;
import fr.factionbedrock.incarna.block.SpeciesChoiceBlock;
import fr.factionbedrock.incarna.block.TeamChoiceBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class IncarnaBlocks
{
    public static final TeamChoiceBlock TEAM_CHOICE_BLOCK = register("team_choice_block", new TeamChoiceBlock(AbstractBlock.Settings.copy(Blocks.BEDROCK).nonOpaque().luminance((state) -> 10)));
    public static final SpeciesChoiceBlock SPECIES_CHOICE_BLOCK = register("species_choice_block", new SpeciesChoiceBlock(AbstractBlock.Settings.copy(Blocks.BEDROCK).nonOpaque().luminance((state) -> 10)));

    public static <T extends Block> T register(String name, T block) {return Registry.register(Registries.BLOCK, Incarna.id(name), block);}

    public static void load() {}
}
