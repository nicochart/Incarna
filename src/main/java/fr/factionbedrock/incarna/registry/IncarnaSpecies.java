package fr.factionbedrock.incarna.registry;

import fr.factionbedrock.incarna.choice.IncarnaSpecie;

import java.util.LinkedHashMap;

public class IncarnaSpecies
{
    public static int MAX_INDEX = 0;
    public static LinkedHashMap<Integer, IncarnaSpecie> SPECIES_INDEXES = new LinkedHashMap<Integer, IncarnaSpecie>();
    public static LinkedHashMap<String, IncarnaSpecie> SPECIES_NAMES = new LinkedHashMap<String, IncarnaSpecie>();
    public static IncarnaSpecie DEFAULT = new  IncarnaSpecie(0, "default");
    public static IncarnaSpecie ELF = new  IncarnaSpecie(1, "elf", IncarnaPowers.CONSTANT_SPEED_MODIFIER);
    public static IncarnaSpecie ENDERIAN = new  IncarnaSpecie(2, "enderian", IncarnaPowers.CONSTANT_NIGHT_VISION_EFFECT);
    public static IncarnaSpecie LUNAR_ELF = new  IncarnaSpecie(3, "lunar_elf", IncarnaPowers.CANCEL_PROJECTILE_DAMAGE);
    public static IncarnaSpecie NETHERIAN = new  IncarnaSpecie(4, "netherian", IncarnaPowers.CONSTANT_FIRE_RESISTANCE_EFFECT);
}
