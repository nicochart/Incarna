package fr.factionbedrock.incarna.power;

import net.minecraft.world.entity.player.Player;

public abstract class IncarnaPower
{
    public IncarnaPower() {}

    public abstract void onRemovedFromPlayer(Player player);
}
