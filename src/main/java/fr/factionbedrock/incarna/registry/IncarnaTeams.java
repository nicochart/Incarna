package fr.factionbedrock.incarna.registry;

import fr.factionbedrock.incarna.choice.IncarnaSpecie;
import fr.factionbedrock.incarna.choice.IncarnaTeam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class IncarnaTeams
{
    public static List<IncarnaSpecie> DEFAULT_SPECIES = new ArrayList<>();
    public static List<IncarnaSpecie> OVERWORLD_SPECIES = new ArrayList<>(Arrays.asList(IncarnaSpecies.ELF));
    public static List<IncarnaSpecie> NETHER_SPECIES = new ArrayList<>(Arrays.asList(IncarnaSpecies.NETHERIAN));
    public static List<IncarnaSpecie> END_SPECIES = new ArrayList<>(Arrays.asList(IncarnaSpecies.ENDERIAN));
    public static List<IncarnaSpecie> AERIAL_HELL_SPECIES = new ArrayList<>(Arrays.asList(IncarnaSpecies.LUNAR_ELF));

    public static int MAX_INDEX = 0;
    public static LinkedHashMap<Integer, IncarnaTeam> TEAM_INDEXES = new LinkedHashMap<Integer, IncarnaTeam>();
    public static LinkedHashMap<String, IncarnaTeam> TEAM_NAMES = new LinkedHashMap<String, IncarnaTeam>();
    public static IncarnaTeam DEFAULT = new  IncarnaTeam(0, "default", DEFAULT_SPECIES);
    public static IncarnaTeam OVERWORLD = new  IncarnaTeam(1, "overworld", OVERWORLD_SPECIES, IncarnaPowers.CONSTANT_SPEED_EFFECT, IncarnaPowers.CONSTANT_JUMP_STRENGTH_MODIFIER, IncarnaPowers.VEGAN, IncarnaPowers.HEAL_IN_WATER);
    public static IncarnaTeam NETHER = new  IncarnaTeam(2, "nether", NETHER_SPECIES, IncarnaPowers.CONSTANT_FIRE_RESISTANCE_EFFECT, IncarnaPowers.CARNIVORE, IncarnaPowers.PREVENT_BOW_USE);
    public static IncarnaTeam END = new  IncarnaTeam(3, "end", END_SPECIES, IncarnaPowers.CONSTANT_NIGHT_VISION_EFFECT, IncarnaPowers.CONSTANT_SPEED_MODIFIER, IncarnaPowers.INCREASED_FALL_DAMAGE, IncarnaPowers.DAMAGE_IN_WATER, IncarnaPowers.CANCEL_PROJECTILE_DAMAGE);
    public static IncarnaTeam AERIAL_HELL = new  IncarnaTeam(4, "aerial_hell", AERIAL_HELL_SPECIES, IncarnaPowers.CONSTANT_SLOW_FALLING_EFFECT);
}
