package fr.factionbedrock.incarna.mixin;

import fr.factionbedrock.incarna.power.AttributeModifierPower;
import fr.factionbedrock.incarna.power.IncarnaPower;
import fr.factionbedrock.incarna.power.IncarnaTickablePower;
import fr.factionbedrock.incarna.registry.ExperienceDeltaReasons;
import fr.factionbedrock.incarna.util.PlayerHelper;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class PlayerTickMixin
{
    @Inject(at = @At("RETURN"), method = "tick")
    private void onTick(CallbackInfo info)
    {
        ServerPlayer player = (ServerPlayer) (Object) this;

        if (player.serverLevel().getGameTime() % 20 == 0) {PlayerHelper.deltaPlayerIncarnaExperience(player, ExperienceDeltaReasons.TIME);}

        for (IncarnaPower power : PlayerHelper.getAllPowersFrom(player))
        {
            if (power instanceof IncarnaTickablePower tickablePower)
            {
                tickablePower.tryTick(player, player.serverLevel().getGameTime(), PlayerHelper.getPlayerIncarnaLevel(player));
            }

            if (power instanceof AttributeModifierPower modifierPower)
            {
                modifierPower.updatePlayerAttributeModifier(player, PlayerHelper.getPlayerIncarnaLevel(player));
            }
        }
    }
}
