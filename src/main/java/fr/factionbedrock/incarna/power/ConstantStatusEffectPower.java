package fr.factionbedrock.incarna.power;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.function.Function;

public class ConstantStatusEffectPower extends IncarnaTickablePower
{
    private final RegistryEntry<StatusEffect> statusEffect;
    private final Function<Integer, Integer> levelToAmplifier;

    public ConstantStatusEffectPower(RegistryEntry<StatusEffect> statusEffect)
    {
        this(statusEffect, (level) -> 0);
    }

    public ConstantStatusEffectPower(RegistryEntry<StatusEffect> statusEffect, Function<Integer, Integer> levelToAmplifier)
    {
        super(20);
        this.statusEffect = statusEffect;
        this.levelToAmplifier = levelToAmplifier;
    }

    @Override protected void tick(ServerPlayerEntity player, int powerLevel)
    {
        if (!player.hasStatusEffect(statusEffect) || player.getStatusEffect(statusEffect).getDuration() < 159900)
        {
            player.addStatusEffect(new StatusEffectInstance(statusEffect, 160000, levelToAmplifier.apply(powerLevel), false, false));
        }
    }

    @Override public void onRemovedFromPlayer(PlayerEntity player)
    {
        if (player instanceof ServerPlayerEntity serverPlayer && serverPlayer.hasStatusEffect(statusEffect))
        {
            serverPlayer.removeStatusEffect(statusEffect);
        }
    }
}
