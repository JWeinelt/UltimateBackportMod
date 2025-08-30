package de.julianweinelt.ubm.entities.render;

import de.julianweinelt.ubm.entities.custom.EntityCustomWolf;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.ResourceLocation;

public class RenderCustomWolf extends RenderWolf {
    private static final ResourceLocation[] WOLF_TEXTURES = new ResourceLocation[]{
            new ResourceLocation("ubm:textures/entity/wolf/wolf_ashen.png"),
            new ResourceLocation("ubm:textures/entity/wolf/wolf_black.png"),
            new ResourceLocation("ubm:textures/entity/wolf/wolf_chestnut.png"),
            new ResourceLocation("ubm:textures/entity/wolf/wolf_rusty.png"),
            new ResourceLocation("ubm:textures/entity/wolf/wolf_snowy.png"),
            new ResourceLocation("ubm:textures/entity/wolf/wolf_spotted.png"),
            new ResourceLocation("ubm:textures/entity/wolf/wolf_woods.png")
    };

    public RenderCustomWolf(RenderManager renderManagerIn) {
        super(renderManagerIn);
        this.addLayer(new LayerWolfArmor(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityWolf entity) {
        if (entity instanceof EntityCustomWolf) {
            int variant = ((EntityCustomWolf) entity).getTextureVariant();
            return WOLF_TEXTURES[variant];
        }
        return super.getEntityTexture(entity);
    }
}
