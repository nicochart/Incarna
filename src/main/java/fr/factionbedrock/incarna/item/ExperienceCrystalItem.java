package fr.factionbedrock.incarna.item;

import fr.factionbedrock.incarna.registry.ExperienceDeltaReasons;
import fr.factionbedrock.incarna.util.PlayerHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class ExperienceCrystalItem extends Item
{
    public ExperienceCrystalItem(Settings settings) {super(settings);}

    @Override public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user)
    {
        ItemStack itemStack = super.finishUsing(stack, world, user);
        if (user instanceof PlayerEntity player)
        {
            PlayerHelper.deltaPlayerIncarnaExperience(player, ExperienceDeltaReasons.EXPERIENCE_CRYSTAL_CONSUME);
        }
        return itemStack;
    }

    @Override public void appendTooltip(ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type)
    {
        tooltip.add(this.getDescription().formatted(Formatting.GRAY));
    }

    public MutableText getDescription() {return Text.translatable(this.getTranslationKey() + ".desc");}
}
