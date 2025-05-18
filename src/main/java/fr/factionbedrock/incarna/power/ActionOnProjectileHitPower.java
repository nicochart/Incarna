package fr.factionbedrock.incarna.power;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;

import java.util.function.Function;

public abstract class ActionOnProjectileHitPower extends IncarnaPower
{
    private final Function<ProjectileEntity, Boolean> shouldAction;
    private final Function<ProjectileEntity, Boolean> shouldCancelHitOrDeflect;

    public ActionOnProjectileHitPower(Function<ProjectileEntity, Boolean> shouldActionAndCancelHitOrDeflect)
    {
        this(shouldActionAndCancelHitOrDeflect, shouldActionAndCancelHitOrDeflect);
    }

    public ActionOnProjectileHitPower(Function<ProjectileEntity, Boolean> shouldAction, Function<ProjectileEntity, Boolean> shouldCancelHitOrDeflect)
    {
        super();
        this.shouldAction = shouldAction;
        this.shouldCancelHitOrDeflect = shouldCancelHitOrDeflect;
    }

    @Override public void onRemovedFromPlayer(PlayerEntity player) {}

    public boolean shouldCancelHitOrDeflect(ProjectileEntity projectile) {return shouldCancelHitOrDeflect.apply(projectile);}

    public void tryTick(PlayerEntity player, ProjectileEntity projectile)
    {
        if (shouldAction.apply(projectile)) {this.tick(player);}
    }

    protected abstract void tick(PlayerEntity player);
}
