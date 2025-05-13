package fr.factionbedrock.incarna.power;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.function.Function;

public abstract class AbilityPower extends IncarnaPower
{
    private final Function<ServerPlayerEntity, Boolean> condition;

    public AbilityPower(Function<ServerPlayerEntity, Boolean> canTick)
    {
        super();
        this.condition = canTick;
    }

    @Override public void onRemovedFromPlayer(PlayerEntity player) {}

    public void tryTick(ServerPlayerEntity player, int level)
    {
        if (condition.apply(player)) {this.tick(player, level);}
    }

    protected abstract void tick(ServerPlayerEntity player, int level);
}
