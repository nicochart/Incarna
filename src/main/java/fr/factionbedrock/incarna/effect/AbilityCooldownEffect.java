package fr.factionbedrock.incarna.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class AbilityCooldownEffect extends StatusEffect
{
    public AbilityCooldownEffect(StatusEffectCategory category, int liquidColor) {super(category, liquidColor);}
    
    @Override public boolean canApplyUpdateEffect(int duration, int amplifier) {return true;}

    @Override public boolean isInstant() {return false;}
}