package fr.factionbedrock.incarna.power;

import net.minecraft.server.network.ServerPlayerEntity;

public abstract class IncarnaTickablePower extends IncarnaPower
{
    public int updateInterval;

    public IncarnaTickablePower() {this(1);}

    public IncarnaTickablePower(int updateInterval)
    {
        super();
        if (updateInterval < 1) {throw new IllegalArgumentException("Incarna Powers Error : update interval must be > 0");}
        this.updateInterval = updateInterval;
    }

    public void tryTick(ServerPlayerEntity player, long worldTime, int powerLevel)
    {
        if (updateInterval == 1 || worldTime % this.updateInterval == 0) {this.tick(player, powerLevel);}
    }

    protected abstract void tick(ServerPlayerEntity player, int powerLevel);
}
