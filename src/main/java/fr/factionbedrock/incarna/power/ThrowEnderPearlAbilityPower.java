package fr.factionbedrock.incarna.power;

import java.util.function.Function;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.Items;

public class ThrowEnderPearlAbilityPower extends AbilityPower
{
    public ThrowEnderPearlAbilityPower() {this((player) -> true);}

    public ThrowEnderPearlAbilityPower(Function<ServerPlayer, Boolean> canTick) {super(canTick);}

    @Override protected void tick(ServerPlayer player, int level)
    {
        ServerLevel world = player.serverLevel();
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDER_PEARL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        ThrownEnderpearl enderPearlEntity = new ThrownEnderpearl(world, player);
        enderPearlEntity.setItem(Items.ENDER_PEARL.getDefaultInstance());
        enderPearlEntity.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
        world.addFreshEntity(enderPearlEntity);
    }
}
