package fr.factionbedrock.incarna.mixin;

import fr.factionbedrock.incarna.registry.IncarnaMobEffects;
import fr.factionbedrock.incarna.util.MilkAbilityCooldownEffectPreserver;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.MilkBucketItem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MilkBucketItem.class)
public class MilkBucketFinishUsingMixin
{
    @Inject(method = "finishUsingItem", at = @At("HEAD"))
    private void onFinishUsing(ItemStack stack, Level world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir)
    {
        if (!world.isClientSide() && user instanceof Player player && user.hasEffect(IncarnaMobEffects.ABILITY_COOLDOWN))
        {
            MilkAbilityCooldownEffectPreserver.add(player.getUUID(), player.getEffect(IncarnaMobEffects.ABILITY_COOLDOWN).getDuration());
        }
    }
}