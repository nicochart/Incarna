package fr.factionbedrock.incarna.registry;

import fr.factionbedrock.incarna.choice.IncarnaSpecie;

import java.util.LinkedHashMap;

public class IncarnaSpecies
{
    public static int MAX_INDEX = 0;
    public static LinkedHashMap<Integer, IncarnaSpecie> SPECIES_INDEXES = new LinkedHashMap<Integer, IncarnaSpecie>();
    public static LinkedHashMap<String, IncarnaSpecie> SPECIES_NAMES = new LinkedHashMap<String, IncarnaSpecie>();
    public static IncarnaSpecie DEFAULT = new  IncarnaSpecie(0, "default");
    public static IncarnaSpecie ELF = new  IncarnaSpecie(1, "elf", IncarnaPowers.CONSTANT_SPEED_MODIFIER, IncarnaPowers.CONSTANT_SPEED_EFFECT, IncarnaPowers.CONSTANT_JUMP_STRENGTH_MODIFIER, IncarnaPowers.VEGAN, IncarnaPowers.SPEED_ABILITY).hasAbility(true);
    public static IncarnaSpecie DRAGON_BORN = new  IncarnaSpecie(2, "dragon_born", IncarnaPowers.CARNIVORE, IncarnaPowers.CONSTANT_ARMOR_MODIFIER);
    public static IncarnaSpecie TIDE_BORN = new  IncarnaSpecie(3, "tide_born", IncarnaPowers.HEAL_IN_WATER, IncarnaPowers.CONSTANT_SPEED_MODIFIER);
    public static IncarnaSpecie FALLEN_ANGEL = new  IncarnaSpecie(4, "fallen_angel", IncarnaPowers.CONSTANT_SPEED_MODIFIER, IncarnaPowers.FIRE_RESISTANCE_ABILITY).hasAbility(true);
    public static IncarnaSpecie NETHERIAN = new  IncarnaSpecie(5, "netherian", IncarnaPowers.CONSTANT_FIRE_RESISTANCE_EFFECT, IncarnaPowers.DAMAGE_IN_WATER, IncarnaPowers.CARNIVORE);
    public static IncarnaSpecie ORC = new  IncarnaSpecie(6, "orc", IncarnaPowers.CONSTANT_FIRE_RESISTANCE_EFFECT, IncarnaPowers.CARNIVORE, IncarnaPowers.CONSTANT_ARMOR_MODIFIER, IncarnaPowers.PREVENT_BOW_USE);
    public static IncarnaSpecie END_HYBRID = new  IncarnaSpecie(7, "end_hybrid", IncarnaPowers.CONSTANT_SPEED_MODIFIER, IncarnaPowers.CANCEL_PROJECTILE_DAMAGE);
    public static IncarnaSpecie ENDERIAN = new  IncarnaSpecie(8, "enderian", IncarnaPowers.CONSTANT_NIGHT_VISION_EFFECT, IncarnaPowers.CONSTANT_SCALE_MODIFIER, IncarnaPowers.CONSTANT_SPEED_MODIFIER, IncarnaPowers.DAMAGE_IN_WATER, IncarnaPowers.CANCEL_PROJECTILE_DAMAGE);
    public static IncarnaSpecie VOID_BORN = new  IncarnaSpecie(9, "void_born", IncarnaPowers.CONSTANT_NIGHT_VISION_EFFECT, IncarnaPowers.CONSTANT_SPEED_MODIFIER, IncarnaPowers.INCREASED_FALL_DAMAGE, IncarnaPowers.DAMAGE_IN_WATER, IncarnaPowers.CANCEL_PROJECTILE_DAMAGE, IncarnaPowers.CONSTANT_ARMOR_MODIFIER);
    public static IncarnaSpecie SHADOW_BORN = new  IncarnaSpecie(10, "shadow_born", IncarnaPowers.CONSTANT_NIGHT_VISION_EFFECT, IncarnaPowers.CANCEL_PROJECTILE_DAMAGE);
    public static IncarnaSpecie AERIAN = new  IncarnaSpecie(11, "aerian", IncarnaPowers.SLOW_FALLING_ABILITY, IncarnaPowers.CONSTANT_JUMP_STRENGTH_MODIFIER).hasAbility(true);
    public static IncarnaSpecie LUNAR_ELF = new  IncarnaSpecie(12, "lunar_elf", IncarnaPowers.CONSTANT_SPEED_MODIFIER, IncarnaPowers.CONSTANT_JUMP_STRENGTH_MODIFIER, IncarnaPowers.VEGAN, IncarnaPowers.INCREASED_FALL_DAMAGE);
    public static IncarnaSpecie VOLUCITE_SERVANT = new  IncarnaSpecie(13, "volucite_servant", IncarnaPowers.CONSTANT_SLOW_FALLING_EFFECT, IncarnaPowers.CONSTANT_JUMP_STRENGTH_MODIFIER, IncarnaPowers.CONSTANT_ARMOR_MODIFIER, IncarnaPowers.INCREASED_FALL_DAMAGE);
}
