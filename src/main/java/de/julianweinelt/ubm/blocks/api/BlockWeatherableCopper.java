package de.julianweinelt.ubm.blocks.api;

import de.julianweinelt.ubm.items.ModItems;
import de.julianweinelt.ubm.misc.AdvancementHelper;
import de.julianweinelt.ubm.misc.ModSounds;
import de.julianweinelt.ubm.particles.ParticleWaxOn;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Random;

public abstract class BlockWeatherableCopper extends Block implements IWeatherable {

    public BlockWeatherableCopper(Material material) {
        super(material);
        setSoundType(SoundType.METAL);
        setHardness(3.0f);
        setResistance(6.0f);
    }

    @Override
    public boolean onBlockActivated(@Nonnull World world, @Nonnull BlockPos pos,
                                    @Nonnull IBlockState state, EntityPlayer player,
                                    @Nonnull EnumHand hand, @Nonnull EnumFacing facing,
                                    float hitX, float hitY, float hitZ) {
        ItemStack held = player.getHeldItem(hand);
        if (held.getItem() instanceof ItemAxe) {
            return getUnweatheredVariant().map(prev -> {
                if (!world.isRemote) {
                    world.setBlockState(pos, prev.getDefaultState());
                    world.playSound(null, pos, ModSounds.ITEM_AXE_SCRAPE,
                            SoundCategory.BLOCKS, 1.0f, 1.0f);
                    player.swingArm(hand);

                    if (this instanceof BlockCopperBulb) {
                        AdvancementHelper.grantAdvancement(player, "lighten_up");
                    }
                    if (!player.capabilities.isCreativeMode)
                        held.damageItem(1, player);
                }
                return true;
            }).orElse(false);
        } else if (held.getItem().equals(ModItems.HONEYCOMB)) {
            return getWaxedVariant().map(waxed -> {
                if (!world.isRemote) {
                    double x = pos.getX();
                    double y = pos.getY();
                    double z = pos.getZ();

                    Random rand = new Random();
                    AdvancementHelper.grantAdvancement(player, "wax_on");

                    for (int i = 0; i < 10; i++) {
                        double offsetX = (rand.nextDouble() - 0.5D) * 0.3D;
                        double offsetZ = (rand.nextDouble() - 0.5D) * 0.3D;

                        Minecraft.getMinecraft().effectRenderer.addEffect(
                                new ParticleWaxOn(
                                        world, x + offsetX, y,z + offsetZ, 0.0D, 0.0D, 0.0D
                                )
                        );
                    }

                    world.setBlockState(pos, waxed.getDefaultState());
                    world.playSound(null, pos, ModSounds.ITEM_HONEYCOMB_WAX_ON,
                            SoundCategory.BLOCKS, 1.0f, 1.0f);
                    player.swingArm(hand);
                    if (!player.capabilities.isCreativeMode)
                        held.shrink(1);
                }
                return true;
            }).orElse(false);
        }
        return false;
    }
}