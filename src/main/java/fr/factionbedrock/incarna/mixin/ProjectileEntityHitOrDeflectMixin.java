package fr.factionbedrock.incarna.mixin;

import fr.factionbedrock.incarna.power.ActionOnProjectileHitPower;
import fr.factionbedrock.incarna.power.IncarnaPower;
import fr.factionbedrock.incarna.util.PlayerHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Projectile.class)
public class ProjectileEntityHitOrDeflectMixin
{
    @Inject(method = "hitTargetOrDeflectSelf", at = @At("HEAD"), cancellable = true)
    private void onHitOrDeflect(HitResult hitResult, CallbackInfoReturnable<ProjectileDeflection> cir)
    {
        Projectile projectile = (Projectile) (Object) this;

        if (!(hitResult instanceof EntityHitResult entityHitResult)) {return;}

        Entity target = entityHitResult.getEntity();
        if (target instanceof ServerPlayer player)
        {
            for (IncarnaPower power : PlayerHelper.getAllPowersFrom(player))
            {
                if (power instanceof ActionOnProjectileHitPower actionPower)
                {
                    actionPower.tryTick(player, projectile);
                    if (actionPower.shouldCancelHitOrDeflect(projectile))
                    {
                        cir.setReturnValue(ProjectileDeflection.REVERSE);
                        cir.cancel();
                    }
                }
            }
        }
    }
}
