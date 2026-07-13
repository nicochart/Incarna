package fr.factionbedrock.incarna.registry;

import fr.factionbedrock.incarna.Incarna;
import fr.factionbedrock.incarna.block.SpeciesChoiceBlock;
import fr.factionbedrock.incarna.block.TeamChoiceBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class IncarnaBlocks
{
    public static final TeamChoiceBlock TEAM_CHOICE_BLOCK = register("team_choice_block", new TeamChoiceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEDROCK).noOcclusion().lightLevel((state) -> 10)));
    public static final SpeciesChoiceBlock SPECIES_CHOICE_BLOCK = register("species_choice_block", new SpeciesChoiceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BEDROCK).noOcclusion().lightLevel((state) -> 10)));

    public static <T extends Block> T register(String name, T block) {return Registry.register(BuiltInRegistries.BLOCK, Incarna.id(name), block);}

    public static void load() {}
}
