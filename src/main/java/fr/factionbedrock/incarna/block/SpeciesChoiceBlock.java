package fr.factionbedrock.incarna.block;

import fr.factionbedrock.incarna.choice.IncarnaSpecie;
import fr.factionbedrock.incarna.choice.IncarnaTeam;
import fr.factionbedrock.incarna.registry.IncarnaSpecies;
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

public class SpeciesChoiceBlock extends Block
{
    public static final IntProperty SPECIES_INDEX = IntProperty.of("species_index", 0, IncarnaSpecies.MAX_INDEX);
    public static final BooleanProperty IS_LOCKED = BooleanProperty.of("is_locked");

    public SpeciesChoiceBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(SPECIES_INDEX, 0).with(IS_LOCKED, false));
    }

    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {builder.add(SPECIES_INDEX, IS_LOCKED);}

    @Override public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity)
    {
        if (entity instanceof PlayerEntity player)
        {
            IncarnaSpecie blockSpecies = IncarnaSpecies.SPECIES_INDEXES.get(state.get(SPECIES_INDEX));
            IncarnaSpecie previousPlayerSpecies = IncarnaSpecies.SPECIES_NAMES.get(player.getDataTracker().get(IncarnaTrackedData.SPECIES));
            IncarnaTeam playerTeam = IncarnaTeams.TEAM_NAMES.get(player.getDataTracker().get(IncarnaTrackedData.TEAM));
            if (previousPlayerSpecies == IncarnaSpecies.DEFAULT && blockSpecies != IncarnaSpecies.DEFAULT && playerTeam.allowsSpecies(blockSpecies))
            {
                player.getDataTracker().set(IncarnaTrackedData.SPECIES, blockSpecies.name());
                if (player instanceof ServerPlayerEntity serverPlayer) {IncarnaHelper.onPlayerChangeTeamOrSpecies(serverPlayer, previousPlayerSpecies, blockSpecies);}
                world.playSound(player, pos, SoundEvents.BLOCK_NOTE_BLOCK_HARP.value(), SoundCategory.BLOCKS, 1.0F, 0.9F + (0.2F * world.random.nextFloat()));
            }
            else if (previousPlayerSpecies != IncarnaSpecies.DEFAULT && blockSpecies == IncarnaSpecies.DEFAULT && playerTeam.allowsSpecies(blockSpecies))
            {
                player.getDataTracker().set(IncarnaTrackedData.SPECIES, blockSpecies.name());
                if (player instanceof ServerPlayerEntity serverPlayer) {IncarnaHelper.onPlayerChangeTeamOrSpecies(serverPlayer, previousPlayerSpecies, blockSpecies);}
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
                world.setBlockState(pos, state.with(SPECIES_INDEX, getNextIndex(state.get(SPECIES_INDEX))));
                world.playSound(player, pos, SoundEvents.BLOCK_NOTE_BLOCK_BIT.value(), SoundCategory.BLOCKS, 1.0F, 0.9F + (0.2F * world.random.nextFloat()));
            }
        }
        return super.onUse(state, world, pos, player, hit);
    }

    private static int getNextIndex(int previousIndex)
    {
        int newIndex = previousIndex + 1;
        while (!IncarnaSpecies.SPECIES_INDEXES.containsKey(newIndex))
        {
            if (newIndex > IncarnaSpecies.MAX_INDEX) {newIndex = 0;}
            else {newIndex++;}
        }
        return newIndex;
    }
}
