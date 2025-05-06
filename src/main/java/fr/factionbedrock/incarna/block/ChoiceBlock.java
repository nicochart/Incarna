package fr.factionbedrock.incarna.block;

import fr.factionbedrock.incarna.choice.IncarnaChoice;
import fr.factionbedrock.incarna.util.PlayerHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.LinkedHashMap;

public abstract class ChoiceBlock extends Block
{
    public static final BooleanProperty IS_LOCKED = BooleanProperty.of("is_locked");

    public ChoiceBlock(Settings settings)
    {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(IS_LOCKED, false));
    }

    @Override protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {builder.add(IS_LOCKED);}

    @Override public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity)
    {
        if (entity instanceof PlayerEntity player)
        {
            IncarnaChoice blockChoice = this.getIndexesToChoiceList().get(this.getChoiceIndex(state));
            IncarnaChoice previousPlayerChoice = this.getNamesToChoiceList().get(player.getDataTracker().get(this.getTrackedData()));
            if (previousPlayerChoice == getDefaultChoice() && blockChoice != getDefaultChoice())
            {
                this.updatePlayerChoice(world, pos, player, previousPlayerChoice, blockChoice);
            }
            else if (previousPlayerChoice != getDefaultChoice() && blockChoice == getDefaultChoice())
            {
                this.updatePlayerChoice(world, pos, player, previousPlayerChoice, blockChoice);
            }
        }
    }

    @Override protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit)
    {
        int currentIndex = this.getChoiceIndex(state);
        boolean locked = state.get(IS_LOCKED);
        if (player.isCreative() && player.isSneaking())
        {
            world.setBlockState(pos, state.with(IS_LOCKED, !locked));
            player.sendMessage(Text.literal("Block is now "+(locked? "un" : "")+"locked with "+this.getChoiceTypeString()+" : "+this.getIndexesToChoiceList().get(currentIndex).name()), true);
            world.playSound(player, pos, SoundEvents.BLOCK_NOTE_BLOCK_BASEDRUM.value(), SoundCategory.BLOCKS, 1.0F, 0.9F + (0.2F * world.random.nextFloat()));
        }
        else
        {
            if (!locked)
            {
                int newIndex = this.getNextIndex(currentIndex);
                world.setBlockState(pos, state.with(this.getChoiceIndexProperty(), newIndex));
                player.sendMessage(Text.literal("Block "+this.getChoiceTypeString()+" : "+this.getIndexesToChoiceList().get(newIndex).name()), true);
                world.playSound(player, pos, SoundEvents.BLOCK_NOTE_BLOCK_BIT.value(), SoundCategory.BLOCKS, 1.0F, 0.9F + (0.2F * world.random.nextFloat()));
            }
            else
            {
                player.sendMessage(Text.literal("Block "+this.getChoiceTypeString()+" : "+this.getIndexesToChoiceList().get(currentIndex).name()), true);
            }
        }
        return super.onUse(state, world, pos, player, hit);
    }

    protected void updatePlayerChoice(World world, BlockPos pos, PlayerEntity player, IncarnaChoice previousChoice, IncarnaChoice newChoice)
    {
        PlayerHelper.updatePlayerChoice(player, this.getTrackedData(), previousChoice, newChoice);
        boolean resetToDefault = newChoice == this.getDefaultChoice();
        SoundEvent soundEvent = resetToDefault ? SoundEvents.BLOCK_NOTE_BLOCK_FLUTE.value() : SoundEvents.BLOCK_NOTE_BLOCK_HARP.value();
        world.playSound(player, pos, soundEvent, SoundCategory.BLOCKS, 1.0F, 0.9F + (0.2F * world.random.nextFloat()));
    }

    protected int getChoiceIndex(BlockState state) {return state.get(this.getChoiceIndexProperty());}
    protected abstract int getMaxIndex();

    protected abstract IntProperty getChoiceIndexProperty();
    protected abstract LinkedHashMap<Integer, ? extends IncarnaChoice> getIndexesToChoiceList();
    protected abstract LinkedHashMap<String, ? extends IncarnaChoice> getNamesToChoiceList();
    protected abstract TrackedData<String> getTrackedData();
    protected abstract IncarnaChoice getDefaultChoice();

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
