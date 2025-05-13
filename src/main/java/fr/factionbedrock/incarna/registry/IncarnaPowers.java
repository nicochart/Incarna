package fr.factionbedrock.incarna.registry;

import fr.factionbedrock.incarna.power.*;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.Items;

public class IncarnaPowers
{
    public static IncarnaPower VEGAN = new FoodRestrictionPower((stack) -> {return !stack.isIn(IncarnaTags.Items.MEAT);});
    public static IncarnaPower CARNIVORE = new FoodRestrictionPower((stack) -> {return stack.isIn(IncarnaTags.Items.MEAT);});
    public static IncarnaPower PREVENT_BOW_USE = new ItemUseRestrictionPower((stack) -> {return !stack.isOf(Items.BOW);});
    public static IncarnaPower CONSTANT_FIRE_RESISTANCE_EFFECT = new ConstantStatusEffectPower(StatusEffects.FIRE_RESISTANCE);
    public static IncarnaPower CONSTANT_SLOW_FALLING_EFFECT = new ConstantStatusEffectPower(StatusEffects.SLOW_FALLING);
    public static IncarnaPower CONSTANT_SPEED_EFFECT = new ConstantStatusEffectPower(StatusEffects.SPEED, (level) -> Math.max(0, level - 1));
    public static IncarnaPower CONSTANT_NIGHT_VISION_EFFECT = new ConstantStatusEffectPower(StatusEffects.NIGHT_VISION);
    public static IncarnaPower CONSTANT_SPEED_MODIFIER = new AttributeModifierPower(EntityAttributes.GENERIC_MOVEMENT_SPEED, AttributeModifierPower.SPEED_MODIFIER, 0.02, (modifierInfo) -> {return modifierInfo.level() * modifierInfo.baseModifierValue();}, EntityAttributeModifier.Operation.ADD_VALUE);
    public static IncarnaPower CONSTANT_JUMP_STRENGTH_MODIFIER = new AttributeModifierPower(EntityAttributes.GENERIC_JUMP_STRENGTH, AttributeModifierPower.JUMP_STRENGTH_MODIFIER, 0.1, (modifierInfo) -> {return modifierInfo.level() * modifierInfo.baseModifierValue();}, EntityAttributeModifier.Operation.ADD_VALUE);
    public static IncarnaPower CONSTANT_ARMOR_MODIFIER = new AttributeModifierPower(EntityAttributes.GENERIC_ARMOR, AttributeModifierPower.ARMOR_MODIFIER, 0.5, (modifierInfo) -> {return modifierInfo.level() * modifierInfo.baseModifierValue();}, EntityAttributeModifier.Operation.ADD_VALUE);
    public static IncarnaPower CONSTANT_SCALE_MODIFIER = new AttributeModifierPower(EntityAttributes.GENERIC_SCALE, AttributeModifierPower.SCALE_MODIFIER, 0.5, (modifierInfo) -> {return modifierInfo.baseModifierValue();}, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    public static IncarnaPower INCREASED_FALL_DAMAGE = new DamageSufferedModifierPower(DamageTypes.FALL, 3.0F, (modifierInfo) -> {return modifierInfo.level() * (float)modifierInfo.baseModifierValue();}, DamageSufferedModifierPower.Operation.MULTIPLY);
    public static IncarnaPower CANCEL_PROJECTILE_DAMAGE = new CancelDamageSufferedPower((damageSource -> {return damageSource.getSource() instanceof ProjectileEntity;}));
    public static IncarnaPower HEAL_IN_WATER = new HealOrDamageOverTimePower(1.0F, (modifierInfo) -> {return modifierInfo.level() * (float)modifierInfo.baseModifierValue();}, (player) -> {return player.isTouchingWater();});
    public static IncarnaPower DAMAGE_IN_WATER = new HealOrDamageOverTimePower(-1.0F, (modifierInfo) -> {return modifierInfo.level() * (float)modifierInfo.baseModifierValue();}, (player) -> {return player.isTouchingWater();});
    public static IncarnaPower FIRE_RESISTANCE_ABILITY = new StatusEffectAbilityPower((level) -> new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 300, 0));
    public static IncarnaPower SLOW_FALLING_ABILITY = new StatusEffectAbilityPower((level) -> new StatusEffectInstance(StatusEffects.SLOW_FALLING, 300, Math.max(0, level - 1)));
    public static IncarnaPower SPEED_ABILITY = new StatusEffectAbilityPower((level) -> new StatusEffectInstance(StatusEffects.SPEED, 300, Math.max(0, level - 1)));
}
