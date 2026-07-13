package fr.factionbedrock.incarna.power;

import java.util.function.Function;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

public class TeleportOnProjectileHitOrDeflectPower extends ActionOnProjectileHitPower
{
    public TeleportOnProjectileHitOrDeflectPower(Function<Projectile, Boolean> shouldActionAndCancelHitOrDeflect)
    {
        super(shouldActionAndCancelHitOrDeflect);
    }

    @Override protected void tick(Player player)
    {
        Level world = player.level();
        if (!world.isClientSide)
        {
            for (int i = 0; i < 16; i++)
            {
                double d = player.getX() + (player.getRandom().nextDouble() - 0.5) * 16.0;
                double e = Mth.clamp(player.getY() + (player.getRandom().nextInt(16) - 8), world.getMinBuildHeight(), (world.getMinBuildHeight() + ((ServerLevel)world).getLogicalHeight() - 1));
                double f = player.getZ() + (player.getRandom().nextDouble() - 0.5) * 16.0;
                if (player.isPassenger()) {player.stopRiding();}

                Vec3 vec3d = player.position();
                if (player.randomTeleport(d, e, f, true))
                {
                    world.gameEvent(GameEvent.TELEPORT, vec3d, GameEvent.Context.of(player));
                    SoundSource soundCategory = SoundSource.PLAYERS;
                    SoundEvent soundEvent = SoundEvents.CHORUS_FRUIT_TELEPORT;

                    world.playSound(null, player.getX(), player.getY(), player.getZ(), soundEvent, soundCategory);
                    player.resetFallDistance();
                    break;
                }
            }

            player.resetCurrentImpulseContext();
        }
    }
}
