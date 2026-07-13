package fr.factionbedrock.incarna.registry;

import fr.factionbedrock.incarna.Incarna;
import fr.factionbedrock.incarna.effect.AbilityCooldownEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class IncarnaMobEffects
{
    public static final Holder<MobEffect> ABILITY_COOLDOWN = register("ability_cooldown", new AbilityCooldownEffect(MobEffectCategory.NEUTRAL, 6501508));

    private static Holder<MobEffect> register(String name, MobEffect statusEffect)
    {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Incarna.id(name), statusEffect);
    }

    public static void load() {}
}