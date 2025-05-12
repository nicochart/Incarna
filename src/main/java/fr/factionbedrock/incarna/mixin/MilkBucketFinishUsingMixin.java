package fr.factionbedrock.incarna.mixin;

import fr.factionbedrock.incarna.registry.IncarnaMobEffects;
import fr.factionbedrock.incarna.util.MilkAbilityCooldownEffectPreserver;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MilkBucketItem.class)
public class MilkBucketFinishUsingMixin
{
    @Inject(method = "finishUsing", at = @At("HEAD"))
    private void onFinishUsing(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir)
    {
        if (!world.isClient() && user instanceof PlayerEntity player && user.hasStatusEffect(IncarnaMobEffects.ABILITY_COOLDOWN))
        {
            MilkAbilityCooldownEffectPreserver.add(player.getUuid(), player.getStatusEffect(IncarnaMobEffects.ABILITY_COOLDOWN).getDuration());
        }
    }
}