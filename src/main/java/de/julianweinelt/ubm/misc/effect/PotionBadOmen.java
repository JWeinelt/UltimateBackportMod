package de.julianweinelt.ubm.misc.effect;

import net.minecraft.client.Minecraft;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionBadOmen extends Potion {
    protected PotionBadOmen() {
        super(true, 0x000000);

        this.setPotionName("effect.bad_omen");
        this.setRegistryName("bad_omen");
        this.setIconIndex(0, 0);
    }

    @Override
    public boolean hasStatusIcon() {
        Minecraft.getMinecraft().getTextureManager().bindTexture(
                new ResourceLocation("ubm", "textures/effects/bad_omen.png")
        );
        return true;
    }
}