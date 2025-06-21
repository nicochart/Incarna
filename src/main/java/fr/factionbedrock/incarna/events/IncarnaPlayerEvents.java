package fr.factionbedrock.incarna.events;

import fr.factionbedrock.incarna.Incarna;
import fr.factionbedrock.incarna.packet.IncarnaS2CSynchData;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;

public class IncarnaPlayerEvents
{
    public static void registerPlayerEvents()
    {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) ->
        {
            ServerPlayerEntity player = handler.getPlayer();
            ServerPlayNetworking.send(player, new IncarnaS2CSynchData("sync_nsh_data", Incarna.CONFIG.xpGainMultiplier, Incarna.CONFIG.xpLossMultiplier));
        });

        ServerPlayerEvents.AFTER_RESPAWN.register((oldPlayer, newPlayer, alive) ->
        {
            ServerPlayNetworking.send(newPlayer, new IncarnaS2CSynchData("sync_nsh_data", Incarna.CONFIG.xpGainMultiplier, Incarna.CONFIG.xpLossMultiplier));
        });
    }
}
