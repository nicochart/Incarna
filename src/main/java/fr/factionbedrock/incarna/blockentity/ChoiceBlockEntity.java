package fr.factionbedrock.incarna.blockentity;

import fr.factionbedrock.incarna.registry.IncarnaBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class ChoiceBlockEntity extends BlockEntity
{
    public ChoiceBlockEntity(BlockEntityType<ChoiceBlockEntity> type, BlockPos pos, BlockState state) {super(type, pos, state);}

    public ChoiceBlockEntity(BlockPos pos, BlockState state) {this(IncarnaBlockEntities.CHOICE_BLOCK, pos, state);}
}
