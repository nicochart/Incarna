package fr.factionbedrock.incarna.power;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.function.Function;

public class StatusEffectAbilityPower extends AbilityPower
{
    private final Function<Integer, StatusEffectInstance> levelToEffectInstance;

    public StatusEffectAbilityPower(Function<Integer, StatusEffectInstance> levelToEffectInstance)
    {
        this(levelToEffectInstance, (player) -> true);
    }

    public StatusEffectAbilityPower(Function<Integer, StatusEffectInstance> levelToEffectInstance, Function<ServerPlayerEntity, Boolean> canTick)
    {
        super(canTick);
        this.levelToEffectInstance = levelToEffectInstance;
    }

    @Override protected void tick(ServerPlayerEntity player, int level)
    {
        player.addStatusEffect(levelToEffectInstance.apply(level));
    }
}
