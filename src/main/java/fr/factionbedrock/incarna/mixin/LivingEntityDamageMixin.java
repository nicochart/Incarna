package fr.factionbedrock.incarna.mixin;

import fr.factionbedrock.incarna.choice.IncarnaTeam;
import fr.factionbedrock.incarna.power.DamageSufferedModifierPower;
import fr.factionbedrock.incarna.power.IncarnaPower;
import fr.factionbedrock.incarna.registry.IncarnaTeams;
import fr.factionbedrock.incarna.registry.IncarnaTrackedData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

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
            IncarnaTeam playerTeam = IncarnaTeams.TEAM_NAMES.get(player.getDataTracker().get(IncarnaTrackedData.TEAM));

            for (IncarnaPower power : playerTeam.powers())
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
}
