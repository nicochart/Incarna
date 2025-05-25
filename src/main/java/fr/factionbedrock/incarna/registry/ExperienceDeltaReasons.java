package fr.factionbedrock.incarna.registry;

import fr.factionbedrock.incarna.util.ExperienceDeltaReason;

public class ExperienceDeltaReasons
{
    public static ExperienceDeltaReason TICK = new ExperienceDeltaReason(1);
    public static ExperienceDeltaReason HOSTILE_ENTITY_KILL = new ExperienceDeltaReason(20);
    public static ExperienceDeltaReason NON_ALLY_PLAYER_KILL = new ExperienceDeltaReason(3600);
    public static ExperienceDeltaReason BOSS_KILL = new ExperienceDeltaReason(10800);

    public static ExperienceDeltaReason FRIENDLY_FIRE = new ExperienceDeltaReason(-20);
    public static ExperienceDeltaReason ALLY_PLAYER_KILL = new ExperienceDeltaReason(-10800);
    public static ExperienceDeltaReason DEATH = new ExperienceDeltaReason(-10800);

    public static ExperienceDeltaReason EXPERIENCE_CRYSTAL_CONSUME = new ExperienceDeltaReason(1800);
}
