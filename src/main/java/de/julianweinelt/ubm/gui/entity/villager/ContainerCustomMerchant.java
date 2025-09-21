package de.julianweinelt.ubm.gui.entity.villager;

import net.minecraft.entity.IMerchant;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerCustomMerchant extends Container {
    private final IMerchant merchant;
    private final EntityPlayer player;
    private int selectedTrade;

    public ContainerCustomMerchant(EntityPlayer player, IMerchant merchant) {
        this.merchant = merchant;
        this.player = player;
        this.selectedTrade = 0;

        this.addSlotToContainer(new Slot(player.inventory, 0, 8, 84)); // Input 1
        this.addSlotToContainer(new Slot(player.inventory, 1, 30, 84)); // Input 2
        this.addSlotToContainer(new Slot(player.inventory, 2, 124, 84) { // Output
            @Override
            public boolean isItemValid(ItemStack stack) { return false; }

            @Override
            public void onSlotChange(ItemStack p_75220_1_, ItemStack p_75220_2_) {
                super.onSlotChange(p_75220_1_, p_75220_2_);
                ContainerCustomMerchant.this.onTradeTaken();
            }
        });
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.merchant.getCustomer() == playerIn;
    }

    public void setSelectedTrade(int index) {
        this.selectedTrade = index;
        // update output slot
    }

    private void onTradeTaken() {
        // Update MerchantRecipeList, Spieler Inventar etc.
    }
}
