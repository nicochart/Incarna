package fr.factionbedrock.incarna.registry;

import fr.factionbedrock.incarna.Incarna;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;

public class IncarnaTags
{
    public static class Items
    {
        public static final TagKey<Item> MEAT = tag("meat");

        private static TagKey<Item> tag(String name) {return TagKey.create(Registries.ITEM, Incarna.id(name));}
    }

    public static class EntityTypes
    {
        public static final TagKey<EntityType<?>> HOSTILE = tag("hostile");
        public static final TagKey<EntityType<?>> BOSS = tag("boss");

        private static TagKey<EntityType<?>> tag(String name) {return TagKey.create(Registries.ENTITY_TYPE, Incarna.id(name));}
    }
}
