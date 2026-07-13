package fr.factionbedrock.incarna.mixin;

import fr.factionbedrock.incarna.registry.IncarnaTrackedData;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerNbtMixin
{
    private static String incarna_experience = "incarna_experience";
    private static String team = "team";
    private static String species = "species";

    @Inject(at = @At("RETURN"), method = "readAdditionalSaveData")
    private void read(CompoundTag nbt, CallbackInfo info)
    {
        Player player = (Player) (Object) this;
        if (nbt.contains(incarna_experience, Tag.TAG_INT))
        {
            player.getEntityData().set(IncarnaTrackedData.INCARNA_EXPERIENCE, nbt.getInt(incarna_experience));
        }
        if (nbt.contains(team, Tag.TAG_STRING))
        {
            player.getEntityData().set(IncarnaTrackedData.TEAM, nbt.getString(team));
        }
        if (nbt.contains(species, Tag.TAG_STRING))
        {
            player.getEntityData().set(IncarnaTrackedData.SPECIES, nbt.getString(species));
        }
    }

    @Inject(at = @At("RETURN"), method = "addAdditionalSaveData")
    private void write(CompoundTag nbt, CallbackInfo info)
    {
        Player player = (Player) (Object) this;
        nbt.putInt(incarna_experience, player.getEntityData().get(IncarnaTrackedData.INCARNA_EXPERIENCE));
        nbt.putString(team, player.getEntityData().get(IncarnaTrackedData.TEAM));
        nbt.putString(species, player.getEntityData().get(IncarnaTrackedData.SPECIES));
    }
}
