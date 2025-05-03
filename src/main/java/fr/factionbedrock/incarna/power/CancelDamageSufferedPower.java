package fr.factionbedrock.incarna.power;

import net.minecraft.entity.damage.DamageSource;

import java.util.function.Function;

public class CancelDamageSufferedPower extends IncarnaPower
{
    private final Function<DamageSource, Boolean> shouldCancel;

    //if damageType is null, the damage amount is modified regardless of the damagesource
    public CancelDamageSufferedPower(Function<DamageSource, Boolean> shouldCancel)
    {
        super();
        this.shouldCancel = shouldCancel;
    }

    public boolean shouldCancel(DamageSource source) {return shouldCancel.apply(source);}
}
