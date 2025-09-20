package de.julianweinelt.ubm.items;

import de.julianweinelt.ubm.blocks.interactable.smithing.IArmorTrim;
import net.minecraft.item.Item;

public class ItemArmorTrim extends Item implements IArmorTrim {
    private final String armorTrim;

    public ItemArmorTrim(String armorTrim) {
        this.armorTrim = armorTrim;
    }

    public String getArmorTrim() {
        return armorTrim;
    }
}
