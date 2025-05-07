package fr.factionbedrock.incarna.registry;

import fr.factionbedrock.incarna.Incarna;
import fr.factionbedrock.incarna.blockentity.ChoiceBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class IncarnaBlockEntities
{
    public static final BlockEntityType<ChoiceBlockEntity> CHOICE_BLOCK = Registry.register(Registries.BLOCK_ENTITY_TYPE, Incarna.id("choice_block"),
            BlockEntityType.Builder.create(ChoiceBlockEntity::new,
                    IncarnaBlocks.TEAM_CHOICE_BLOCK,
                    IncarnaBlocks.SPECIES_CHOICE_BLOCK
            ).build());

    public static void load() {}
}
