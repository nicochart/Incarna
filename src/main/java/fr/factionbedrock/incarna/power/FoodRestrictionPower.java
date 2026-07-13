package fr.factionbedrock.incarna.power;

import java.util.function.Function;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;

public class FoodRestrictionPower extends ItemUseRestrictionPower
{
    public FoodRestrictionPower(Function<ItemStack, Boolean> canUse)
    {
        super(canUse);
    }

    @Override public boolean canUse(ItemStack stack)
    {
        FoodProperties foodComponent = stack.get(DataComponents.FOOD);
        if (foodComponent == null) {return true;}
        else {return super.canUse(stack);}
    }
}
