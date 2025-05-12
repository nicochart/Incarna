package fr.factionbedrock.incarna.util;

import java.util.LinkedHashMap;
import java.util.UUID;

public class MilkAbilityCooldownEffectPreserver
{
    public static LinkedHashMap<UUID, Integer> PLAYER_USED_MILK_BUCKET_WITH_ABILITY_COOLDOWN_EFFECT = new LinkedHashMap<UUID, Integer>();

    public static void add(UUID id, int duration)
    {
        PLAYER_USED_MILK_BUCKET_WITH_ABILITY_COOLDOWN_EFFECT.put(id, duration);
    }

    public static int getAndRemove(UUID uuid)
    {
        if (PLAYER_USED_MILK_BUCKET_WITH_ABILITY_COOLDOWN_EFFECT.containsKey(uuid))
        {
            int duration = PLAYER_USED_MILK_BUCKET_WITH_ABILITY_COOLDOWN_EFFECT.get(uuid);
            PLAYER_USED_MILK_BUCKET_WITH_ABILITY_COOLDOWN_EFFECT.remove(uuid, duration);
            return duration;
        }
        return 0;
    }
}
