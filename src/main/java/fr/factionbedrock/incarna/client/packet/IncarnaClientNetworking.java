package fr.factionbedrock.incarna.client.packet;

import fr.factionbedrock.incarna.config.ServerLoadedConfig;
import fr.factionbedrock.incarna.packet.IncarnaS2CSynchData;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class IncarnaClientNetworking
{
    public static void registerClientReceiver()
    {
        ClientPlayNetworking.registerGlobalReceiver(IncarnaS2CSynchData.ID, (payload, context) ->
        {
            if (payload.name().equals("sync_nsh_data"))
            {
                ServerLoadedConfig.storeServerParams(payload.xpGainMultiplier(), payload.xpLossMultiplier());
            }
        });
    }
}
