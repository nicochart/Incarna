package fr.factionbedrock.incarna.registry;

import fr.factionbedrock.incarna.Incarna;
import fr.factionbedrock.incarna.blockentity.ChoiceBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class IncarnaBlockEntities
{
    public static final BlockEntityType<ChoiceBlockEntity> CHOICE_BLOCK = Registry.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, Incarna.id("choice_block"),
            BlockEntityType.Builder.of(ChoiceBlockEntity::new,
                    IncarnaBlocks.TEAM_CHOICE_BLOCK,
                    IncarnaBlocks.SPECIES_CHOICE_BLOCK
            ).build());

    public static void load() {}
}
