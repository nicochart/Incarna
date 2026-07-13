package fr.factionbedrock.incarna.effect;

import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class AbilityCooldownEffect extends MobEffect
{
    public AbilityCooldownEffect(MobEffectCategory category, int liquidColor) {super(category, liquidColor);}
    
    @Override public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {return true;}

    @Override public boolean isInstantenous() {return false;}
}