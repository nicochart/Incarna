package fr.factionbedrock.incarna.power;

import java.util.function.Function;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public abstract class AbilityPower extends IncarnaPower
{
    private final Function<ServerPlayer, Boolean> condition;

    public AbilityPower(Function<ServerPlayer, Boolean> canTick)
    {
        super();
        this.condition = canTick;
    }

    @Override public void onRemovedFromPlayer(Player player) {}

    public void tryTick(ServerPlayer player, int level)
    {
        if (condition.apply(player)) {this.tick(player, level);}
    }

    protected abstract void tick(ServerPlayer player, int level);
}
