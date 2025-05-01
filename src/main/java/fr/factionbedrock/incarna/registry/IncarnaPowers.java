package fr.factionbedrock.incarna.registry;

import fr.factionbedrock.incarna.power.AttributeModifierPower;
import fr.factionbedrock.incarna.power.ConstantStatusEffectPower;
import fr.factionbedrock.incarna.power.IncarnaPower;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffects;

public class IncarnaPowers
{
    public static IncarnaPower CONSTANT_FIRE_RESISTANCE_EFFECT = new ConstantStatusEffectPower(StatusEffects.FIRE_RESISTANCE);
    public static IncarnaPower CONSTANT_SLOW_FALLING_EFFECT = new ConstantStatusEffectPower(StatusEffects.SLOW_FALLING);
    public static IncarnaPower CONSTANT_SPEED_EFFECT = new ConstantStatusEffectPower(StatusEffects.SPEED);
    public static IncarnaPower CONSTANT_NIGHT_VISION_EFFECT = new ConstantStatusEffectPower(StatusEffects.NIGHT_VISION, 240);
    public static IncarnaPower CONSTANT_SPEED_MODIFIER = new AttributeModifierPower(EntityAttributes.GENERIC_MOVEMENT_SPEED, AttributeModifierPower.SPEED_MODIFIER, 0.02, (modifierInfo) -> {return modifierInfo.level() * modifierInfo.baseModifierValue();}, EntityAttributeModifier.Operation.ADD_VALUE);
    public static IncarnaPower CONSTANT_JUMP_STRENGTH_MODIFIER = new AttributeModifierPower(EntityAttributes.GENERIC_JUMP_STRENGTH, AttributeModifierPower.JUMP_STRENGTH_MODIFIER, 0.1, (modifierInfo) -> {return modifierInfo.level() * modifierInfo.baseModifierValue();}, EntityAttributeModifier.Operation.ADD_VALUE);
}
