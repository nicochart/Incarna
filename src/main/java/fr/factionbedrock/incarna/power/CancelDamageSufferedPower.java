package fr.factionbedrock.incarna.power;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;

import java.util.function.Function;

public class CancelDamageSufferedPower extends IncarnaPower
{
    private final Function<CancelDamageSufferedPower.Info, Boolean> shouldCancel;

    public CancelDamageSufferedPower(Function<CancelDamageSufferedPower.Info, Boolean> shouldCancel)
    {
        super();
        this.shouldCancel = shouldCancel;
    }

    @Override public void onRemovedFromPlayer(PlayerEntity player) {}

    public boolean shouldCancel(CancelDamageSufferedPower.Info info) {return shouldCancel.apply(info);}

    public record Info(DamageSource damageSource, float amount) {}
}
