package fr.factionbedrock.incarna.block;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.incarna.choice.IncarnaChoice;
import fr.factionbedrock.incarna.choice.IncarnaTeam;
import fr.factionbedrock.incarna.registry.IncarnaTeams;
import fr.factionbedrock.incarna.registry.IncarnaTrackedData;
import fr.factionbedrock.incarna.util.PlayerHelper;
import java.util.LinkedHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class TeamChoiceBlock extends ChoiceBlock
{
    public static final MapCodec<TeamChoiceBlock> CODEC = simpleCodec(TeamChoiceBlock::new);
    public static final IntegerProperty TEAM_INDEX = IntegerProperty.create("team_index", 0, IncarnaTeams.MAX_INDEX);

    public TeamChoiceBlock(Properties settings)
    {
        super(settings);
        this.registerDefaultState(this.defaultBlockState().setValue(TEAM_INDEX, 0));
    }

    @Override protected MapCodec<? extends ChoiceBlock> codec() {return CODEC;}

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {super.createBlockStateDefinition(builder); builder.add(TEAM_INDEX);}

    @Override protected void updatePlayerChoice(Level world, BlockPos pos, Player player, IncarnaChoice previousChoice, IncarnaChoice newChoice)
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

    @Override protected IntegerProperty getChoiceIndexProperty() {return TEAM_INDEX;}
    @Override protected LinkedHashMap<Integer, IncarnaTeam> getIndexesToChoiceList() {return IncarnaTeams.TEAM_INDEXES;}
    @Override protected LinkedHashMap<String, IncarnaTeam> getNamesToChoiceList() {return IncarnaTeams.TEAM_NAMES;}
    @Override protected EntityDataAccessor<String> getTrackedData() {return IncarnaTrackedData.TEAM;}
    @Override protected IncarnaChoice getDefaultChoice() {return IncarnaTeams.DEFAULT;}

    @Override protected boolean canPlayerChooseChoice(Player player, IncarnaChoice choice) {return true;}

    @Override protected String getChoiceTypeString() {return "Team";}
}
