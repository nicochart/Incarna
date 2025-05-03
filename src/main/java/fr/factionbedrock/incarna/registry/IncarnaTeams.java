package fr.factionbedrock.incarna.registry;

import fr.factionbedrock.incarna.choice.IncarnaTeam;

import java.util.LinkedHashMap;

public class IncarnaTeams
{
    public static int MAX_INDEX = 0;
    public static LinkedHashMap<Integer, IncarnaTeam> TEAM_INDEXES = new LinkedHashMap<Integer, IncarnaTeam>();
    public static LinkedHashMap<String, IncarnaTeam> TEAM_NAMES = new LinkedHashMap<String, IncarnaTeam>();
    public static  IncarnaTeam DEFAULT = new  IncarnaTeam(0, "default");
    public static  IncarnaTeam OVERWORLD = new  IncarnaTeam(1, "overworld", IncarnaPowers.CONSTANT_SPEED_EFFECT, IncarnaPowers.CONSTANT_JUMP_STRENGTH_MODIFIER, IncarnaPowers.VEGAN, IncarnaPowers.HEAL_IN_WATER);
    public static  IncarnaTeam NETHER = new  IncarnaTeam(2, "nether", IncarnaPowers.CONSTANT_FIRE_RESISTANCE_EFFECT, IncarnaPowers.CARNIVORE);
    public static  IncarnaTeam END = new  IncarnaTeam(3, "end", IncarnaPowers.CONSTANT_NIGHT_VISION_EFFECT, IncarnaPowers.CONSTANT_SPEED_MODIFIER, IncarnaPowers.INCREASED_FALL_DAMAGE, IncarnaPowers.DAMAGE_IN_WATER, IncarnaPowers.CANCEL_PROJECTILE_DAMAGE);
    public static  IncarnaTeam AERIAL_HELL = new  IncarnaTeam(4, "aerial_hell", IncarnaPowers.CONSTANT_SLOW_FALLING_EFFECT);
}
