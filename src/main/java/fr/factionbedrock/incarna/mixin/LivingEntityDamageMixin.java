package fr.factionbedrock.incarna.mixin;

import fr.factionbedrock.incarna.power.CancelDamageSufferedPower;
import fr.factionbedrock.incarna.power.DamageSufferedModifierPower;
import fr.factionbedrock.incarna.power.IncarnaPower;
import fr.factionbedrock.incarna.util.PlayerHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityDamageMixin
{
    @ModifyArg(method = "damage", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;applyDamage(Lnet/minecraft/entity/damage/DamageSource;F)V"), index = 1)
    private float modifyEntityDamage(DamageSource source, float amount)
    {
        LivingEntity entity = (LivingEntity)(Object)this;

        if (entity instanceof PlayerEntity player)
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

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void onDamage(DamageSource damageSource, float amount, CallbackInfoReturnable<Boolean> cir)
    {
        LivingEntity entity = (LivingEntity)(Object)this;

        if (entity instanceof PlayerEntity player)
        {
            for (IncarnaPower power : PlayerHelper.getAllPowersFrom(player))
            {
                if (power instanceof CancelDamageSufferedPower cancelDamagePower && cancelDamagePower.shouldCancel(new CancelDamageSufferedPower.Info(damageSource, amount)))
                {
                    cir.setReturnValue(false);
                }
            }
        }
    }
}
