package fr.factionbedrock.incarna.power;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;

public class ConstantStatusEffectPower extends IncarnaTickablePower
{
    private final RegistryEntry<StatusEffect> statusEffect;
    private final int minDuration;

    public ConstantStatusEffectPower(RegistryEntry<StatusEffect> statusEffect, int minDuration)
    {
        super(20);
        this.statusEffect = statusEffect;
        this.minDuration = minDuration;
    }

    public ConstantStatusEffectPower(RegistryEntry<StatusEffect> statusEffect)
    {
        this(statusEffect, 60);
    }

    @Override protected void tick(ServerPlayerEntity player, int powerLevel)
    {
        if (!player.hasStatusEffect(statusEffect) || player.getStatusEffect(statusEffect).getDuration() < minDuration)
        {
            player.addStatusEffect(new StatusEffectInstance(statusEffect, minDuration + 40, 0, false, false));
        }
    }
}
