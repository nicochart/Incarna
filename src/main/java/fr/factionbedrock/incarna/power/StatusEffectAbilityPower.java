package fr.factionbedrock.incarna.power;

import java.util.function.Function;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;

public class StatusEffectAbilityPower extends AbilityPower
{
    private final Function<Integer, MobEffectInstance> levelToEffectInstance;

    public StatusEffectAbilityPower(Function<Integer, MobEffectInstance> levelToEffectInstance)
    {
        this(levelToEffectInstance, (player) -> true);
    }

    public StatusEffectAbilityPower(Function<Integer, MobEffectInstance> levelToEffectInstance, Function<ServerPlayer, Boolean> canTick)
    {
        super(canTick);
        this.levelToEffectInstance = levelToEffectInstance;
    }

    @Override protected void tick(ServerPlayer player, int level)
    {
        player.addEffect(levelToEffectInstance.apply(level));
    }
}
