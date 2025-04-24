package fr.factionbedrock.incarna.block;

import fr.factionbedrock.incarna.registry.IncarnaTeams;
import fr.factionbedrock.incarna.registry.IncarnaTrackedData;
import fr.factionbedrock.incarna.util.IncarnaHelper;
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
            String blockTeam = IncarnaTeams.TEAMS.get(state.get(TEAM_INDEX));
            String previousPlayerTeam = player.getDataTracker().get(IncarnaTrackedData.TEAM);
            if (previousPlayerTeam.equals(IncarnaTeams.DEFAULT.value()) && !blockTeam.equals(IncarnaTeams.DEFAULT.value()))
            {
                player.getDataTracker().set(IncarnaTrackedData.TEAM, blockTeam);
                if (player instanceof ServerPlayerEntity serverPlayer) {IncarnaHelper.runFunction(serverPlayer, blockTeam);}
                world.playSound(player, pos, SoundEvents.BLOCK_NOTE_BLOCK_HARP.value(), SoundCategory.BLOCKS, 1.0F, 0.9F + (0.2F * world.random.nextFloat()));
            }
            else if (!previousPlayerTeam.equals(IncarnaTeams.DEFAULT.value()) && blockTeam.equals(IncarnaTeams.DEFAULT.value()))
            {
                player.getDataTracker().set(IncarnaTrackedData.TEAM, blockTeam);
                if (player instanceof ServerPlayerEntity serverPlayer) {IncarnaHelper.runFunction(serverPlayer, blockTeam);}
                world.playSound(player, pos, SoundEvents.BLOCK_NOTE_BLOCK_FLUTE.value(), SoundCategory.BLOCKS, 1.0F, 0.9F + (0.2F * world.random.nextFloat()));
            }
        }
    }

    @Override protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
    {
        if (player.isCreative() && player.isSneaking())
        {
            world.setBlockState(pos, state.with(IS_LOCKED, !state.get(IS_LOCKED)));
            world.playSound(player, pos, SoundEvents.BLOCK_NOTE_BLOCK_BASEDRUM.value(), SoundCategory.BLOCKS, 1.0F, 0.9F + (0.2F * world.random.nextFloat()));
        }
        else
        {
            if (!state.get(IS_LOCKED))
            {
                world.setBlockState(pos, state.with(TEAM_INDEX, getNextIndex(state.get(TEAM_INDEX))));
                world.playSound(player, pos, SoundEvents.BLOCK_NOTE_BLOCK_BIT.value(), SoundCategory.BLOCKS, 1.0F, 0.9F + (0.2F * world.random.nextFloat()));
            }
        }
        return super.onUse(state, world, pos, player, hit);
    }

    private static int getNextIndex(int previousIndex)
    {
        int newIndex = previousIndex + 1;
        while (!IncarnaTeams.TEAMS.containsKey(newIndex))
        {
            if (newIndex > IncarnaTeams.MAX_INDEX) {newIndex = 0;}
            else {newIndex++;}
        }
        return newIndex;
    }
}
