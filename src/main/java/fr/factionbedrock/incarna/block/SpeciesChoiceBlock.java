package fr.factionbedrock.incarna.block;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.incarna.choice.IncarnaChoice;
import fr.factionbedrock.incarna.choice.IncarnaSpecie;
import fr.factionbedrock.incarna.registry.IncarnaSpecies;
import fr.factionbedrock.incarna.registry.IncarnaTrackedData;
import fr.factionbedrock.incarna.util.PlayerHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;

import java.util.LinkedHashMap;

public class SpeciesChoiceBlock extends ChoiceBlock
{
    public static final MapCodec<SpeciesChoiceBlock> CODEC = createCodec(SpeciesChoiceBlock::new);
    public static final IntProperty SPECIES_INDEX = IntProperty.of("species_index", 0, IncarnaSpecies.MAX_INDEX);

    public SpeciesChoiceBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(SPECIES_INDEX, 0));
    }

    @Override protected MapCodec<? extends ChoiceBlock> getCodec() {return CODEC;}

    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {super.appendProperties(builder); builder.add(SPECIES_INDEX);}

    @Override protected int getMaxIndex() {return IncarnaSpecies.MAX_INDEX;}

    @Override protected IntProperty getChoiceIndexProperty() {return SPECIES_INDEX;}
    @Override protected LinkedHashMap<Integer, IncarnaSpecie> getIndexesToChoiceList() {return IncarnaSpecies.SPECIES_INDEXES;}
    @Override protected LinkedHashMap<String, IncarnaSpecie> getNamesToChoiceList() {return IncarnaSpecies.SPECIES_NAMES;}
    @Override protected TrackedData<String> getTrackedData() {return IncarnaTrackedData.SPECIES;}
    @Override protected IncarnaChoice getDefaultChoice() {return IncarnaSpecies.DEFAULT;}

    @Override protected boolean canPlayerChooseChoice(PlayerEntity player, IncarnaChoice choice) {return choice instanceof IncarnaSpecie species && PlayerHelper.getPlayerTeam(player).allowsSpecies(species);}

    @Override protected String getChoiceTypeString() {return "Species";}
}
