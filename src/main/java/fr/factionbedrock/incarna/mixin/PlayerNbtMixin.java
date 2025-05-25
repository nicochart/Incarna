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
    private static String incarna_experience = "incarna_experience";
    private static String team = "team";
    private static String species = "species";

    @Inject(at = @At("RETURN"), method = "readCustomDataFromNbt")
    private void read(NbtCompound nbt, CallbackInfo info)
    {
        PlayerEntity player = (PlayerEntity) (Object) this;
        if (nbt.contains(incarna_experience, NbtElement.INT_TYPE))
        {
            player.getDataTracker().set(IncarnaTrackedData.INCARNA_EXPERIENCE, nbt.getInt(incarna_experience));
        }
        if (nbt.contains(team, NbtElement.STRING_TYPE))
        {
            player.getDataTracker().set(IncarnaTrackedData.TEAM, nbt.getString(team));
        }
        if (nbt.contains(species, NbtElement.STRING_TYPE))
        {
            player.getDataTracker().set(IncarnaTrackedData.SPECIES, nbt.getString(species));
        }
    }

    @Inject(at = @At("RETURN"), method = "writeCustomDataToNbt")
    private void write(NbtCompound nbt, CallbackInfo info)
    {
        PlayerEntity player = (PlayerEntity) (Object) this;
        nbt.putInt(incarna_experience, player.getDataTracker().get(IncarnaTrackedData.INCARNA_EXPERIENCE));
        nbt.putString(team, player.getDataTracker().get(IncarnaTrackedData.TEAM));
        nbt.putString(species, player.getDataTracker().get(IncarnaTrackedData.SPECIES));
    }
}
