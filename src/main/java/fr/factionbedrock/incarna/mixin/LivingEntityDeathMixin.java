package fr.factionbedrock.incarna.mixin;

import fr.factionbedrock.incarna.registry.ExperienceDeltaReasons;
import fr.factionbedrock.incarna.registry.IncarnaTags;
import fr.factionbedrock.incarna.util.PlayerHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityDeathMixin
{
    //everything related to PLAYER death  is done in PlayerDeathMixin (data tracker save, experience delta, etc)

    @Inject(at = @At("HEAD"), method = "onDeath")
    private void applyOnDeath(DamageSource damageSource, CallbackInfo info)
    {
        LivingEntity deadEntity = (LivingEntity) (Object) this;

        if (deadEntity instanceof PlayerEntity) {return;}
        if (!(damageSource.getAttacker() instanceof PlayerEntity playerAttacker)) {return;}

        if (deadEntity.getType().isIn(IncarnaTags.EntityTypes.BOSS))
        {
            PlayerHelper.deltaPlayerIncarnaExperience(playerAttacker, ExperienceDeltaReasons.BOSS_KILL);
        }
        else if (deadEntity.getType().isIn(IncarnaTags.EntityTypes.HOSTILE))
        {
            PlayerHelper.deltaPlayerIncarnaExperience(playerAttacker, ExperienceDeltaReasons.HOSTILE_ENTITY_KILL);
        }
    }
}
