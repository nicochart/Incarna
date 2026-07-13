package fr.factionbedrock.incarna.power;

import java.util.function.Function;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;

public abstract class ActionOnProjectileHitPower extends IncarnaPower
{
    private final Function<Projectile, Boolean> shouldAction;
    private final Function<Projectile, Boolean> shouldCancelHitOrDeflect;

    public ActionOnProjectileHitPower(Function<Projectile, Boolean> shouldActionAndCancelHitOrDeflect)
    {
        this(shouldActionAndCancelHitOrDeflect, shouldActionAndCancelHitOrDeflect);
    }

    public ActionOnProjectileHitPower(Function<Projectile, Boolean> shouldAction, Function<Projectile, Boolean> shouldCancelHitOrDeflect)
    {
        super();
        this.shouldAction = shouldAction;
        this.shouldCancelHitOrDeflect = shouldCancelHitOrDeflect;
    }

    @Override public void onRemovedFromPlayer(Player player) {}

    public boolean shouldCancelHitOrDeflect(Projectile projectile) {return shouldCancelHitOrDeflect.apply(projectile);}

    public void tryTick(Player player, Projectile projectile)
    {
        if (shouldAction.apply(projectile)) {this.tick(player);}
    }

    protected abstract void tick(Player player);
}
