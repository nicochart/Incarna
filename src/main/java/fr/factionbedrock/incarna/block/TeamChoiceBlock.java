package fr.factionbedrock.incarna.block;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.incarna.choice.IncarnaChoice;
import fr.factionbedrock.incarna.choice.IncarnaTeam;
import fr.factionbedrock.incarna.registry.IncarnaTeams;
import fr.factionbedrock.incarna.registry.IncarnaTrackedData;
import fr.factionbedrock.incarna.util.PlayerHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.LinkedHashMap;

public class TeamChoiceBlock extends ChoiceBlock
{
    public static final MapCodec<TeamChoiceBlock> CODEC = createCodec(TeamChoiceBlock::new);
    public static final IntProperty TEAM_INDEX = IntProperty.of("team_index", 0, IncarnaTeams.MAX_INDEX);

    public TeamChoiceBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(TEAM_INDEX, 0));
    }

    @Override protected MapCodec<? extends ChoiceBlock> getCodec() {return CODEC;}

    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {super.appendProperties(builder); builder.add(TEAM_INDEX);}

    @Override protected void updatePlayerChoice(World world, BlockPos pos, PlayerEntity player, IncarnaChoice previousChoice, IncarnaChoice newChoice)
    {
        //also resets species if resets team
        super.updatePlayerChoice(world, pos, player, previousChoice, newChoice);
        boolean resetToDefault = newChoice == this.getDefaultChoice();
        if (resetToDefault)
        {
            PlayerHelper.resetPlayerSpecies(player);
        }
    }

    @Override protected int getMaxIndex() {return IncarnaTeams.MAX_INDEX;}

    @Override protected IntProperty getChoiceIndexProperty() {return TEAM_INDEX;}
    @Override protected LinkedHashMap<Integer, IncarnaTeam> getIndexesToChoiceList() {return IncarnaTeams.TEAM_INDEXES;}
    @Override protected LinkedHashMap<String, IncarnaTeam> getNamesToChoiceList() {return IncarnaTeams.TEAM_NAMES;}
    @Override protected TrackedData<String> getTrackedData() {return IncarnaTrackedData.TEAM;}
    @Override protected IncarnaChoice getDefaultChoice() {return IncarnaTeams.DEFAULT;}

    @Override protected String getChoiceTypeString() {return "Team";}
}
