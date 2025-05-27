package fr.factionbedrock.incarna.registry;

import fr.factionbedrock.incarna.power.*;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.PotionEntity;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.item.Items;
import net.minecraft.world.dimension.DimensionTypes;

public class IncarnaPowers
{
    public static IncarnaPower VEGAN = new FoodRestrictionPower((stack) -> {return !stack.isIn(IncarnaTags.Items.MEAT);});
    public static IncarnaPower CARNIVORE = new FoodRestrictionPower((stack) -> {return stack.isIn(IncarnaTags.Items.MEAT);});
    public static IncarnaPower PREVENT_BOW_USE = new ItemUseRestrictionPower((stack) -> {return !stack.isOf(Items.BOW);});
    public static IncarnaPower CONSTANT_FIRE_RESISTANCE_EFFECT = new ConstantStatusEffectPower(StatusEffects.FIRE_RESISTANCE);
    public static IncarnaPower CONSTANT_SLOW_FALLING_EFFECT = new ConstantStatusEffectPower(StatusEffects.SLOW_FALLING);
    public static IncarnaPower CONSTANT_SPEED_EFFECT = new ConstantStatusEffectPower(StatusEffects.SPEED, IncarnaPowers::levelScaler_0_2);
    public static IncarnaPower CONSTANT_NIGHT_VISION_EFFECT = new ConstantStatusEffectPower(StatusEffects.NIGHT_VISION);
    public static IncarnaPower CONSTANT_SPEED_MODIFIER = new AttributeModifierPower(EntityAttributes.GENERIC_MOVEMENT_SPEED, AttributeModifierPower.SPEED_MODIFIER, 0.02, (modifierInfo) -> {return 0.5F * levelScaler_1_3(modifierInfo.level()) * modifierInfo.baseModifierValue();}, EntityAttributeModifier.Operation.ADD_VALUE);
    public static IncarnaPower CONSTANT_JUMP_STRENGTH_MODIFIER = new AttributeModifierPower(EntityAttributes.GENERIC_JUMP_STRENGTH, AttributeModifierPower.JUMP_STRENGTH_MODIFIER, 0.1, (modifierInfo) -> {return 0.5F * levelScaler_1_3(modifierInfo.level()) * modifierInfo.baseModifierValue();}, EntityAttributeModifier.Operation.ADD_VALUE);
    public static IncarnaPower CONSTANT_ARMOR_MODIFIER = new AttributeModifierPower(EntityAttributes.GENERIC_ARMOR, AttributeModifierPower.ARMOR_MODIFIER, 0.5, (modifierInfo) -> {return 0.5F * levelScaler_1_3(modifierInfo.level()) * modifierInfo.baseModifierValue();}, EntityAttributeModifier.Operation.ADD_VALUE);
    public static IncarnaPower CONSTANT_SCALE_MODIFIER = new AttributeModifierPower(EntityAttributes.GENERIC_SCALE, AttributeModifierPower.SCALE_MODIFIER, 0.5, (modifierInfo) -> {return modifierInfo.baseModifierValue();}, EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE);
    public static IncarnaPower INCREASED_FALL_DAMAGE = new DamageSufferedModifierPower(DamageTypes.FALL, 3.0F, (modifierInfo) -> {return 0.5F * levelScaler_1_3(modifierInfo.level()) * (float)modifierInfo.baseModifierValue();}, DamageSufferedModifierPower.Operation.MULTIPLY);
    public static IncarnaPower CANCEL_PROJECTILE_DAMAGE = new CancelDamageSufferedPower((damageInfo -> {return damageInfo.damageSource().getSource() instanceof ProjectileEntity;}));
    public static IncarnaPower CANCEL_LOW_FALL_DAMAGE = new CancelDamageSufferedPower((damageInfo -> {return damageInfo.damageSource().isOf(DamageTypes.FALL) && damageInfo.amount() < 6.0F;}));
    public static IncarnaPower HEAL_IN_WATER = new HealOrDamageOverTimePower(1.0F, (modifierInfo) -> {return 0.5F * levelScaler_1_3(modifierInfo.level()) * (float)modifierInfo.baseModifierValue();}, (player) -> {return player.isTouchingWater();});
    public static IncarnaPower DAMAGE_IN_WATER = new HealOrDamageOverTimePower(-1.0F, (modifierInfo) -> {return 0.5F * levelScaler_1_3(modifierInfo.level()) * (float)modifierInfo.baseModifierValue();}, (player) -> {return player.isTouchingWater();});
    public static IncarnaPower DAMAGE_IN_NETHER = new HealOrDamageOverTimePower(-1.0F, (modifierInfo) -> {return 0.5F * levelScaler_1_3(modifierInfo.level()) * (float)modifierInfo.baseModifierValue();}, (player) -> {return player.getWorld().getDimensionEntry().matchesKey(DimensionTypes.THE_NETHER);});
    public static IncarnaPower FIRE_RESISTANCE_ABILITY = new StatusEffectAbilityPower((level) -> new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 300, 0));
    public static IncarnaPower SLOW_FALLING_ABILITY = new StatusEffectAbilityPower((level) -> new StatusEffectInstance(StatusEffects.SLOW_FALLING, 300, levelScaler_0_2(level)));
    public static IncarnaPower SPEED_ABILITY = new StatusEffectAbilityPower((level) -> new StatusEffectInstance(StatusEffects.SPEED, 300, levelScaler_0_2(level)));
    public static IncarnaPower THROW_ENDERPEARL_ABILITY = new ThrowEnderPearlAbilityPower();
    public static IncarnaPower TELEPORT_ON_PROJECTILE_DAMAGE = new TeleportOnProjectileHitOrDeflectPower(projectile -> {return projectile instanceof PersistentProjectileEntity || projectile instanceof ThrownEntity && !(projectile instanceof PotionEntity);});

    public static int levelScaler_1_3(int level)
    {
        return levelScaler_0_2(level) + 1;
    }

    public static int levelScaler_0_2(int level)
    {
        if (level < 7) {return 0;}
        else if (level < 9) {return 1;}
        else {return 2;}
    }
}
