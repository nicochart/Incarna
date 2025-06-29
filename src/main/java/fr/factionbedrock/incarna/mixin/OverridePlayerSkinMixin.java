package fr.factionbedrock.incarna.mixin;

import fr.factionbedrock.incarna.Incarna;
import fr.factionbedrock.incarna.choice.IncarnaSpecie;
import fr.factionbedrock.incarna.registry.IncarnaSpecies;
import fr.factionbedrock.incarna.util.PlayerHelper;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.util.SkinTextures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayerEntity.class)
public class OverridePlayerSkinMixin
{
    @Inject(at = @At("HEAD"), method = "getSkinTextures", cancellable = true)
    public void overrideSkin(CallbackInfoReturnable<SkinTextures> cir)
    {
        AbstractClientPlayerEntity player = (AbstractClientPlayerEntity) (Object) this;

        IncarnaSpecie playerSpecies = PlayerHelper.getPlayerSpecies(player);
        if (playerSpecies == IncarnaSpecies.DRAGON_BORN) {cir.setReturnValue(createSkinTextures("textures/entity/player/wide/dragon_born.png"));}
        else if (playerSpecies == IncarnaSpecies.TIDE_BORN) {cir.setReturnValue(createSkinTextures("textures/entity/player/wide/tide_born.png"));}
        else if (playerSpecies == IncarnaSpecies.ORC) {cir.setReturnValue(createSkinTextures("textures/entity/player/wide/orc.png"));}
        else if (playerSpecies == IncarnaSpecies.NETHERIAN) {cir.setReturnValue(createSkinTextures("textures/entity/player/wide/netherian.png"));}
        else if (playerSpecies == IncarnaSpecies.FALLEN_ANGEL) {cir.setReturnValue(createSkinTextures("textures/entity/player/wide/fallen_angel.png"));}
        else if (playerSpecies == IncarnaSpecies.ENDERIAN) {cir.setReturnValue(createSkinTextures("textures/entity/player/wide/enderian.png"));}
        else if (playerSpecies == IncarnaSpecies.VOID_BORN) {cir.setReturnValue(createSkinTextures("textures/entity/player/wide/void_born.png"));}
        else if (playerSpecies == IncarnaSpecies.END_HYBRID) {cir.setReturnValue(createSkinTextures("textures/entity/player/wide/end_hybrid.png"));}
        else if (playerSpecies == IncarnaSpecies.SHADOW_BORN) {cir.setReturnValue(createSkinTextures("textures/entity/player/wide/shadow_born.png"));}
        else if (playerSpecies == IncarnaSpecies.ELF) {cir.setReturnValue(createSkinTextures("textures/entity/player/wide/elf.png"));}
        else if (playerSpecies == IncarnaSpecies.AERIAN) {cir.setReturnValue(createSkinTextures("textures/entity/player/wide/aerian.png"));}
        else if (playerSpecies == IncarnaSpecies.LUNAR_ELF) {cir.setReturnValue(createSkinTextures("textures/entity/player/wide/lunar_elf.png"));}
        else if (playerSpecies == IncarnaSpecies.VOLUCITE_SERVANT) {cir.setReturnValue(createSkinTextures("textures/entity/player/wide/volucite_servant.png"));}
    }

    private static SkinTextures createSkinTextures(String texture)
    {
        return createSkinTextures(texture, SkinTextures.Model.WIDE);
    }

    private static SkinTextures createSkinTextures(String texture, SkinTextures.Model model)
    {
        return new SkinTextures(Incarna.id(texture), null, null, null, model, true);
    }
}
