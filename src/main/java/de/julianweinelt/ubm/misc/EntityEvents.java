package de.julianweinelt.ubm.misc;

import de.julianweinelt.ubm.UBM;
import de.julianweinelt.ubm.blocks.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid = UBM.MODID)
public class EntityEvents {

    @SubscribeEvent
    public static void onEntityTick(TickEvent.PlayerTickEvent event) {
        Entity entity = event.player;
        World world = entity.world;

        if (world.isRemote) return;

        BlockPos pos = new BlockPos(entity.posX, entity.posY, entity.posZ);
        BlockPos pos2 = new BlockPos(entity.posX, entity.posY + 1, entity.posZ);
        if (world.getBlockState(pos).getBlock() == ModBlocks.POWDER_SNOW ||
            world.getBlockState(pos2).getBlock() == ModBlocks.POWDER_SNOW) {
            applySlowdown(entity);
        }
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            EntityPlayer player = event.player;
            BlockPos pos = new BlockPos(player.posX, player.posY, player.posZ);
            IBlockState state = player.world.getBlockState(pos);

            if (state.getBlock() == ModBlocks.POWDER_SNOW) {
                player.getEntityData().setBoolean("inFrozenBlock", true);
                player.getEntityData().setFloat("freezeTime", player.getEntityData().getFloat("freezeTime") + 0.005f);
            } else {
                player.getEntityData().setBoolean("inFrozenBlock", false);
                player.getEntityData().setFloat("freezeTime", 0F);
            }
        }
    }

    @SubscribeEvent
    public static void onRenderOverlay(RenderGameOverlayEvent.Pre event) {
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.player;

        if (player != null && player.getEntityData().getBoolean("inFrozenBlock")) {
            if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
                mc.getTextureManager().bindTexture(new ResourceLocation("ubm", "textures/gui/powder_snow_outline.png"));
                GlStateManager.color(1F, 1F, 1F, Math.min(1.0F, player.getEntityData().getFloat("freezeTime")));
                Gui.drawModalRectWithCustomSizedTexture(0, 0, 0, 0,
                        event.getResolution().getScaledWidth(),
                        event.getResolution().getScaledHeight(),
                        event.getResolution().getScaledWidth(),
                        event.getResolution().getScaledHeight());
            }
        }
    }

    @SubscribeEvent
    public void onRenderHearts(RenderGameOverlayEvent.Pre event) {
        if (event.getType() != RenderGameOverlayEvent.ElementType.HEALTH) return;

        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer player = mc.player;

        if (player == null) return;
        if (!player.getEntityData().getBoolean("inFrozenBlock")) return;

        event.setCanceled(true);

        int left = 10;
        int top = event.getResolution().getScaledHeight() - 39;

        float health = player.getHealth();
        float maxHealth = player.getMaxHealth();

        int hearts = MathHelper.ceil(maxHealth / 2.0F);

        for (int i = 0; i < hearts; i++) {
            int x = left + i * 8;
            int y = top;

            if (health >= (i + 1) * 2) {
                mc.getTextureManager().bindTexture(new ResourceLocation("ubm:textures/gui/heart/frozen_full.png"));
            } else if (health >= i * 2 + 1) {
                mc.getTextureManager().bindTexture(new ResourceLocation("ubm:textures/gui/heart/frozen_half.png"));
            } else {
                mc.getTextureManager().bindTexture(new ResourceLocation("ubm:textures/gui/heart/container.png"));
            }

            GlStateManager.color(1F, 1F, 1F, 1F);
            mc.ingameGUI.drawTexturedModalRect(x, y, 0, 0, 9, 9);
        }
    }


    @SubscribeEvent
    public void onPlayerTickDamage(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            EntityPlayer player = event.player;

            if (player.getEntityData().getBoolean("inFrozenBlock")) {
                int timer = player.getEntityData().getInteger("freezeTime");
                timer++;
                if (timer >= 100) {
                    player.attackEntityFrom(ModDamageSource.frostbite(), 1.0F);
                    timer = 0;
                }
            } else {
                player.getEntityData().setFloat("freezeTime", 0);
            }
        }
    }



    private static void applySlowdown(Entity entity) {
        entity.motionX *= 0.2D;
        entity.motionZ *= 0.2D;
        entity.motionY *= 0.6D;
    }
}
