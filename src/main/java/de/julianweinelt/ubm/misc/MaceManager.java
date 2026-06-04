package de.julianweinelt.ubm.misc;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.enchantments.ModEnchantments;
import de.julianweinelt.ubm.items.ModItems;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Mod.EventBusSubscriber(modid = UBM.MODID)
public class MaceManager {
    public static Map<UUID, Float> highestY = new HashMap<>();
    public static Map<UUID, Boolean> falling = new HashMap<>();

    @SubscribeEvent
    public static void onUpdate(LivingEvent.LivingUpdateEvent e) {
        if (!(e.getEntityLiving() instanceof EntityPlayerMP)) return;

        EntityPlayerMP player = (EntityPlayerMP) e.getEntityLiving();
        UUID uuid = player.getUniqueID();

        if (player.onGround) {
            highestY.remove(uuid);
            falling.put(uuid, false);
            return;
        }

        falling.put(uuid, true);

        float y = (float) player.posY;
        highestY.merge(uuid, y, Math::max);
    }

    @SubscribeEvent
    public static void onAttack(AttackEntityEvent e) {
        EntityPlayer player = e.getEntityPlayer();
        UUID uuid = player.getUniqueID();

        ItemStack stack = player.getHeldItemMainhand();
        if (stack.isEmpty()) return;
        if (stack.getItem() != ModItems.MACE) return;

        float highest = highestY.getOrDefault(uuid, (float) player.posY);
        float fall = highest - (float) player.posY;

        if (fall < 1.5F) return;

        player.getEntityData().setFloat("mace_fall", fall);
    }

    @SubscribeEvent
    public static void onHurt(LivingHurtEvent e) {
        if (!(e.getSource().getTrueSource() instanceof EntityPlayer)) return;

        EntityPlayer player = (EntityPlayer) e.getSource().getTrueSource();
        UUID uuid = player.getUniqueID();

        float fall = player.getEntityData().getFloat("mace_fall");
        if (fall <= 0) return;

        float damage = calculateDamage(fall);

        ItemStack stack = player.getHeldItemMainhand();
        if (stack.getItem().equals(ModItems.MACE)) {
            if (!player.isCreative())
                stack.damageItem(1, player);
            if (EnchantmentHelper.getEnchantments(stack).containsKey(ModEnchantments.DENSITY)) {
                int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.DENSITY, stack);
                float strength = level * 0.25F;
                float plusDamage = fall * strength;
                damage += plusDamage;
            }
            if (EnchantmentHelper.getEnchantments(stack).containsKey(ModEnchantments.WINDBURST)) {
                int level = EnchantmentHelper.getEnchantmentLevel(ModEnchantments.WINDBURST, stack);
                double blocks = level;
                double[] multiplier = {2.02D, 2.04D, 2.06D};
                blocks *= multiplier[level - 1];
                player.motionY += blocks;
                player.velocityChanged = true;

                if (!player.world.isRemote)
                    player.playSound(ModSounds.MACE_AIR, 1.0F, 1.0F);
            }

        }

        UBM.getLogger().debug("Falling: " + fall);
        UBM.getLogger().debug("Damage: " + damage);

        if (!player.world.isRemote)
            player.playSound(ModSounds.MACE_GROUND, 1.0F, 1.0F);

        player.getEntityData().removeTag("mace_fall");

        e.setAmount(damage);
    }

    private static float calculateDamage(float fallenBlocks) {
        float damage = 0;

        if (fallenBlocks > 8) {
            damage += (fallenBlocks - 8);
            damage += 5 * 2;
            damage += 3 * 4;
        } else if (fallenBlocks > 3) {
            damage += (fallenBlocks - 3);
            damage += 3 * 4;
        } else {
            damage += fallenBlocks * 4;
        }

        return damage;
    }
}
