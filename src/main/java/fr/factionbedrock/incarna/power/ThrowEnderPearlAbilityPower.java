package fr.factionbedrock.incarna.power;

import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

import java.util.function.Function;

public class ThrowEnderPearlAbilityPower extends AbilityPower
{
    public ThrowEnderPearlAbilityPower() {this((player) -> true);}

    public ThrowEnderPearlAbilityPower(Function<ServerPlayerEntity, Boolean> canTick) {super(canTick);}

    @Override protected void tick(ServerPlayerEntity player, int level)
    {
        ServerWorld world = player.getServerWorld();
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.getRandom().nextFloat() * 0.4F + 0.8F));
        EnderPearlEntity enderPearlEntity = new EnderPearlEntity(world, player);
        enderPearlEntity.setItem(Items.ENDER_PEARL.getDefaultStack());
        enderPearlEntity.setVelocity(player, player.getPitch(), player.getYaw(), 0.0F, 1.5F, 1.0F);
        world.spawnEntity(enderPearlEntity);
    }
}
