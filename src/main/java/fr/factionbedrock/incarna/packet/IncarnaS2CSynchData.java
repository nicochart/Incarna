package fr.factionbedrock.incarna.packet;

import fr.factionbedrock.incarna.Incarna;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record IncarnaS2CSynchData(String name, float xpGainMultiplier, float xpLossMultiplier) implements CustomPayload
{
    public static final Id<IncarnaS2CSynchData> ID = new Id<>(Incarna.id("s2c_sync_data"));

    public static final PacketCodec<RegistryByteBuf, IncarnaS2CSynchData> CODEC = PacketCodec.tuple(
            PacketCodecs.STRING, IncarnaS2CSynchData::name,
            PacketCodecs.FLOAT, IncarnaS2CSynchData::xpGainMultiplier,
            PacketCodecs.FLOAT, IncarnaS2CSynchData::xpLossMultiplier,
            IncarnaS2CSynchData::new);

    @Override public Id<? extends CustomPayload> getId() {return ID;}
}