package de.julianweinelt.ubm.entities.render;

import de.julianweinelt.ubm.entities.EntityAxolotl;
import de.julianweinelt.ubm.entities.models.ModelAxolotl;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAxolotl extends RenderLiving<EntityAxolotl> {

    public RenderAxolotl(RenderManager rendermanagerIn) {
        super(rendermanagerIn, new ModelAxolotl(), 0.4F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityAxolotl entity) {
        return entity.getTexture();
    }
}
