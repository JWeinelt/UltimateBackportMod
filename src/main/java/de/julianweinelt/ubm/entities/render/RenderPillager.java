package de.julianweinelt.ubm.entities.render;

import de.julianweinelt.ubm.entities.EntityPillager;
import de.julianweinelt.ubm.entities.models.ModelPillager;
import de.julianweinelt.ubm.items.ModItems;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class RenderPillager extends RenderLiving<EntityPillager> {

    public RenderPillager(RenderManager manager) {
        super(manager, new ModelPillager(), 0.5f);

        //this.addLayer(new LayerHeldItem(this));

        this.addLayer(new LayerCustomHead(((ModelPillager)this.mainModel).head));
    }

    @Nullable
    @Override
    protected ResourceLocation getEntityTexture(@Nonnull EntityPillager entity) {
        return new ResourceLocation("ubm:textures/entity/illager/pillager.png");
    }

    @Override
    protected void applyRotations(@Nonnull EntityPillager entityLiving, float ageInTicks,
                                  float rotationYaw, float partialTicks) {
        super.applyRotations(entityLiving, ageInTicks, rotationYaw, partialTicks);

        ModelPillager model = (ModelPillager) this.mainModel;
        ItemStack mainHand = entityLiving.getHeldItemMainhand();

        if (mainHand.getItem() == ModItems.CROSSBOW && entityLiving.isHandActive()) {
            model.RightArm.rotateAngleX = -1.5F;
            model.RightArm.rotateAngleY = 0F;
        }
    }
}

