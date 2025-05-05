package fr.factionbedrock.incarna.registry;

import fr.factionbedrock.incarna.Incarna;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class IncarnaItems
{
    public static final BlockItem TEAM_CHOICE_BLOCK = register("team_choice_block", new BlockItem(IncarnaBlocks.TEAM_CHOICE_BLOCK, new Item.Settings()));
    public static final BlockItem SPECIES_CHOICE_BLOCK = register("species_choice_block", new BlockItem(IncarnaBlocks.SPECIES_CHOICE_BLOCK, new Item.Settings()));

    public static <T extends Item> T register(String name, T item) {return Registry.register(Registries.ITEM, Incarna.id(name), item);}

    public static void load() {}
}
