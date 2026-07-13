package fr.factionbedrock.incarna.power;

import java.util.function.Function;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;

public class CancelDamageSufferedPower extends IncarnaPower
{
    private final Function<CancelDamageSufferedPower.Info, Boolean> shouldCancel;

    public CancelDamageSufferedPower(Function<CancelDamageSufferedPower.Info, Boolean> shouldCancel)
    {
        super();
        this.shouldCancel = shouldCancel;
    }

    @Override public void onRemovedFromPlayer(Player player) {}

    public boolean shouldCancel(CancelDamageSufferedPower.Info info) {return shouldCancel.apply(info);}

    public record Info(DamageSource damageSource, float amount) {}
}
