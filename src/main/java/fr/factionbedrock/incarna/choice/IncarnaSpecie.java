package fr.factionbedrock.incarna.choice;

import fr.factionbedrock.incarna.power.IncarnaPower;
import fr.factionbedrock.incarna.registry.IncarnaSpecies;
import fr.factionbedrock.incarna.registry.IncarnaTeams;

import java.util.LinkedHashMap;
import java.util.List;

public class IncarnaSpecie extends IncarnaChoice
{
    private boolean hasAbility;
    @Override protected LinkedHashMap<Integer, IncarnaSpecie> getIndexesMap() {return IncarnaSpecies.SPECIES_INDEXES;}
    @Override protected LinkedHashMap<String, IncarnaSpecie> getNamesMap() {return IncarnaSpecies.SPECIES_NAMES;}

    public IncarnaSpecie(int index, String name, IncarnaPower ... powers) {super(index, name, powers); this.hasAbility = false;}

    public IncarnaSpecie(int index, String name) {super(index, name); this.hasAbility = false;}

    @Override protected void addSelfToMaps()
    {
        this.getIndexesMap().put(this.id(), this);
        this.getNamesMap().put(this.name(), this);
    }

    @Override protected void doOtherUpdates(int index, String name)
    {
        if (IncarnaSpecies.MAX_INDEX < index) {IncarnaSpecies.MAX_INDEX = index;}
    }

    @Override protected String getIndexError(int index)
    {
        return "Incarna Species Error : species index "+index+" is used in more than one species.";
    }

    @Override protected String getNameError(String name)
    {
        return "Incarna Species Error : species name "+name+" is used in more than one species.";
    }

    public IncarnaSpecie hasAbility(boolean hasAbility) {this.hasAbility = hasAbility; return this;}
    public boolean hasAbility() {return this.hasAbility;}
}