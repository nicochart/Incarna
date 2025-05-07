package fr.factionbedrock.incarna.power;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

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

    @Override public void onRemovedFromPlayer(PlayerEntity player) {}

    public boolean shouldCancel(DamageSource source) {return shouldCancel.apply(source);}
}
