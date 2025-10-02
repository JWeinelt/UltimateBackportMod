package de.julianweinelt.ubm.blocks.api;

import net.minecraft.block.BlockPane;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.creativetab.CreativeTabs;

public class BlockModBars extends BlockPane {

    public BlockModBars(String name, Material material) {
        super(material, true);

        this.setRegistryName(name);
        this.setUnlocalizedName(name);
        this.setCreativeTab(CreativeTabs.DECORATIONS);

        this.setSoundType(SoundType.METAL);
        this.setHardness(5.0F);
        this.setResistance(10.0F);
    }
}
