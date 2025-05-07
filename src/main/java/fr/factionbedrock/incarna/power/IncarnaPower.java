package fr.factionbedrock.incarna.power;

import net.minecraft.entity.player.PlayerEntity;

public abstract class IncarnaPower
{
    public IncarnaPower() {}

    public abstract void onRemovedFromPlayer(PlayerEntity player);
}
