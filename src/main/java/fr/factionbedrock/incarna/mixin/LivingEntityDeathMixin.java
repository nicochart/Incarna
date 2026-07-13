package fr.factionbedrock.incarna.mixin;

import fr.factionbedrock.incarna.registry.ExperienceDeltaReasons;
import fr.factionbedrock.incarna.registry.IncarnaTags;
import fr.factionbedrock.incarna.util.PlayerHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityDeathMixin
{
    //everything related to PLAYER death is done in PlayerDeathMixin (data tracker save, experience delta, etc)

    @Inject(at = @At("HEAD"), method = "die")
    private void applyOnDeath(DamageSource damageSource, CallbackInfo info)
    {
        LivingEntity deadEntity = (LivingEntity) (Object) this;

        if (deadEntity instanceof Player) {return;}
        if (!(damageSource.getEntity() instanceof Player playerAttacker)) {return;}

        if (deadEntity.getType().is(IncarnaTags.EntityTypes.BOSS))
        {
            PlayerHelper.deltaPlayerIncarnaExperience(playerAttacker, ExperienceDeltaReasons.BOSS_KILL);
        }
        else if (deadEntity.getType().is(IncarnaTags.EntityTypes.HOSTILE))
        {
            PlayerHelper.deltaPlayerIncarnaExperience(playerAttacker, ExperienceDeltaReasons.HOSTILE_ENTITY_KILL);
        }
    }
}
