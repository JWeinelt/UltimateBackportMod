package de.julianweinelt.ubm.items;

import de.julianweinelt.ubm.misc.ModCreativeTabs;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public class ItemPotterySherd extends Item {

    public enum SherdType {
        ANGLER(0, "angler"),
        ARCHER(1, "archer"),
        ARMS_UP(2, "arms_up"),
        BLADE(3, "blade"),
        BREWER(4, "brewer"),
        BURN(5, "burn"),
        DANGER(6, "danger"),
        EMPTY(7, "empty"),
        EXPLORER(8, "explorer"),
        FLOW(9, "flow"),
        FRIEND(10, "friend"),
        GUSTER(11, "guster"),
        HEART(12, "heart"),
        HEARTBREAK(13, "heartbreak"),
        HOWL(14, "howl"),
        MINER(15, "miner"),
        MOURNER(16, "mourner"),
        PLENTY(17, "plenty"),
        PRIZE(18, "prize"),
        SCRAPE(19, "scrape"),
        SHEAF(20, "sheaf"),
        SHELTER(21, "shelter"),
        SKULL(22, "skull"),
        SNORT(23, "snort"),
        UNKNOWN(255, "unknown");

        private static final SherdType[] META_LOOKUP = new SherdType[values().length];
        private final int id;
        private final String name;

        SherdType(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public static SherdType byId(int id) {
            for (SherdType type : values()) {
                if (type.id == id) return type;
            }
            return UNKNOWN;
        }
    }

    public ItemPotterySherd() {
        setRegistryName("pottery_sherd");
        setUnlocalizedName("pottery_sherd");
        setCreativeTab(ModCreativeTabs.UBM_TAB_TRAILS_TALES);
        setHasSubtypes(true);
    }

    @Override
    public int getMetadata(int damage) {
        return damage;
    }

    @Override
    @Nonnull
    public String getUnlocalizedName(ItemStack stack) {
        return super.getUnlocalizedName() + "." + SherdType.byId(stack.getMetadata()).getName();
    }

    public int getSherdId(ItemStack stack) {
        return SherdType.byId(stack.getMetadata()).getId();
    }

    @Override
    public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> items) {
        if (isInCreativeTab(tab)) {
            for (SherdType type : SherdType.values()) {
                if (type != SherdType.UNKNOWN)
                    items.add(new ItemStack(this, 1, type.getId()));
            }
        }
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, @Nullable ITooltipFlag flagIn) {
        SherdType type = SherdType.byId(stack.getMetadata());
        tooltip.add(TextFormatting.GRAY + I18n.translateToLocal("tooltip.ubm.sherd." + type.getName()));
    }
}
