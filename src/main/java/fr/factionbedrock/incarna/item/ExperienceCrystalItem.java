package fr.factionbedrock.incarna.item;

import fr.factionbedrock.incarna.registry.ExperienceDeltaReasons;
import fr.factionbedrock.incarna.util.PlayerHelper;
import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public class ExperienceCrystalItem extends Item
{
    public ExperienceCrystalItem(Properties settings) {super(settings);}

    @Override public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity user)
    {
        ItemStack itemStack = super.finishUsingItem(stack, world, user);
        if (user instanceof Player player)
        {
            PlayerHelper.deltaPlayerIncarnaExperience(player, ExperienceDeltaReasons.EXPERIENCE_CRYSTAL_CONSUME);
        }
        return itemStack;
    }

    @Override public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag type)
    {
        tooltip.add(this.getDescription().withStyle(ChatFormatting.GRAY));
    }

    public MutableComponent getDescription() {return Component.translatable(this.getDescriptionId() + ".desc");}
}
