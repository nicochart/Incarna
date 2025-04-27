package fr.factionbedrock.incarna.choice;

import fr.factionbedrock.incarna.registry.IncarnaTeams;

public class IncarnaTeam
{
    private final int id;
    private final String name;

    public IncarnaTeam(int index, String name)
    {
        if (IncarnaTeams.TEAM_INDEXES.containsKey(index)) {throw new IllegalArgumentException("Incarna Teams Error : team index "+index+" is used in more than one team.");}
        if (IncarnaTeams.TEAM_NAMES.containsKey(name)) {throw new IllegalArgumentException("Incarna Teams Error : team name "+name+" is used in more than one team.");}
        this.id = index;
        this.name = name;
        IncarnaTeams.TEAM_INDEXES.put(this.id, this);
        IncarnaTeams.TEAM_NAMES.put(this.name, this);
        if (IncarnaTeams.MAX_INDEX < index) {IncarnaTeams.MAX_INDEX = index;}
    }

    public int id() {return id;}
    public String name() {return name;}
}