package fr.factionbedrock.incarna.event;

import fr.factionbedrock.incarna.choice.IncarnaTeam;
import fr.factionbedrock.incarna.registry.IncarnaPowers;
import fr.factionbedrock.incarna.registry.IncarnaTags;
import fr.factionbedrock.incarna.registry.IncarnaTeams;
import fr.factionbedrock.incarna.registry.IncarnaTrackedData;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.TypedActionResult;

public class PlayerEvents
{
    public static void registerUseItemCallback()
    {
        UseItemCallback.EVENT.register((player, world, hand) ->
        {
            ItemStack stack = player.getStackInHand(hand);
            Item item = stack.getItem();
            FoodComponent foodComponent = (FoodComponent)stack.get(DataComponentTypes.FOOD);

            if (player.isCreative() || player.isSpectator() || foodComponent == null) {return TypedActionResult.pass(stack);}

            IncarnaTeam playerTeam = IncarnaTeams.TEAM_NAMES.get(player.getDataTracker().get(IncarnaTrackedData.TEAM));
            if (playerTeam.powers().contains(IncarnaPowers.VEGAN) && stack.isIn(IncarnaTags.Items.MEAT))
            {
                return TypedActionResult.fail(stack);
            }
            else if (playerTeam.powers().contains(IncarnaPowers.CARNIVORE) && !stack.isIn(IncarnaTags.Items.MEAT))
            {
                return TypedActionResult.fail(stack);
            }

            return TypedActionResult.pass(stack);
        });
    }
}
