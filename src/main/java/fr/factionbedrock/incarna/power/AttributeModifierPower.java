package fr.factionbedrock.incarna.power;

import fr.factionbedrock.incarna.Incarna;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class AttributeModifierPower extends IncarnaPower
{
    public static final Identifier SPEED_MODIFIER = Incarna.id("speed_modifier");

    private final RegistryEntry<EntityAttribute> attribute;
    private final Identifier modifierId;
    private final double baseValue;
    private final Function<ModifierInfo, Double> modifierValue;
    private final EntityAttributeModifier.Operation modifierOperation;


    public AttributeModifierPower(RegistryEntry<EntityAttribute> attribute, Identifier modifierId, double baseValue, Function<ModifierInfo, Double> modifierValue, EntityAttributeModifier.Operation modifierOperation)
    {
        super();
        this.attribute = attribute;
        this.modifierId = modifierId;
        this.baseValue = baseValue;
        this.modifierValue = modifierValue;
        this.modifierOperation = modifierOperation;
    }

    public void updatePlayerAttributeModifier(ServerPlayerEntity player)
    {
        EntityAttributeInstance attribute = player.getAttributeInstance(this.attribute);
        if (attribute != null)
        {
            attribute.removeModifier(this.modifierId);
            attribute.addTemporaryModifier(this.getModifier(1));
        }
    }

    private EntityAttributeModifier getModifier(int powerLevel)
    {
        return new EntityAttributeModifier(
                modifierId,
                modifierValue.apply(new ModifierInfo(baseValue, powerLevel)),
                modifierOperation
        );
    }

    public Identifier getId() {return modifierId;}

    public record ModifierInfo(double baseModifierValue, int level) {}
}
