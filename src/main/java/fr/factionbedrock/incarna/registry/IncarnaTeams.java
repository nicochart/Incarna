package fr.factionbedrock.incarna.registry;

import fr.factionbedrock.incarna.choice.IncarnaSpecie;
import fr.factionbedrock.incarna.choice.IncarnaTeam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class IncarnaTeams
{
    public static List<IncarnaSpecie> DEFAULT_SPECIES = new ArrayList<>(Arrays.asList(IncarnaSpecies.DEFAULT));
    public static List<IncarnaSpecie> OVERWORLD_SPECIES = new ArrayList<>(Arrays.asList(IncarnaSpecies.ELF, IncarnaSpecies.DRAGON_BORN, IncarnaSpecies.TIDE_BORN, IncarnaSpecies.FALLEN_ANGEL, IncarnaSpecies.AERIAN, IncarnaSpecies.END_HYBRID));
    public static List<IncarnaSpecie> NETHER_SPECIES = new ArrayList<>(Arrays.asList(IncarnaSpecies.FALLEN_ANGEL, IncarnaSpecies.NETHERIAN, IncarnaSpecies.ORC));
    public static List<IncarnaSpecie> END_SPECIES = new ArrayList<>(Arrays.asList(IncarnaSpecies.END_HYBRID, IncarnaSpecies.ENDERIAN, IncarnaSpecies.VOID_BORN, IncarnaSpecies.SHADOW_BORN));
    public static List<IncarnaSpecie> AERIAL_HELL_SPECIES = new ArrayList<>(Arrays.asList(IncarnaSpecies.SHADOW_BORN, IncarnaSpecies.AERIAN, IncarnaSpecies.LUNAR_ELF, IncarnaSpecies.VOLUCITE_SERVANT));

    public static int MAX_INDEX = 0;
    public static LinkedHashMap<Integer, IncarnaTeam> TEAM_INDEXES = new LinkedHashMap<Integer, IncarnaTeam>();
    public static LinkedHashMap<String, IncarnaTeam> TEAM_NAMES = new LinkedHashMap<String, IncarnaTeam>();
    public static IncarnaTeam DEFAULT = new  IncarnaTeam(0, "default", DEFAULT_SPECIES);
    public static IncarnaTeam OVERWORLD = new  IncarnaTeam(1, "overworld", OVERWORLD_SPECIES);
    public static IncarnaTeam NETHER = new  IncarnaTeam(2, "nether", NETHER_SPECIES);
    public static IncarnaTeam END = new  IncarnaTeam(3, "end", END_SPECIES);
    public static IncarnaTeam AERIAL_HELL = new  IncarnaTeam(4, "aerial_hell", AERIAL_HELL_SPECIES);
}
