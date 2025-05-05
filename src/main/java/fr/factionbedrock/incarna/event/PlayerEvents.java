package fr.factionbedrock.incarna.event;

import fr.factionbedrock.incarna.power.IncarnaPower;
import fr.factionbedrock.incarna.power.ItemUseRestrictionPower;
import fr.factionbedrock.incarna.util.PlayerHelper;
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
            boolean canUse = true;
            for (IncarnaPower power : PlayerHelper.getAllPowersFrom(player))
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
