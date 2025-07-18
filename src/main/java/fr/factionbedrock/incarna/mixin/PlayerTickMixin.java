package fr.factionbedrock.incarna.mixin;

import fr.factionbedrock.incarna.power.AttributeModifierPower;
import fr.factionbedrock.incarna.power.IncarnaPower;
import fr.factionbedrock.incarna.power.IncarnaTickablePower;
import fr.factionbedrock.incarna.registry.ExperienceDeltaReasons;
import fr.factionbedrock.incarna.util.PlayerHelper;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class PlayerTickMixin
{
    @Inject(at = @At("RETURN"), method = "tick")
    private void onTick(CallbackInfo info)
    {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;

        if (player.getServerWorld().getTime() % 20 == 0) {PlayerHelper.deltaPlayerIncarnaExperience(player, ExperienceDeltaReasons.TIME);}

        for (IncarnaPower power : PlayerHelper.getAllPowersFrom(player))
        {
            if (power instanceof IncarnaTickablePower tickablePower)
            {
                tickablePower.tryTick(player, player.getServerWorld().getTime(), PlayerHelper.getPlayerIncarnaLevel(player));
            }

            if (power instanceof AttributeModifierPower modifierPower)
            {
                modifierPower.updatePlayerAttributeModifier(player, PlayerHelper.getPlayerIncarnaLevel(player));
            }
        }
    }
}
