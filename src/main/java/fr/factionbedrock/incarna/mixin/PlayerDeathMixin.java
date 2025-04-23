package fr.factionbedrock.incarna.mixin;

import fr.factionbedrock.incarna.config.ServerLoadedConfig;
import fr.factionbedrock.incarna.registry.IncarnaTrackedData;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public class PlayerDeathMixin
{
    @Inject(at = @At("HEAD"), method = "onDeath")
    private void applyOnDeath(DamageSource damageSource, CallbackInfo info)
    {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;
        /*... previousData = player.getDataTracker().get(IncarnaTrackedData.DATA);
        if ()
        {
            player.getDataTracker().set(IncarnaTrackedData.DATA, updatedData);
        }*/
    }

    @Inject(at = @At("HEAD"), method = "copyFrom")
    private void applyOnCopyFrom(ServerPlayerEntity oldPlayer, boolean alive, CallbackInfo info)
    {
        ServerPlayerEntity newPlayer = (ServerPlayerEntity) (Object) this;
        /*... data = oldPlayer.getDataTracker().get(IncarnaTrackedData.DATA);
        newPlayer.getDataTracker().set(IncarnaTrackedData.DATA, data);*/
    }
}