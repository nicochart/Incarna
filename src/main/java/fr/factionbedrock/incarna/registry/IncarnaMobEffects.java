package fr.factionbedrock.incarna.registry;

import fr.factionbedrock.incarna.Incarna;
import fr.factionbedrock.incarna.effect.AbilityCooldownEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public class IncarnaMobEffects
{
    public static final RegistryEntry<StatusEffect> ABILITY_COOLDOWN = register("ability_cooldown", new AbilityCooldownEffect(StatusEffectCategory.NEUTRAL, 6501508));

    private static RegistryEntry<StatusEffect> register(String name, StatusEffect statusEffect)
    {
        return Registry.registerReference(Registries.STATUS_EFFECT, Incarna.id(name), statusEffect);
    }

    public static void load() {}
}