package de.julianweinelt.ubm.entities;

import de.julianweinelt.ubm.configuration.ModConfig;
import de.julianweinelt.ubm.entities.ai.EntityAISwimAround;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityWaterMob;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathNavigateSwimmer;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.*;

public class EntityTropicalFish extends EntityWaterMob {
    private static final DataParameter<Integer> MODEL = EntityDataManager.createKey(EntityTropicalFish.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> BASE_COLOR = EntityDataManager.createKey(EntityTropicalFish.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> PATTERN = EntityDataManager.createKey(EntityTropicalFish.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> PATTERN_COLOR = EntityDataManager.createKey(EntityTropicalFish.class, DataSerializers.VARINT);

    public EntityTropicalFish(World worldIn) {
        super(worldIn);
        this.setSize(0.5F, 0.5F);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        this.dataManager.register(MODEL, rand.nextInt(1));
        this.dataManager.register(BASE_COLOR, getRandomDyeColor().getRGB());
        this.dataManager.register(PATTERN, rand.nextInt(6));
        this.dataManager.register(PATTERN_COLOR, getRandomDyeColor().getRGB());
    }

    @Override
    protected void initEntityAI() {
        this.tasks.addTask(0, new EntityAISwimAround(this, 1.0D));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(ModConfig.getEntityConfig("tropicalfish").getHealth() / 2.0D);
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

    public int getFishModel() {
        return this.dataManager.get(MODEL);
    }

    public void setFishModel(int model) {
        this.dataManager.set(MODEL, model);
    }

    public Color getBaseColor() {
        return new Color(this.dataManager.get(BASE_COLOR));
    }

    public void setBaseColor(Color color) {
        this.dataManager.set(BASE_COLOR, color.getRGB());
    }

    public int getPattern() {
        return this.dataManager.get(PATTERN);
    }

    public void setPattern(int pattern) {
        this.dataManager.set(PATTERN, pattern);
    }

    public Color getPatternColor() {
        return new Color(this.dataManager.get(PATTERN_COLOR));
    }

    public void setPatternColor(Color color) {
        this.dataManager.set(PATTERN_COLOR, color.getRGB());
    }

    @Override
    public void writeEntityToNBT(@Nonnull NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setInteger("Model", getFishModel());
        compound.setInteger("BaseColor", getBaseColor().getRGB());
        compound.setInteger("Pattern", getPattern());
        compound.setInteger("PatternColor", getPatternColor().getRGB());
    }

    @Override
    public void readEntityFromNBT(@Nonnull NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        setFishModel(compound.getInteger("Model"));
        setBaseColor(new Color(compound.getInteger("BaseColor")));
        setPattern(compound.getInteger("Pattern"));
        setPatternColor(new Color(compound.getInteger("PatternColor")));
    }

    @Override
    public IEntityLivingData onInitialSpawn(@Nonnull DifficultyInstance difficulty, @Nullable IEntityLivingData livingdata) {
        super.onInitialSpawn(difficulty, livingdata);

        setFishModel(rand.nextBoolean() ? 0 : 1);

        setBaseColor(getRandomDyeColor());

        setPattern(rand.nextInt(6));
        setPatternColor(getRandomDyeColor());
        return livingdata;
    }

    private Color getRandomDyeColor() {
        int[][] vanillaColors = {
                {255, 255, 255}, // White
                {216, 127, 51},  // Orange
                {178, 76, 216},  // Magenta
                {102, 153, 216}, // Light Blue
                {229, 229, 51},  // Yellow
                {127, 204, 25},  // Lime
                {242, 127, 165}, // Pink
                {76, 76, 76},    // Gray
                {153, 153, 153}, // Light Gray
                {76, 127, 153},  // Cyan
                {127, 63, 178},  // Purple
                {51, 76, 178},   // Blue
                {102, 76, 51},   // Brown
                {102, 127, 51},  // Green
                {153, 51, 51},   // Red
                {25, 25, 25}     // Black
        };
        int[] rgb = vanillaColors[rand.nextInt(vanillaColors.length)];
        return new Color(rgb[0], rgb[1], rgb[2]);
    }
}
