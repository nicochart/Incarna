package fr.factionbedrock.incarna.packet;

import fr.factionbedrock.incarna.Incarna;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public record IncarnaS2CSynchData(String name, float xpGainMultiplier, float xpLossMultiplier, boolean displayDebugInfoInInfoScreen) implements CustomPacketPayload
{
    public static final Type<IncarnaS2CSynchData> ID = new Type<>(Incarna.id("s2c_sync_data"));

    public static final StreamCodec<RegistryFriendlyByteBuf, IncarnaS2CSynchData> CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, IncarnaS2CSynchData::name,
            ByteBufCodecs.FLOAT, IncarnaS2CSynchData::xpGainMultiplier,
            ByteBufCodecs.FLOAT, IncarnaS2CSynchData::xpLossMultiplier,
            ByteBufCodecs.BOOL, IncarnaS2CSynchData::displayDebugInfoInInfoScreen,
            IncarnaS2CSynchData::new);

    @Override public Type<? extends CustomPacketPayload> type() {return ID;}
}