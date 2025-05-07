package fr.factionbedrock.incarna.blockentity;

import fr.factionbedrock.incarna.registry.IncarnaBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class ChoiceBlockEntity extends BlockEntity
{
    public ChoiceBlockEntity(BlockEntityType<ChoiceBlockEntity> type, BlockPos pos, BlockState state) {super(type, pos, state);}

    public ChoiceBlockEntity(BlockPos pos, BlockState state) {this(IncarnaBlockEntities.CHOICE_BLOCK, pos, state);}
}
