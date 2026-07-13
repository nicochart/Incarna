package fr.factionbedrock.incarna.block;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.incarna.choice.IncarnaChoice;
import fr.factionbedrock.incarna.choice.IncarnaSpecie;
import fr.factionbedrock.incarna.registry.IncarnaSpecies;
import fr.factionbedrock.incarna.registry.IncarnaTrackedData;
import fr.factionbedrock.incarna.util.PlayerHelper;
import java.util.LinkedHashMap;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class SpeciesChoiceBlock extends ChoiceBlock
{
    public static final MapCodec<SpeciesChoiceBlock> CODEC = simpleCodec(SpeciesChoiceBlock::new);
    public static final IntegerProperty SPECIES_INDEX = IntegerProperty.create("species_index", 0, IncarnaSpecies.MAX_INDEX);

    public SpeciesChoiceBlock(Properties settings)
    {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(SPECIES_INDEX, 0));
    }

    @Override protected MapCodec<? extends ChoiceBlock> codec() {return CODEC;}

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {super.createBlockStateDefinition(builder); builder.add(SPECIES_INDEX);}

    @Override protected int getMaxIndex() {return IncarnaSpecies.MAX_INDEX;}

    @Override protected IntegerProperty getChoiceIndexProperty() {return SPECIES_INDEX;}
    @Override protected LinkedHashMap<Integer, IncarnaSpecie> getIndexesToChoiceList() {return IncarnaSpecies.SPECIES_INDEXES;}
    @Override protected LinkedHashMap<String, IncarnaSpecie> getNamesToChoiceList() {return IncarnaSpecies.SPECIES_NAMES;}
    @Override protected EntityDataAccessor<String> getTrackedData() {return IncarnaTrackedData.SPECIES;}
    @Override protected IncarnaChoice getDefaultChoice() {return IncarnaSpecies.DEFAULT;}

    @Override protected boolean canPlayerChooseChoice(Player player, IncarnaChoice choice) {return choice instanceof IncarnaSpecie species && PlayerHelper.getPlayerTeam(player).allowsSpecies(species);}

    @Override protected String getChoiceTypeString() {return "Species";}
}
