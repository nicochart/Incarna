package fr.factionbedrock.incarna.power;

import fr.factionbedrock.incarna.util.ModifierInfo;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.RegistryKey;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;

public class DamageSufferedModifierPower extends IncarnaPower
{
    @Nullable private final RegistryKey<DamageType> damageType;
    private final float baseValue;
    private final Function<ModifierInfo, Float> modifierValue;
    private final Operation modifierOperation;

    //if damageType is null, the damage amount is modified regardless of the damagesource
    public DamageSufferedModifierPower(@Nullable RegistryKey<DamageType> damageType, float baseValue, Function<ModifierInfo, Float> modifierValue, Operation modifierOperation)
    {
        super();
        this.damageType = damageType;
        this.baseValue = baseValue;
        this.modifierValue = modifierValue;
        this.modifierOperation = modifierOperation;
    }

    @Override public void onRemovedFromPlayer(PlayerEntity player) {}

    public float updateDamageModifier(DamageSource source, float previousAmount, int powerLevel)
    {
        if (this.matches(source))
        {
            float value = modifierValue.apply(new ModifierInfo(baseValue, powerLevel));
            return modifierOperation == Operation.ADD ? previousAmount + value : previousAmount * value;
        }
        return previousAmount;
    }

    public boolean matches(DamageSource source)
    {
        return this.damageType == null || source.isOf(this.damageType);
    }

    public enum Operation{ADD, MULTIPLY}
}
