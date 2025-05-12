package fr.factionbedrock.incarna.packet;

import fr.factionbedrock.incarna.util.PlayerHelper;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;

public class IncarnaNetworking
{

    public static void registerData()
    {
        PayloadTypeRegistry.playC2S().register(IncarnaData.ID, IncarnaData.CODEC);
        PayloadTypeRegistry.playS2C().register(IncarnaData.ID, IncarnaData.CODEC);
    }

    public static void registerServerReceiver()
    {
        ServerPlayNetworking.registerGlobalReceiver(IncarnaData.ID, (payload, context) ->
        {
            if (payload.name().equals("ability_use"))
            {
                PlayerHelper.onPlayerUseAbility(context.player());
            }
        });
    }
}
