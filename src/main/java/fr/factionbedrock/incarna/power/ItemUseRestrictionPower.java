package fr.factionbedrock.incarna.power;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

import java.util.function.Function;

public class ItemUseRestrictionPower extends IncarnaPower
{
    private final Function<ItemStack, Boolean> canUse;
    public ItemUseRestrictionPower(Function<ItemStack, Boolean> canUse)
    {
        super();
        this.canUse = canUse;
    }

    @Override public void onRemovedFromPlayer(PlayerEntity player) {}

    public boolean canUse(ItemStack stack) {return canUse.apply(stack);}
}
