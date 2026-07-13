package fr.factionbedrock.incarna.registry;

import fr.factionbedrock.incarna.Incarna;
import fr.factionbedrock.incarna.item.ExperienceCrystalItem;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;

public class IncarnaItems
{
    public static final BlockItem TEAM_CHOICE_BLOCK = register("team_choice_block", new BlockItem(IncarnaBlocks.TEAM_CHOICE_BLOCK, new Item.Properties()));
    public static final BlockItem SPECIES_CHOICE_BLOCK = register("species_choice_block", new BlockItem(IncarnaBlocks.SPECIES_CHOICE_BLOCK, new Item.Properties()));
    public static final Item EXPERIENCE_CRYSTAL = register("experience_crystal", new ExperienceCrystalItem(new Item.Properties().food(new FoodProperties.Builder().alwaysEdible().nutrition(5).saturationModifier(0.6F).build())));

    public static <T extends Item> T register(String name, T item) {return Registry.register(BuiltInRegistries.ITEM, Incarna.id(name), item);}

    public static void load() {}
}
