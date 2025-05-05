package fr.factionbedrock.incarna.block;

import fr.factionbedrock.incarna.choice.IncarnaSpecie;
import fr.factionbedrock.incarna.choice.IncarnaTeam;
import fr.factionbedrock.incarna.registry.IncarnaSpecies;
import fr.factionbedrock.incarna.registry.IncarnaTeams;
import fr.factionbedrock.incarna.registry.IncarnaTrackedData;
import fr.factionbedrock.incarna.util.IncarnaHelper;
import fr.factionbedrock.incarna.util.PlayerHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TeamChoiceBlock extends Block
{
    public static final IntProperty TEAM_INDEX = IntProperty.of("team_index", 0, IncarnaTeams.MAX_INDEX);
    public static final BooleanProperty IS_LOCKED = BooleanProperty.of("is_locked");

    public TeamChoiceBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(TEAM_INDEX, 0).with(IS_LOCKED, false));
    }

    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {builder.add(TEAM_INDEX, IS_LOCKED);}

    @Override public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity)
    {
        if (entity instanceof PlayerEntity player)
        {
            IncarnaTeam blockTeam = IncarnaTeams.TEAM_INDEXES.get(state.get(TEAM_INDEX));
            IncarnaTeam previousPlayerTeam = IncarnaTeams.TEAM_NAMES.get(player.getDataTracker().get(IncarnaTrackedData.TEAM));
            if (previousPlayerTeam == IncarnaTeams.DEFAULT && blockTeam != IncarnaTeams.DEFAULT)
            {
                player.getDataTracker().set(IncarnaTrackedData.TEAM, blockTeam.name());
                if (player instanceof ServerPlayerEntity serverPlayer) {IncarnaHelper.onPlayerChangeTeamOrSpecies(serverPlayer, previousPlayerTeam, blockTeam);}
                world.playSound(player, pos, SoundEvents.BLOCK_NOTE_BLOCK_HARP.value(), SoundCategory.BLOCKS, 1.0F, 0.9F + (0.2F * world.random.nextFloat()));
            }
            else if (previousPlayerTeam != IncarnaTeams.DEFAULT && blockTeam == IncarnaTeams.DEFAULT)
            {
                IncarnaSpecie previousPlayerSpecies = IncarnaSpecies.SPECIES_NAMES.get(player.getDataTracker().get(IncarnaTrackedData.SPECIES));
                PlayerHelper.resetPlayerChoices(player);
                if (player instanceof ServerPlayerEntity serverPlayer)
                {
                    IncarnaHelper.onPlayerChangeTeamOrSpecies(serverPlayer, previousPlayerTeam, IncarnaTeams.DEFAULT);
                    IncarnaHelper.onPlayerChangeTeamOrSpecies(serverPlayer, previousPlayerSpecies, IncarnaSpecies.DEFAULT);
                }
                world.playSound(player, pos, SoundEvents.BLOCK_NOTE_BLOCK_FLUTE.value(), SoundCategory.BLOCKS, 1.0F, 0.9F + (0.2F * world.random.nextFloat()));
            }
        }
    }

    @Override protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
    {
        int currentIndex = state.get(TEAM_INDEX);
        boolean locked = state.get(IS_LOCKED);
        if (player.isCreative() && player.isSneaking())
        {
            world.setBlockState(pos, state.with(IS_LOCKED, !locked));
            player.sendMessage(Text.literal("Block is now "+(locked? "un" : "")+"locked with Team : "+IncarnaTeams.TEAM_INDEXES.get(currentIndex).name()), true);
            world.playSound(player, pos, SoundEvents.BLOCK_NOTE_BLOCK_BASEDRUM.value(), SoundCategory.BLOCKS, 1.0F, 0.9F + (0.2F * world.random.nextFloat()));
        }
        else
        {
            if (!locked)
            {
                int newIndex = getNextIndex(currentIndex);
                world.setBlockState(pos, state.with(TEAM_INDEX, newIndex));
                player.sendMessage(Text.literal("Block Team : "+IncarnaTeams.TEAM_INDEXES.get(newIndex).name()), true);
                world.playSound(player, pos, SoundEvents.BLOCK_NOTE_BLOCK_BIT.value(), SoundCategory.BLOCKS, 1.0F, 0.9F + (0.2F * world.random.nextFloat()));
            }
        }
        return super.onUse(state, world, pos, player, hit);
    }

    private static int getNextIndex(int previousIndex)
    {
        int newIndex = previousIndex + 1;
        while (!IncarnaTeams.TEAM_INDEXES.containsKey(newIndex))
        {
            if (newIndex > IncarnaTeams.MAX_INDEX) {newIndex = 0;}
            else {newIndex++;}
        }
        return newIndex;
    }
}
