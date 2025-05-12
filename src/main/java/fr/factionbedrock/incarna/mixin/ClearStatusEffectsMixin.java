package fr.factionbedrock.incarna.mixin;

import fr.factionbedrock.incarna.registry.IncarnaMobEffects;
import fr.factionbedrock.incarna.util.MilkAbilityCooldownEffectPreserver;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class ClearStatusEffectsMixin
{

    @Inject(method = "clearStatusEffects", at = @At("RETURN"))
    private void onClearStatusEffects(CallbackInfoReturnable<Boolean> cir)
    {
        LivingEntity livingEntity = (LivingEntity)(Object)this;

        if (!livingEntity.getWorld().isClient && livingEntity instanceof PlayerEntity player)
        {
            int duration = MilkAbilityCooldownEffectPreserver.getAndRemove(player.getUuid());
            if (duration > 0)
            {
                player.addStatusEffect(new StatusEffectInstance(IncarnaMobEffects.ABILITY_COOLDOWN, duration));
            }
        }
    }
}