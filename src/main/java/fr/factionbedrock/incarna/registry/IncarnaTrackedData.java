package fr.factionbedrock.incarna.registry;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.player.Player;

public class IncarnaTrackedData
{
    public static final EntityDataAccessor<Integer> INCARNA_EXPERIENCE = SynchedEntityData.defineId(Player.class, EntityDataSerializers.INT);
    public static final EntityDataAccessor<String> TEAM = SynchedEntityData.defineId(Player.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<String> SPECIES = SynchedEntityData.defineId(Player.class, EntityDataSerializers.STRING);

    public static void load() {}
}
