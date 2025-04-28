package fr.factionbedrock.incarna.registry;

import fr.factionbedrock.incarna.power.ConstantStatusEffectPower;
import fr.factionbedrock.incarna.power.IncarnaPower;
import net.minecraft.entity.effect.StatusEffects;

public class IncarnaPowers
{
    public static IncarnaPower CONSTANT_FIRE_RESISTANCE = new ConstantStatusEffectPower(StatusEffects.FIRE_RESISTANCE);
    public static IncarnaPower CONSTANT_SLOW_FALLING = new ConstantStatusEffectPower(StatusEffects.SLOW_FALLING);
    public static IncarnaPower CONSTANT_SPEED = new ConstantStatusEffectPower(StatusEffects.SPEED);
    public static IncarnaPower CONSTANT_NIGHT_VISION = new ConstantStatusEffectPower(StatusEffects.NIGHT_VISION, 240);
}
