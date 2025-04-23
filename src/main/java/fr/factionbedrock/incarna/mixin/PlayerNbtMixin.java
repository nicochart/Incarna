package fr.factionbedrock.incarna.mixin;

import fr.factionbedrock.incarna.registry.IncarnaTrackedData;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerEntity.class)
public class PlayerNbtMixin
{
    private static String data = "data_id_in_nbts";

    @Inject(at = @At("RETURN"), method = "readCustomDataFromNbt")
    private void read(NbtCompound nbt, CallbackInfo info)
    {
        PlayerEntity player = (PlayerEntity) (Object) this;
        /*if (nbt.contains(data, NbtElement.TYPE))
        {
            player.getDataTracker().set(IncarnaTrackedData.DATA, nbt.getInt(data));
        }*/
    }

    @Inject(at = @At("RETURN"), method = "writeCustomDataToNbt")
    private void write(NbtCompound nbt, CallbackInfo info)
    {
        PlayerEntity player = (PlayerEntity) (Object) this;
        //nbt.putInt(data, player.getDataTracker().get(IncarnaTrackedData.DATA));
    }
}
