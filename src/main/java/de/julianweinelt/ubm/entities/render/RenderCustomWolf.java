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
    private static final ResourceLocation[] WOLF_TEXTURES_ANGRY = new ResourceLocation[]{
            new ResourceLocation("ubm:textures/entity/wolf/wolf_ashen_angry.png"),
            new ResourceLocation("ubm:textures/entity/wolf/wolf_black_angry.png"),
            new ResourceLocation("ubm:textures/entity/wolf/wolf_chestnut_angry.png"),
            new ResourceLocation("ubm:textures/entity/wolf/wolf_rusty_angry.png"),
            new ResourceLocation("ubm:textures/entity/wolf/wolf_snowy_angry.png"),
            new ResourceLocation("ubm:textures/entity/wolf/wolf_spotted_angry.png"),
            new ResourceLocation("ubm:textures/entity/wolf/wolf_woods_angry.png")
    };
    private static final ResourceLocation[] WOLF_TEXTURES_TAME = new ResourceLocation[]{
            new ResourceLocation("ubm:textures/entity/wolf/wolf_ashen_tame.png"),
            new ResourceLocation("ubm:textures/entity/wolf/wolf_black_tame.png"),
            new ResourceLocation("ubm:textures/entity/wolf/wolf_chestnut_tame.png"),
            new ResourceLocation("ubm:textures/entity/wolf/wolf_rusty_tame.png"),
            new ResourceLocation("ubm:textures/entity/wolf/wolf_snowy_tame.png"),
            new ResourceLocation("ubm:textures/entity/wolf/wolf_spotted_tame.png"),
            new ResourceLocation("ubm:textures/entity/wolf/wolf_woods_tame.png")
    };

    public RenderCustomWolf(RenderManager renderManagerIn) {
        super(renderManagerIn);
        this.addLayer(new LayerWolfArmor(this));
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityWolf entity) {
        if (entity instanceof EntityCustomWolf) {
            int variant = ((EntityCustomWolf) entity).getTextureVariant();
            if (entity.isTamed()) {
                return WOLF_TEXTURES_TAME[variant];
            }
            if (entity.isAngry()) {
                return WOLF_TEXTURES_ANGRY[variant];
            }
            return WOLF_TEXTURES[variant];
        }
        return super.getEntityTexture(entity);
    }
}
