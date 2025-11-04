package de.julianweinelt.ubm.entities.render;

import de.julianweinelt.ubm.entities.EntityTropicalFish;
import de.julianweinelt.ubm.entities.models.ModelTropicalFishA;
import de.julianweinelt.ubm.entities.models.ModelTropicalFishB;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderTropicalFish extends RenderLiving<EntityTropicalFish> {
    private static final ResourceLocation MODEL_A_TEXTURE = new ResourceLocation("ubm:textures/entity/fish/tropical_a.png");
    private static final ResourceLocation MODEL_B_TEXTURE = new ResourceLocation("ubm:textures/entity/fish/tropical_b.png");

    private static final ResourceLocation[] PATTERN_TEXTURES_A = new ResourceLocation[] {
            new ResourceLocation("ubm:textures/entity/fish/tropical_a_pattern_1.png"),
            new ResourceLocation("ubm:textures/entity/fish/tropical_a_pattern_2.png"),
            new ResourceLocation("ubm:textures/entity/fish/tropical_a_pattern_3.png"),
            new ResourceLocation("ubm:textures/entity/fish/tropical_a_pattern_4.png"),
            new ResourceLocation("ubm:textures/entity/fish/tropical_a_pattern_5.png"),
            new ResourceLocation("ubm:textures/entity/fish/tropical_a_pattern_6.png")
    };

    private static final ResourceLocation[] PATTERN_TEXTURES_B = new ResourceLocation[] {
            new ResourceLocation("ubm:textures/entity/fish/tropical_b_pattern_1.png"),
            new ResourceLocation("ubm:textures/entity/fish/tropical_b_pattern_2.png"),
            new ResourceLocation("ubm:textures/entity/fish/tropical_b_pattern_3.png"),
            new ResourceLocation("ubm:textures/entity/fish/tropical_b_pattern_4.png"),
            new ResourceLocation("ubm:textures/entity/fish/tropical_b_pattern_5.png"),
            new ResourceLocation("ubm:textures/entity/fish/tropical_b_pattern_6.png")
    };

    private final ModelBase modelA = new ModelTropicalFishA();
    private final ModelBase modelB = new ModelTropicalFishB();

    public RenderTropicalFish(RenderManager renderManager) {
        super(renderManager, new ModelTropicalFishA(), 0.2F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityTropicalFish entity) {
        return entity.getFishModel() == 0 ? MODEL_A_TEXTURE : MODEL_B_TEXTURE;
    }

    @Override
    public void doRender(EntityTropicalFish entity, double x, double y, double z, float entityYaw, float partialTicks) {
        this.mainModel = (entity.getFishModel() == 0 ? modelA : modelB);
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    @Override
    protected int getColorMultiplier(EntityTropicalFish entity, float lightBrightness, float partialTickTime) {
        return entity.getBaseColor().getRGB();
    }

    @Override
    protected void renderLayers(EntityTropicalFish entity, float limbSwing, float limbSwingAmount,
                                float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
        super.renderLayers(entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, scale);

        int bColor = entity.getBaseColor().getRGB();
        float rB = ((bColor >> 16) & 255) / 255.0F;
        float gB = ((bColor >> 8) & 255) / 255.0F;
        float bB = (bColor & 255) / 255.0F;

        GlStateManager.color(rB, gB, bB);

        int patternIndex = entity.getPattern();
        ResourceLocation[] PATTERN_TEXTURES = (entity.getFishModel() == 0) ? PATTERN_TEXTURES_A :  PATTERN_TEXTURES_B;

        if (patternIndex >= 0 && patternIndex < PATTERN_TEXTURES.length) {
            this.bindTexture(PATTERN_TEXTURES[patternIndex]);

            int color = entity.getPatternColor().getRGB();
            float r = ((color >> 16) & 255) / 255.0F;
            float g = ((color >> 8) & 255) / 255.0F;
            float b = (color & 255) / 255.0F;

            GlStateManager.color(r, g, b);
            GlStateManager.enableBlend();
            this.mainModel.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GlStateManager.disableBlend();
            GlStateManager.color(1F, 1F, 1F);
        }
    }
}
