package de.julianweinelt.ubm.entities;

import de.julianweinelt.ubm.configuration.ModConfig;
import de.julianweinelt.ubm.entities.ai.EntityAISwimAround;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.security.SecureRandom;
import java.util.Random;

public class EntitySalmon extends EntityWaterMob {
    private float scale;

    public EntitySalmon(World worldIn) {
        super(worldIn);
        this.setSize(0.5F, 0.5F);
        scale = new Random().nextFloat() * 1.8F + 0.3F;
    }

    public float getScale() {
        return scale;
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimAround(this, 1.0D));
    }


    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();

        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ModConfig.getEntityConfig("salmon").getHealth() / 2.0D);

        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(1.0D);
    }

    @Override
    @Nonnull
    protected PathNavigate createNavigator(@Nonnull World worldIn) {
        return new PathNavigateSwimmer(this, worldIn);
    }


    @Override
    public void onLivingUpdate() {
        super.onLivingUpdate();

        if (this.isInWater()) {
            this.motionY += 0.02D;
        } else if (this.onGround) {
            this.motionY += 0.3D;
            this.motionX += (this.rand.nextDouble() - 0.5D) * 0.4D;
            this.motionZ += (this.rand.nextDouble() - 0.5D) * 0.4D;
        }
    }

    @Nullable
    @Override
    protected Item getDropItem() {
        return Items.FISH;
    }

    @Override
    public boolean getCanSpawnHere() {
        return this.isInWater() && super.getCanSpawnHere();
    }
}
