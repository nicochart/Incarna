package fr.factionbedrock.incarna.power;

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

    public boolean canUse(ItemStack stack) {return canUse.apply(stack);}
}
