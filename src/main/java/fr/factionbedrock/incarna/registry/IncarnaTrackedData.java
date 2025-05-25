package fr.factionbedrock.incarna.registry;

import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;

public class IncarnaTrackedData
{
    public static final TrackedData<Integer> INCARNA_EXPERIENCE = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.INTEGER);
    public static final TrackedData<String> TEAM = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.STRING);
    public static final TrackedData<String> SPECIES = DataTracker.registerData(PlayerEntity.class, TrackedDataHandlerRegistry.STRING);

    public static void load() {}
}
