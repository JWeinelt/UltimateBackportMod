package de.julianweinelt.ubm.trims;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class LayerArmorTrim extends LayerBipedArmor {
    private static final Map<String, Color[]> MATERIAL_TO_PALETTE = new HashMap<>();
    static {
        MATERIAL_TO_PALETTE.put("iron", new Color[]{
                new Color(0xD8D8D8), new Color(0xC0C0C0), new Color(0xA0A0A0),
                new Color(0x808080), new Color(0x606060), new Color(0x404040),
                new Color(0x202020), new Color(0x000000)
        });

        MATERIAL_TO_PALETTE.put("gold", new Color[]{
                new Color(0xFFF000), new Color(0xFFD000), new Color(0xFFB000),
                new Color(0xFF9000), new Color(0xFF7000), new Color(0xFF5000),
                new Color(0xFF3000), new Color(0xFF1000)
        });
    }


    private final RenderLivingBase<?> renderer;

    public LayerArmorTrim(RenderLivingBase<?> renderer) {
        super(renderer);
        this.renderer = renderer;
    }

    @Override
    protected void initArmor() {
        this.modelLeggings = new ModelBiped(0.5F);
        this.modelArmor = new ModelBiped(1.0F);
    }

    @Override
    public void doRenderLayer(@Nonnull EntityLivingBase entity, float limbSwing, float limbSwingAmount,
                              float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {

        for (EntityEquipmentSlot slot : EntityEquipmentSlot.values()) {
            if (slot.getSlotType() != EntityEquipmentSlot.Type.ARMOR) continue;

            ItemStack stack = entity.getItemStackFromSlot(slot);
            if (stack.isEmpty() || !stack.hasTagCompound()) continue;

            NBTTagCompound trim = stack.getSubCompound("Trim");
            if (trim == null) continue;

            String pattern = trim.getString("pattern");
            String material = trim.getString("material");

            String overlayPath = "textures/equipment/troms/entity/humanoid/" + pattern + ".png";
            Color[] palette = MATERIAL_TO_PALETTE.get(material);
            if (palette == null) continue;

            // Modell auswählen
            ModelBiped model = slot == EntityEquipmentSlot.LEGS ? this.modelLeggings : this.modelArmor;
            model.setModelAttributes(this.renderer.getMainModel());

            // Sichtbarkeit passend zum Slot setzen (wie vorher)
            setupVisibilityForSlot(model, slot);

            // Dynamische Textur färben
            ResourceLocation coloredTrim = TrimColorHelper.createColoredTrim(overlayPath, palette);
            if (coloredTrim == null) continue;

            // Rendern
            GlStateManager.pushMatrix();
            this.renderer.bindTexture(coloredTrim);
            model.render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);
            GlStateManager.popMatrix();
        }
    }
    private static final Map<String, ResourceLocation> TRIM_CACHE = new HashMap<>();

    private ResourceLocation getOrCreateTrim(String pattern, String material) {
        String key = pattern + "_" + material;
        if (TRIM_CACHE.containsKey(key)) return TRIM_CACHE.get(key);

        String overlayPath = "textures/equipment/troms/entity/humanoid/" + pattern + ".png";
        Color[] palette = MATERIAL_TO_PALETTE.get(material);
        if (palette == null) return null;

        ResourceLocation res = TrimColorHelper.createColoredTrim(overlayPath, palette);
        TRIM_CACHE.put(key, res);
        return res;
    }

    private void setupVisibilityForSlot(ModelBiped model, EntityEquipmentSlot slot) {
        // Alles erstmal unsichtbar machen
        model.bipedHead.showModel = false;
        model.bipedHeadwear.showModel = false;
        model.bipedBody.showModel = false;
        model.bipedRightArm.showModel = false;
        model.bipedLeftArm.showModel = false;
        model.bipedRightLeg.showModel = false;
        model.bipedLeftLeg.showModel = false;

        // Slot-spezifisch aktivieren
        switch (slot) {
            case HEAD:
                model.bipedHead.showModel = true;
                model.bipedHeadwear.showModel = true;
                break;
            case CHEST:
                model.bipedBody.showModel = true;
                model.bipedRightArm.showModel = true;
                model.bipedLeftArm.showModel = true;
                break;
            case LEGS:
                model.bipedRightLeg.showModel = true;
                model.bipedLeftLeg.showModel = true;
                break;
            case FEET:
                model.bipedRightLeg.showModel = true;
                model.bipedLeftLeg.showModel = true;
                break;
            default:
                break;
        }
    }




}
