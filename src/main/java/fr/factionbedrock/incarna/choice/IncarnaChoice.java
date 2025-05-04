package fr.factionbedrock.incarna.choice;

import fr.factionbedrock.incarna.power.IncarnaPower;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public abstract class IncarnaChoice
{
    private final int id;
    private final String name;
    private final List<IncarnaPower> powers = new ArrayList<>();

    protected abstract LinkedHashMap<Integer, ? extends IncarnaChoice> getIndexesMap();
    protected abstract LinkedHashMap<String, ? extends IncarnaChoice> getNamesMap();


    public IncarnaChoice(int index, String name, IncarnaPower ... powers)
    {
        this(index, name);
        this.powers.addAll(Arrays.asList(powers));
    }

    public IncarnaChoice(int index, String name)
    {
        if (this.getIndexesMap().containsKey(index)) {throw new IllegalArgumentException(this.getIndexError(index));}
        if (this.getNamesMap().containsKey(name)) {throw new IllegalArgumentException(this.getNameError(name));}
        this.id = index;
        this.name = name;
        this.addSelfToMaps();
        this.doOtherUpdates(index, name);
    }

    protected abstract void addSelfToMaps();
    protected abstract void doOtherUpdates(int index, String name);
    protected abstract String getIndexError(int index);
    protected abstract String getNameError(String name);

    public int id() {return id;}
    public String name() {return name;}
    public List<IncarnaPower> powers() {return powers;}
}