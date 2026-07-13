package fr.factionbedrock.incarna.event;

import fr.factionbedrock.incarna.power.IncarnaPower;
import fr.factionbedrock.incarna.power.ItemUseRestrictionPower;
import fr.factionbedrock.incarna.util.PlayerHelper;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.item.ItemStack;

public class PlayerEvents
{
    public static void registerUseItemCallback()
    {
        UseItemCallback.EVENT.register((player, world, hand) ->
        {
            ItemStack stack = player.getItemInHand(hand);
            if (player.isCreative() || player.isSpectator()) {return InteractionResultHolder.pass(stack);}
            boolean canUse = true;
            for (IncarnaPower power : PlayerHelper.getAllPowersFrom(player))
            {
                if (power instanceof ItemUseRestrictionPower itemRestrictionPower)
                {
                    if (!itemRestrictionPower.canUse(stack)) {canUse = false;}
                }
            }

            if (!canUse) {return InteractionResultHolder.fail(stack);}
            return InteractionResultHolder.pass(stack);
        });
    }
}
