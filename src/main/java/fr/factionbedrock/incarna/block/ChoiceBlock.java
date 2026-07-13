package fr.factionbedrock.incarna.block;

import com.mojang.serialization.MapCodec;
import fr.factionbedrock.incarna.blockentity.ChoiceBlockEntity;
import fr.factionbedrock.incarna.choice.IncarnaChoice;
import fr.factionbedrock.incarna.util.PlayerHelper;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

public abstract class ChoiceBlock extends BaseEntityBlock
{
    public static final BooleanProperty IS_LOCKED = BooleanProperty.create("is_locked");

    public ChoiceBlock(Properties settings)
    {
        super(settings);
        this.registerDefaultState(this.stateDefinition.any().setValue(IS_LOCKED, false));
    }

    @Override protected abstract MapCodec<? extends ChoiceBlock> codec();
    @Override public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {return new ChoiceBlockEntity(pos, state);}

    @Override protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {builder.add(IS_LOCKED);}

    @Override public void stepOn(Level world, BlockPos pos, BlockState state, Entity entity)
    {
        if (entity instanceof Player player)
        {
            IncarnaChoice blockChoice = this.getIndexesToChoiceList().get(this.getChoiceIndex(state));
            IncarnaChoice previousPlayerChoice = this.getNamesToChoiceList().get(player.getEntityData().get(this.getTrackedData()));
            if (previousPlayerChoice == getDefaultChoice() && blockChoice != getDefaultChoice() && this.canPlayerChooseChoice(player, blockChoice))
            {
                this.updatePlayerChoice(world, pos, player, previousPlayerChoice, blockChoice);
            }
            else if (previousPlayerChoice != getDefaultChoice() && blockChoice == getDefaultChoice())
            {
                this.updatePlayerChoice(world, pos, player, previousPlayerChoice, blockChoice);
            }
        }
    }

    @Override protected InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit)
    {
        int currentIndex = this.getChoiceIndex(state);
        boolean locked = state.getValue(IS_LOCKED);
        if (player.isCreative() && player.isShiftKeyDown())
        {
            world.setBlockAndUpdate(pos, state.setValue(IS_LOCKED, !locked));
            player.displayClientMessage(Component.literal("Block is now "+(locked? "un" : "")+"locked with "+this.getChoiceTypeString()+" : "+this.getIndexesToChoiceList().get(currentIndex).name()), true);
            world.playSound(player, pos, SoundEvents.NOTE_BLOCK_BASEDRUM.value(), SoundSource.BLOCKS, 1.0F, 0.9F + (0.2F * world.random.nextFloat()));
        }
        else
        {
            if (!locked)
            {
                int newIndex = this.getNextIndex(currentIndex);
                world.setBlockAndUpdate(pos, state.setValue(this.getChoiceIndexProperty(), newIndex));
                player.displayClientMessage(Component.literal("Block "+this.getChoiceTypeString()+" : "+this.getIndexesToChoiceList().get(newIndex).name()), true);
                world.playSound(player, pos, SoundEvents.NOTE_BLOCK_BIT.value(), SoundSource.BLOCKS, 1.0F, 0.9F + (0.2F * world.random.nextFloat()));
            }
            else
            {
                player.displayClientMessage(Component.literal("Block "+this.getChoiceTypeString()+" : "+this.getIndexesToChoiceList().get(currentIndex).name()), true);
            }
        }
        return super.useWithoutItem(state, world, pos, player, hit);
    }

    protected void updatePlayerChoice(Level world, BlockPos pos, Player player, IncarnaChoice previousChoice, IncarnaChoice newChoice)
    {
        PlayerHelper.updatePlayerChoice(player, this.getTrackedData(), previousChoice, newChoice);
        boolean resetToDefault = newChoice == this.getDefaultChoice();
        SoundEvent soundEvent = resetToDefault ? SoundEvents.NOTE_BLOCK_FLUTE.value() : SoundEvents.NOTE_BLOCK_HARP.value();
        world.playSound(player, pos, soundEvent, SoundSource.BLOCKS, 1.0F, 0.9F + (0.2F * world.random.nextFloat()));
    }

    protected int getChoiceIndex(BlockState state) {return state.getValue(this.getChoiceIndexProperty());}
    protected abstract int getMaxIndex();

    protected abstract IntegerProperty getChoiceIndexProperty();
    protected abstract LinkedHashMap<Integer, ? extends IncarnaChoice> getIndexesToChoiceList();
    protected abstract LinkedHashMap<String, ? extends IncarnaChoice> getNamesToChoiceList();
    protected abstract EntityDataAccessor<String> getTrackedData();
    protected abstract IncarnaChoice getDefaultChoice();

    protected abstract boolean canPlayerChooseChoice(Player player, IncarnaChoice choice);

    protected abstract String getChoiceTypeString();

    private int getNextIndex(int previousIndex)
    {
        int newIndex = previousIndex + 1;
        while (!this.getIndexesToChoiceList().containsKey(newIndex))
        {
            if (newIndex > this.getMaxIndex()) {newIndex = 0;}
            else {newIndex++;}
        }
        return newIndex;
    }
}
