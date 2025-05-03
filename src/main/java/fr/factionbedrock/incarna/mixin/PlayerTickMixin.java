package fr.factionbedrock.incarna.mixin;

import fr.factionbedrock.incarna.choice.IncarnaTeam;
import fr.factionbedrock.incarna.power.AttributeModifierPower;
import fr.factionbedrock.incarna.power.IncarnaPower;
import fr.factionbedrock.incarna.power.IncarnaTickablePower;
import fr.factionbedrock.incarna.registry.IncarnaTeams;
import fr.factionbedrock.incarna.registry.IncarnaTrackedData;
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

        IncarnaTeam playerTeam = IncarnaTeams.TEAM_NAMES.get(player.getDataTracker().get(IncarnaTrackedData.TEAM));
        for (IncarnaPower power : playerTeam.powers())
        {
            if (power instanceof IncarnaTickablePower tickablePower)
            {
                tickablePower.tryTick(player, player.getServerWorld().getTime(), 1);
            }

            if (power instanceof AttributeModifierPower modifierPower)
            {
                modifierPower.updatePlayerAttributeModifier(player, 1);
            }
        }
    }
}
