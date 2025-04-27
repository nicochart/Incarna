package fr.factionbedrock.incarna.registry;

import fr.factionbedrock.incarna.choice.IncarnaTeam;

import java.util.LinkedHashMap;

public class IncarnaTeams
{
    public static int MAX_INDEX = 0;
    public static LinkedHashMap<Integer, IncarnaTeam> TEAM_INDEXES = new LinkedHashMap<Integer, IncarnaTeam>();
    public static LinkedHashMap<String, IncarnaTeam> TEAM_NAMES = new LinkedHashMap<String, IncarnaTeam>();
    public static  IncarnaTeam DEFAULT = new  IncarnaTeam(0, "default");
    public static  IncarnaTeam OVERWORLD = new  IncarnaTeam(1, "overworld");
    public static  IncarnaTeam NETHER = new  IncarnaTeam(2, "nether");
    public static  IncarnaTeam END = new  IncarnaTeam(3, "end");
    public static  IncarnaTeam AERIAL_HELL = new  IncarnaTeam(4, "aerial_hell");
}
