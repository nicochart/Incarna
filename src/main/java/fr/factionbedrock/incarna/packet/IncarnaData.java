package fr.factionbedrock.incarna.packet;

import fr.factionbedrock.incarna.Incarna;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;

public record IncarnaData(String name) implements CustomPayload
{
    public static final Id<IncarnaData> ID = new Id<>(Incarna.id("ability_data"));

    public static final PacketCodec<RegistryByteBuf, IncarnaData> CODEC = PacketCodec.tuple(
            PacketCodecs.STRING, IncarnaData::name,
            IncarnaData::new);

    @Override public Id<? extends CustomPayload> getId() {return ID;}
}