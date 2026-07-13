package fr.factionbedrock.incarna.mixin;

import fr.factionbedrock.incarna.power.ActionOnDamagePower;
import fr.factionbedrock.incarna.power.CancelDamageSufferedPower;
import fr.factionbedrock.incarna.power.DamageSufferedModifierPower;
import fr.factionbedrock.incarna.power.IncarnaPower;
import fr.factionbedrock.incarna.registry.ExperienceDeltaReasons;
import fr.factionbedrock.incarna.registry.IncarnaTags;
import fr.factionbedrock.incarna.util.PlayerHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityDamageMixin
{
    @ModifyArg(method = "hurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;actuallyHurt(Lnet/minecraft/world/damagesource/DamageSource;F)V"), index = 1)
    private float modifyEntityDamage(DamageSource source, float amount)
    {
        LivingEntity entity = (LivingEntity)(Object)this;

        if (entity instanceof Player player)
        {
            float modifiedAmount = amount;
            for (IncarnaPower power : PlayerHelper.getAllPowersFrom(player))
            {
                if (power instanceof DamageSufferedModifierPower damageModifierPower)
                {
                    modifiedAmount = damageModifierPower.updateDamageModifier(source, modifiedAmount, 1);
                }
            }
            return modifiedAmount;
        }
        return amount;
    }

    @Inject(method = "hurt", at = @At("HEAD"), cancellable = true)
    private void onDamage(DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        LivingEntity damagedEntity = (LivingEntity)(Object)this;
        Entity attackerEntity = damageSource.getEntity();

        if (damagedEntity instanceof Player damagedPlayer)
        {
            //experience delta
            if (attackerEntity instanceof Player attackerPlayer && PlayerHelper.arePlayersInSameTeam(damagedPlayer, attackerPlayer))
            {
                PlayerHelper.deltaPlayerIncarnaExperience(attackerPlayer, ExperienceDeltaReasons.FRIENDLY_FIRE);
            }

            //power actions
            for (IncarnaPower power : PlayerHelper.getAllPowersFrom(damagedPlayer))
            {
                if (power instanceof CancelDamageSufferedPower cancelDamagePower)
                {
                    CancelDamageSufferedPower.Info info = new CancelDamageSufferedPower.Info(damageSource, amount);
                    if (power instanceof ActionOnDamagePower actionOnDamagePower)
                    {
                        actionOnDamagePower.tryTick(damagedPlayer, info);
                    }
                    if (cancelDamagePower.shouldCancel(info)) {cir.setReturnValue(false);}
                }
            }
        }
    }
}
