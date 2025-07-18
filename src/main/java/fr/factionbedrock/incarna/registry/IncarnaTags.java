package fr.factionbedrock.incarna.registry;

import fr.factionbedrock.incarna.Incarna;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class IncarnaTags
{
    public static class Items
    {
        public static final TagKey<Item> MEAT = tag("meat");

        private static TagKey<Item> tag(String name) {return TagKey.of(RegistryKeys.ITEM, Incarna.id(name));}
    }

    public static class EntityTypes
    {
        public static final TagKey<EntityType<?>> HOSTILE = tag("hostile");
        public static final TagKey<EntityType<?>> BOSS = tag("boss");

        private static TagKey<EntityType<?>> tag(String name) {return TagKey.of(RegistryKeys.ENTITY_TYPE, Incarna.id(name));}
    }
}
