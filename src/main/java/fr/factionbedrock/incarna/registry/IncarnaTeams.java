package fr.factionbedrock.incarna.registry;

import java.util.LinkedHashMap;

public class IncarnaTeams
{
    public static int MAX_INDEX = 0;
    public static LinkedHashMap<Integer, String> TEAMS = new LinkedHashMap<Integer, String>();
    public static Team DEFAULT = new Team(0, "default");
    public static Team OVERWORLD = new Team(1, "overworld");
    public static Team NETHER = new Team(2, "nether");
    public static Team END = new Team(3, "end");
    public static Team AERIAL_HELL = new Team(4, "aerial_hell");

    public static class Team
    {
        private final int id;
        private final String name;

        private Team(int index, String name)
        {
            if (TEAMS.containsKey(index)) {throw new IllegalArgumentException("Incarna Teams Error : team index "+index+" is used in more than one team.");}
            if (TEAMS.containsValue(name)) {throw new IllegalArgumentException("Incarna Teams Error : team name "+name+" is used in more than one team.");}
            this.id = index;
            this.name = name;
            TEAMS.put(id, name);
            if (IncarnaTeams.MAX_INDEX < index) {IncarnaTeams.MAX_INDEX = index;}
        }

        public int id() {return id;}
        public String value() {return name;}
    }
}
