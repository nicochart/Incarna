package fr.factionbedrock.incarna.power;

import java.util.function.Function;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class ItemUseRestrictionPower extends IncarnaPower
{
    private final Function<ItemStack, Boolean> canUse;
    public ItemUseRestrictionPower(Function<ItemStack, Boolean> canUse)
    {
        super();
        this.canUse = canUse;
    }

    @Override public void onRemovedFromPlayer(Player player) {}

    public boolean canUse(ItemStack stack) {return canUse.apply(stack);}
}
