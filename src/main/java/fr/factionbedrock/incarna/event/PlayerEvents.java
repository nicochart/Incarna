package fr.factionbedrock.incarna.event;

import fr.factionbedrock.incarna.choice.IncarnaTeam;
import fr.factionbedrock.incarna.power.IncarnaPower;
import fr.factionbedrock.incarna.power.ItemUseRestrictionPower;
import fr.factionbedrock.incarna.registry.IncarnaTeams;
import fr.factionbedrock.incarna.registry.IncarnaTrackedData;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.util.TypedActionResult;

public class PlayerEvents
{
    public static void registerUseItemCallback()
    {
        UseItemCallback.EVENT.register((player, world, hand) ->
        {
            ItemStack stack = player.getStackInHand(hand);
            if (player.isCreative() || player.isSpectator()) {return TypedActionResult.pass(stack);}
            IncarnaTeam playerTeam = IncarnaTeams.TEAM_NAMES.get(player.getDataTracker().get(IncarnaTrackedData.TEAM));
            boolean canUse = true;
            for (IncarnaPower power : playerTeam.powers())
            {
                if (power instanceof ItemUseRestrictionPower itemRestrictionPower)
                {
                    if (!itemRestrictionPower.canUse(stack)) {canUse = false;}
                }
            }

            if (!canUse) {return TypedActionResult.fail(stack);}
            return TypedActionResult.pass(stack);
        });
    }
}
