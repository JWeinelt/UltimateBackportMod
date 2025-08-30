package de.julianweinelt.ubm.entities.render;

import de.julianweinelt.ubm.entities.EntityGlowSquid;
import de.julianweinelt.ubm.entities.custom.EntityCustomWolf;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSquid;
import net.minecraft.client.renderer.entity.RenderWolf;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class RenderGlowSquid extends RenderSquid {

    public RenderGlowSquid(RenderManager renderManagerIn) {
        super(renderManagerIn);
    }

    @Override
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntitySquid entity) {
        if (entity instanceof EntityGlowSquid) {
            return new ResourceLocation("ubm:textures/entity/glow_squid.png");
        }
        return super.getEntityTexture(entity);
    }
}
