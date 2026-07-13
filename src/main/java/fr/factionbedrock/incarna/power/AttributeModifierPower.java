package fr.factionbedrock.incarna.power;

import fr.factionbedrock.incarna.Incarna;
import fr.factionbedrock.incarna.util.ModifierInfo;
import java.util.function.Function;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;

public class AttributeModifierPower extends IncarnaPower
{
    public static final ResourceLocation SPEED_MODIFIER = Incarna.id("speed_modifier");
    public static final ResourceLocation JUMP_STRENGTH_MODIFIER = Incarna.id("jump_strength_modifier");
    public static final ResourceLocation ARMOR_MODIFIER = Incarna.id("armor_modifier");
    public static final ResourceLocation SCALE_MODIFIER = Incarna.id("scale_modifier");

    private final Holder<Attribute> attribute;
    private final ResourceLocation modifierId;
    private final double baseValue;
    private final Function<ModifierInfo, Double> modifierValue;
    private final AttributeModifier.Operation modifierOperation;


    public AttributeModifierPower(Holder<Attribute> attribute, ResourceLocation modifierId, double baseValue, Function<ModifierInfo, Double> modifierValue, AttributeModifier.Operation modifierOperation)
    {
        super();
        this.attribute = attribute;
        this.modifierId = modifierId;
        this.baseValue = baseValue;
        this.modifierValue = modifierValue;
        this.modifierOperation = modifierOperation;
    }

    @Override public void onRemovedFromPlayer(Player player)
    {
        if (player instanceof ServerPlayer serverPlayer) {removePlayerAttributeModifier(serverPlayer);}
    }

    public void updatePlayerAttributeModifier(ServerPlayer player, int powerLevel)
    {
        AttributeInstance attribute = player.getAttribute(this.attribute);
        if (attribute != null)
        {
            attribute.removeModifier(this.modifierId);
            attribute.addTransientModifier(this.getModifier(powerLevel));
        }
    }

    public void removePlayerAttributeModifier(ServerPlayer player)
    {
        AttributeInstance attribute = player.getAttribute(this.attribute);
        if (attribute != null)
        {
            attribute.removeModifier(this.modifierId);
        }
    }

    private AttributeModifier getModifier(int powerLevel)
    {
        return new AttributeModifier(
                modifierId,
                modifierValue.apply(new ModifierInfo(baseValue, powerLevel)),
                modifierOperation
        );
    }

    public ResourceLocation getId() {return modifierId;}
}
