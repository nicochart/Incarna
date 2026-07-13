package fr.factionbedrock.incarna.power;

import fr.factionbedrock.incarna.util.ModifierInfo;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.player.Player;

public class DamageSufferedModifierPower extends IncarnaPower
{
    @Nullable private final ResourceKey<DamageType> damageType;
    private final float baseValue;
    private final Function<ModifierInfo, Float> modifierValue;
    private final Operation modifierOperation;

    //if damageType is null, the damage amount is modified regardless of the damagesource
    public DamageSufferedModifierPower(@Nullable ResourceKey<DamageType> damageType, float baseValue, Function<ModifierInfo, Float> modifierValue, Operation modifierOperation)
    {
        super();
        this.damageType = damageType;
        this.baseValue = baseValue;
        this.modifierValue = modifierValue;
        this.modifierOperation = modifierOperation;
    }

    @Override public void onRemovedFromPlayer(Player player) {}

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
        return this.damageType == null || source.is(this.damageType);
    }

    public enum Operation{ADD, MULTIPLY}
}
