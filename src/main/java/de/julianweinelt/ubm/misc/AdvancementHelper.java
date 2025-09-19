package de.julianweinelt.ubm.misc;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementProgress;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ResourceLocation;

public class AdvancementHelper {
    public static void grantAdvancement(EntityPlayer player, String advancementName) {
        if (player.world.isRemote) return;
        MinecraftServer server = player.getServer();

        EntityPlayerMP mP = (EntityPlayerMP) player;

        if (server == null) return;
        Advancement advancement = server.getAdvancementManager()
                .getAdvancement(new ResourceLocation("ubm", advancementName));
        if (advancement == null) return;

        AdvancementProgress progress = mP.getAdvancements().getProgress(advancement);
        if (!progress.isDone()) {
            for (String criterion : progress.getRemaningCriteria()) {
                mP.getAdvancements().grantCriterion(advancement, criterion);
            }
        }
    }
}
