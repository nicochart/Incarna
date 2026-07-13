package fr.factionbedrock.incarna.mixin;

import fr.factionbedrock.incarna.registry.IncarnaMobEffects;
import fr.factionbedrock.incarna.util.MilkAbilityCooldownEffectPreserver;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class ClearStatusEffectsMixin
{
    @Inject(method = "removeAllEffects", at = @At("RETURN"))
    private void onClearStatusEffects(CallbackInfoReturnable<Boolean> cir)
    {
        LivingEntity livingEntity = (LivingEntity)(Object)this;

        if (!livingEntity.level().isClientSide && livingEntity instanceof Player player)
        {
            int duration = MilkAbilityCooldownEffectPreserver.getAndRemove(player.getUUID());
            if (duration > 0)
            {
                player.addEffect(new MobEffectInstance(IncarnaMobEffects.ABILITY_COOLDOWN, duration));
            }
        }
    }
}