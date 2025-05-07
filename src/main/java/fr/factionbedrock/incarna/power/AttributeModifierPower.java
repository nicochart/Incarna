package fr.factionbedrock.incarna.power;

import fr.factionbedrock.incarna.Incarna;
import fr.factionbedrock.incarna.util.ModifierInfo;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class AttributeModifierPower extends IncarnaPower
{
    public static final Identifier SPEED_MODIFIER = Incarna.id("speed_modifier");
    public static final Identifier JUMP_STRENGTH_MODIFIER = Incarna.id("jump_strength_modifier");
    public static final Identifier ARMOR_MODIFIER = Incarna.id("armor_modifier");
    public static final Identifier SCALE_MODIFIER = Incarna.id("scale_modifier");

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

    @Override public void onRemovedFromPlayer(PlayerEntity player)
    {
        if (player instanceof ServerPlayerEntity serverPlayer) {removePlayerAttributeModifier(serverPlayer);}
    }

    public void updatePlayerAttributeModifier(ServerPlayerEntity player, int powerLevel)
    {
        EntityAttributeInstance attribute = player.getAttributeInstance(this.attribute);
        if (attribute != null)
        {
            attribute.removeModifier(this.modifierId);
            attribute.addTemporaryModifier(this.getModifier(powerLevel));
        }
    }

    public void removePlayerAttributeModifier(ServerPlayerEntity player)
    {
        EntityAttributeInstance attribute = player.getAttributeInstance(this.attribute);
        if (attribute != null)
        {
            attribute.removeModifier(this.modifierId);
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
}
