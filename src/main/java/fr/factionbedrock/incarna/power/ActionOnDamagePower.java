package fr.factionbedrock.incarna.power;

import java.util.function.Function;
import net.minecraft.world.entity.player.Player;

public abstract class ActionOnDamagePower extends CancelDamageSufferedPower
{
    public Function<CancelDamageSufferedPower.Info, Boolean> shouldAction;

    public ActionOnDamagePower(Function<CancelDamageSufferedPower.Info, Boolean> shouldActionAndCancel) {this(shouldActionAndCancel, shouldActionAndCancel);}

    public ActionOnDamagePower(Function<CancelDamageSufferedPower.Info, Boolean> shouldAction, Function<CancelDamageSufferedPower.Info, Boolean> shouldCancel)
    {
        super(shouldCancel);
        this.shouldAction = shouldAction;
    }

    public void tryTick(Player player, CancelDamageSufferedPower.Info info)
    {
        if (shouldAction.apply(info)) {this.tick(player);}
    }

    protected abstract void tick(Player player);
}
