package de.julianweinelt.ubm.items;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemSword;

public class NetheriteSword extends ItemSword {
    public NetheriteSword(ToolMaterial material) {
        super(material);
        this.setUnlocalizedName("netherite_sword");
        this.setRegistryName("netherite_sword");
        this.setCreativeTab(ModCreativeTabs.UBM_TAB_NETHER);
    }

    @Override
    public boolean onEntityItemUpdate(EntityItem entityItem) {
        if (entityItem.isBurning()) {
            entityItem.extinguish();
        }
        entityItem.setFire(0);
        return false;
    }
}
