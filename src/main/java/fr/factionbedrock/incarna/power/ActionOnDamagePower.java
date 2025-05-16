package fr.factionbedrock.incarna.power;

import net.minecraft.entity.player.PlayerEntity;

import java.util.function.Function;

public abstract class ActionOnDamagePower extends CancelDamageSufferedPower
{
    public Function<CancelDamageSufferedPower.Info, Boolean> shouldAction;

    public ActionOnDamagePower(Function<CancelDamageSufferedPower.Info, Boolean> shouldActionAndCancel) {this(shouldActionAndCancel, shouldActionAndCancel);}

    public ActionOnDamagePower(Function<CancelDamageSufferedPower.Info, Boolean> shouldAction, Function<CancelDamageSufferedPower.Info, Boolean> shouldCancel)
    {
        super(shouldCancel);
        this.shouldAction = shouldAction;
    }

    public void tryTick(PlayerEntity player, CancelDamageSufferedPower.Info info)
    {
        if (shouldAction.apply(info)) {this.tick(player);}
    }

    protected abstract void tick(PlayerEntity player);
}
