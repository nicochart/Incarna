package fr.factionbedrock.incarna.power;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.ItemStack;

import java.util.function.Function;

public class FoodRestrictionPower extends ItemUseRestrictionPower
{
    public FoodRestrictionPower(Function<ItemStack, Boolean> canUse)
    {
        super(canUse);
    }

    @Override public boolean canUse(ItemStack stack)
    {
        FoodComponent foodComponent = stack.get(DataComponentTypes.FOOD);
        if (foodComponent == null) {return true;}
        else {return super.canUse(stack);}
    }
}
