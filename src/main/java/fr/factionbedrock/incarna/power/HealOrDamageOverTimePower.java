package fr.factionbedrock.incarna.power;

import fr.factionbedrock.incarna.util.ModifierInfo;
import java.util.function.Function;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class HealOrDamageOverTimePower extends IncarnaTickablePower
{
    private final float baseAmount;
    private final Function<ModifierInfo, Float> amountModifier;
    private final Function<ServerPlayer, Boolean> condition;

    public HealOrDamageOverTimePower(float baseAmount, Function<ModifierInfo, Float> amountModifier, Function<ServerPlayer, Boolean> condition)
    {
        super(20);
        this.baseAmount = baseAmount;
        this.amountModifier = amountModifier;
        this.condition = condition;
    }

    @Override public void onRemovedFromPlayer(Player player) {}

    @Override protected void tick(ServerPlayer player, int powerLevel)
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
                player.hurt(player.level().damageSources().generic(), amount);
            }
        }
    }
}
