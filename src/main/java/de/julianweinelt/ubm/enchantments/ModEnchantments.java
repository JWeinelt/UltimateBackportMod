package de.julianweinelt.ubm.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class ModEnchantments {

    public static final Enchantment SWIFT_SNEAK = new EnchantmentSwiftSneak();
    public static final Enchantment QUICK_CHARGE = new EnchantmentQuickCharge();

    @SubscribeEvent
    public static void registerEnchantments(RegistryEvent.Register<Enchantment> event) {
        event.getRegistry().registerAll(
                SWIFT_SNEAK,
                QUICK_CHARGE
        );
    }


    @SubscribeEvent
    public static void onPlayerUpdate(LivingEvent.LivingUpdateEvent event) {
        if (!(event.getEntityLiving() instanceof EntityPlayer)) return;

        EntityPlayer player = (EntityPlayer) event.getEntityLiving();

        if (player.isSneaking()) {
            boolean hasEnchantment = false;

            ItemStack leggings = player.getItemStackFromSlot(EntityEquipmentSlot.LEGS);
            if (leggings != null && EnchantmentHelper.getEnchantmentLevel(ModEnchantments.SWIFT_SNEAK, leggings) > 0) {
                hasEnchantment = true;
            }

            if (hasEnchantment) {
                double factor = 4D;

                if (player.moveForward != 0 || player.moveStrafing != 0) {
                    player.moveRelative(0F, 0F, 0.02F * (float) factor, 0.02F);
                }
            }
        }
    }
}
