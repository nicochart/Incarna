package fr.factionbedrock.incarna.choice;

import fr.factionbedrock.incarna.power.IncarnaPower;
import fr.factionbedrock.incarna.registry.IncarnaTeams;

import java.util.LinkedHashMap;
import java.util.List;

public class IncarnaTeam extends IncarnaChoice
{
    private final List<IncarnaSpecie> available_species;

    @Override protected LinkedHashMap<Integer, IncarnaTeam> getIndexesMap() {return IncarnaTeams.TEAM_INDEXES;}
    @Override protected LinkedHashMap<String, IncarnaTeam> getNamesMap() {return IncarnaTeams.TEAM_NAMES;}

    public IncarnaTeam(int index, String name, List<IncarnaSpecie> available_species, IncarnaPower ... powers)
    {
        super(index, name, powers);
        this.available_species = available_species;
    }

    public IncarnaTeam(int index, String name, List<IncarnaSpecie> available_species)
    {
        super(index, name);
        this.available_species = available_species;
    }

    @Override protected void addSelfToMaps()
    {
        this.getIndexesMap().put(this.id(), this);
        this.getNamesMap().put(this.name(), this);
    }

    @Override protected void doOtherUpdates(int index, String name)
    {
        if (IncarnaTeams.MAX_INDEX < index) {IncarnaTeams.MAX_INDEX = index;}
    }

    @Override protected String getIndexError(int index)
    {
        return "Incarna Teams Error : team index "+index+" is used in more than one team.";
    }

    @Override protected String getNameError(String name)
    {
        return "Incarna Teams Error : team name "+name+" is used in more than one team.";
    }
}