package fr.factionbedrock.incarna.mixin;

import fr.factionbedrock.incarna.power.ActionOnProjectileHitPower;
import fr.factionbedrock.incarna.power.IncarnaPower;
import fr.factionbedrock.incarna.util.PlayerHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ProjectileDeflection;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ProjectileEntity.class)
public class ProjectileEntityHitOrDeflectMixin
{
    @Inject(method = "hitOrDeflect", at = @At("HEAD"), cancellable = true)
    private void onHitOrDeflect(HitResult hitResult, CallbackInfoReturnable<ProjectileDeflection> cir)
    {
        ProjectileEntity projectile = (ProjectileEntity) (Object) this;

        if (!(hitResult instanceof EntityHitResult entityHitResult)) {return;}

        Entity target = entityHitResult.getEntity();
        if (target instanceof ServerPlayerEntity player)
        {
            for (IncarnaPower power : PlayerHelper.getAllPowersFrom(player))
            {
                if (power instanceof ActionOnProjectileHitPower actionPower)
                {
                    actionPower.tryTick(player, projectile);
                    if (actionPower.shouldCancelHitOrDeflect(projectile))
                    {
                        cir.setReturnValue(ProjectileDeflection.SIMPLE);
                        cir.cancel();
                    }
                }
            }
        }
    }
}
