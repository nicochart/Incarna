package fr.factionbedrock.incarna.power;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.function.Function;

public class TeleportOnProjectileHitOrDeflectPower extends ActionOnProjectileHitPower
{
    public TeleportOnProjectileHitOrDeflectPower(Function<ProjectileEntity, Boolean> shouldActionAndCancelHitOrDeflect)
    {
        super(shouldActionAndCancelHitOrDeflect);
    }

    @Override protected void tick(PlayerEntity player)
    {
        World world = player.getWorld();
        if (!world.isClient)
        {
            for (int i = 0; i < 16; i++)
            {
                double d = player.getX() + (player.getRandom().nextDouble() - 0.5) * 16.0;
                double e = MathHelper.clamp(player.getY() + (player.getRandom().nextInt(16) - 8), world.getBottomY(), (world.getBottomY() + ((ServerWorld)world).getLogicalHeight() - 1));
                double f = player.getZ() + (player.getRandom().nextDouble() - 0.5) * 16.0;
                if (player.hasVehicle()) {player.stopRiding();}

                Vec3d vec3d = player.getPos();
                if (player.teleport(d, e, f, true))
                {
                    world.emitGameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Emitter.of(player));
                    SoundCategory soundCategory = SoundCategory.PLAYERS;
                    SoundEvent soundEvent = SoundEvents.ITEM_CHORUS_FRUIT_TELEPORT;

                    world.playSound(null, player.getX(), player.getY(), player.getZ(), soundEvent, soundCategory);
                    player.onLanding();
                    break;
                }
            }

            player.clearCurrentExplosion();
        }
    }
}
