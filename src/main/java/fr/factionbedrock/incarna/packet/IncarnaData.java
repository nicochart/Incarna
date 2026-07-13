package fr.factionbedrock.incarna.packet;

import fr.factionbedrock.incarna.Incarna;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record IncarnaData(String name) implements CustomPacketPayload
{
    public static final Type<IncarnaData> ID = new Type<>(Incarna.id("ability_data"));

    public static final StreamCodec<RegistryFriendlyByteBuf, IncarnaData> CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, IncarnaData::name,
            IncarnaData::new);

    @Override public Type<? extends CustomPacketPayload> type() {return ID;}
}