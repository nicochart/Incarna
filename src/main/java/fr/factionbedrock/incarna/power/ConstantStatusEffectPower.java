package fr.factionbedrock.incarna.power;

import java.util.function.Function;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;

public class ConstantStatusEffectPower extends IncarnaTickablePower
{
    private final Holder<MobEffect> statusEffect;
    private final Function<Integer, Integer> levelToAmplifier;

    public ConstantStatusEffectPower(Holder<MobEffect> statusEffect)
    {
        this(statusEffect, (level) -> 0);
    }

    public ConstantStatusEffectPower(Holder<MobEffect> statusEffect, Function<Integer, Integer> levelToAmplifier)
    {
        super(20);
        this.statusEffect = statusEffect;
        this.levelToAmplifier = levelToAmplifier;
    }

    @Override protected void tick(ServerPlayer player, int powerLevel)
    {
        if (!player.hasEffect(statusEffect) || player.getEffect(statusEffect).getDuration() < 159900)
        {
            player.addEffect(new MobEffectInstance(statusEffect, 160000, levelToAmplifier.apply(powerLevel), false, false));
        }
    }

    @Override public void onRemovedFromPlayer(Player player)
    {
        if (player instanceof ServerPlayer serverPlayer && serverPlayer.hasEffect(statusEffect))
        {
            serverPlayer.removeEffect(statusEffect);
        }
    }
}
