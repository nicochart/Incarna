package fr.factionbedrock.incarna.power;

import fr.factionbedrock.incarna.util.ModifierInfo;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;

import java.util.function.Function;

public class HealOrDamageOverTimePower extends IncarnaTickablePower
{
    private final float baseAmount;
    private final Function<ModifierInfo, Float> amountModifier;
    private final Function<ServerPlayerEntity, Boolean> condition;

    public HealOrDamageOverTimePower(float baseAmount, Function<ModifierInfo, Float> amountModifier, Function<ServerPlayerEntity, Boolean> condition)
    {
        super(20);
        this.baseAmount = baseAmount;
        this.amountModifier = amountModifier;
        this.condition = condition;
    }

    @Override public void onRemovedFromPlayer(PlayerEntity player) {}

    @Override protected void tick(ServerPlayerEntity player, int powerLevel)
    {
        float amount = amountModifier.apply(new ModifierInfo(baseAmount, powerLevel));
        if (condition.apply(player))
        {
            if (amount > 0 && player.getHealth() < player.getMaxHealth())
            {
                player.heal(amount);
            }
            else if (amount < 0 && player.getHealth() > 0.0F)
            {
                amount *= -1;
                player.damage(player.getWorld().getDamageSources().generic(), amount);
            }
        }
    }
}
