package de.julianweinelt.ubm.entities.custom;

import de.julianweinelt.ubm.entities.sync.NetworkHandler;
import de.julianweinelt.ubm.entities.sync.PacketSyncTrades;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.INpc;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class EntityNewVillager extends EntityCreature implements INpc {
    private static final DataParameter<Boolean> CHILD =
            EntityDataManager.createKey(EntityNewVillager.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> PROFESSION = 
            EntityDataManager.createKey(EntityNewVillager.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> BIOME =
            EntityDataManager.createKey(EntityNewVillager.class, DataSerializers.VARINT);

    private static final DataParameter<Integer> WORK_BLOCK_X =
            EntityDataManager.createKey(EntityNewVillager.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> WORK_BLOCK_Y =
            EntityDataManager.createKey(EntityNewVillager.class, DataSerializers.VARINT);
    private static final DataParameter<Integer> WORK_BLOCK_Z =
            EntityDataManager.createKey(EntityNewVillager.class, DataSerializers.VARINT);

    private static final DataParameter<Boolean> HAS_HOME =
            EntityDataManager.createKey(EntityNewVillager.class, DataSerializers.BOOLEAN);

    private static final DataParameter<Integer> PROFESSION_LEVEL =
            EntityDataManager.createKey(EntityNewVillager.class, DataSerializers.VARINT);

    private List<MerchantRecipe> trades;

    public EntityNewVillager(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void entityInit() {
        super.entityInit();

        this.dataManager.register(CHILD, false);
        this.dataManager.register(PROFESSION, 0);
        this.dataManager.register(BIOME, 0);
        this.dataManager.register(PROFESSION_LEVEL, 0);
        this.dataManager.register(WORK_BLOCK_X, 0);
        this.dataManager.register(WORK_BLOCK_Y, 0);
        this.dataManager.register(WORK_BLOCK_Z, 0);
        this.dataManager.register(HAS_HOME, false);
    }

    @Override
    protected void initEntityAI() {
        super.initEntityAI();

        this.tasks.addTask(0, new EntityAIPanic(this, 1.5F));
        this.tasks.addTask(1, new EntityAILookIdle(this));
        this.tasks.addTask(1, new EntityAIWander(this, 1F));
        this.tasks.addTask(2, new EntityAIOpenDoor(this, true));
        this.tasks.addTask(2, new EntityAIMoveIndoors(this));
        this.tasks.addTask(3, new EntityAIMoveThroughVillage(this, 1, false));
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_VILLAGER_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_VILLAGER_DEATH;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(@Nonnull DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_VILLAGER_HURT;
    }

    @Override
    public boolean processInteract(@Nonnull EntityPlayer player, @Nonnull EnumHand hand) {
        if (!world.isRemote) {
            if (isChild()) return false;
            NetworkHandler.INSTANCE.sendTo(new PacketSyncTrades(this), (EntityPlayerMP) player);
        }
        return true;
    }

    @Nullable
    @Override
    public IEntityLivingData onInitialSpawn(@Nonnull DifficultyInstance difficulty,
                                            @Nullable IEntityLivingData livingData) {
        initDefaultTrades();
        return super.onInitialSpawn(difficulty, livingData);
    }

    @Override
    public void writeEntityToNBT(@Nonnull NBTTagCompound compound) {
        super.writeEntityToNBT(compound);

        BlockPos home = getHome();
        if (home != null) {
            compound.setBoolean("HasHome", true);
            compound.setInteger("HomeX", home.getX());
            compound.setInteger("HomeY", home.getY());
            compound.setInteger("HomeZ", home.getZ());
        } else {
            compound.setBoolean("HasHome", false);
        }

        VillagerProfession profession = getProfession();
        compound.setInteger("ProfessionID", profession.ordinal());
        compound.setString("ProfessionName", profession.name());
        VillagerBiome biome = getBiome();
        compound.setInteger("BiomeID", biome.ordinal());
        compound.setString("BiomeName", biome.name());
        compound.setInteger("ProfessionLevel", getProfessionLevel());

        if (trades != null && !trades.isEmpty()) {
            NBTTagList tradeList = new NBTTagList();
            for (MerchantRecipe recipe : trades) {
                tradeList.appendTag(recipe.writeToTags());
            }
            compound.setTag("Trades", tradeList);
        }
    }

    @Override
    public void readEntityFromNBT(@Nonnull NBTTagCompound compound) {
        super.readEntityFromNBT(compound);

        if (compound.getBoolean("HasHome")) {
            int x = compound.getInteger("HomeX");
            int y = compound.getInteger("HomeY");
            int z = compound.getInteger("HomeZ");
            setHome(new BlockPos(x, y, z));
        } else {
            setHome(null);
        }

        if (compound.hasKey("ProfessionID")) {
            int id = compound.getInteger("ProfessionID");
            if (id >= 0 && id < VillagerProfession.values().length) {
                setProfession(VillagerProfession.values()[id]);
            } else {
                setProfession(VillagerProfession.UNEMPLOYED);
            }
        } else if (compound.hasKey("ProfessionName")) {
            try {
                setProfession(VillagerProfession.valueOf(compound.getString("ProfessionName")));
            } catch (IllegalArgumentException ex) {
                setProfession(VillagerProfession.UNEMPLOYED);
            }
        }

        if (compound.hasKey("BiomeID")) {
            int id = compound.getInteger("BiomeID");
            if (id >= 0 && id < VillagerBiome.values().length) {
                setBiome(VillagerBiome.values()[id]);
            } else {
                setBiome(VillagerBiome.PLAINS);
            }
        } else if (compound.hasKey("BiomeName")) {
            try {
                setBiome(VillagerBiome.valueOf(compound.getString("BiomeName")));
            } catch (IllegalArgumentException ex) {
                setBiome(VillagerBiome.PLAINS);
            }
        }

        if (compound.hasKey("ProfessionLevel")) {
            setProfessionLevel(compound.getInteger("ProfessionLevel"));
        } else setProfessionLevel(0);


        trades = new ArrayList<>();
        if (compound.hasKey("Trades", 9)) {
            NBTTagList tradeList = compound.getTagList("Trades", 10);
            for (int i = 0; i < tradeList.tagCount(); i++) {
                NBTTagCompound recipeNBT = tradeList.getCompoundTagAt(i);
                MerchantRecipe recipe = new MerchantRecipe(recipeNBT);
                trades.add(recipe);
            }
        }
    }

    public void initDefaultTrades() {
        trades = new ArrayList<>();

        trades.add(new MerchantRecipe(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(Items.BREAD, 5)
        ));

        trades.add(new MerchantRecipe(
                new ItemStack(Items.EMERALD, 3),
                new ItemStack(Items.IRON_SWORD, 1)
        ));

        trades.add(new MerchantRecipe(
                new ItemStack(Items.EMERALD, 1),
                new ItemStack(Items.ARROW, 16)
        ));
    }



    public List<MerchantRecipe> getTrades() {
        return trades;
    }

    public void setTrades(List<MerchantRecipe> trades) {
        this.trades = trades;
    }

    public void setProfession(VillagerProfession profession) {
        this.dataManager.set(PROFESSION, profession.ordinal());
    }

    public VillagerProfession getProfession() {
        int id = this.dataManager.get(PROFESSION);
        return VillagerProfession.values()[Math.max(0, Math.min(id, VillagerProfession.values().length - 1))];
    }

    public void setBiome(VillagerBiome biome) {
        this.dataManager.set(BIOME, biome.ordinal());
    }

    public VillagerBiome getBiome() {
        int id = this.dataManager.get(BIOME);
        return VillagerBiome.values()[Math.max(0, Math.min(id, VillagerBiome.values().length - 1))];
    }

    public void setProfessionLevel(int level) {
        this.dataManager.set(PROFESSION_LEVEL, level);
    }
    public int getProfessionLevel() {
        return this.dataManager.get(PROFESSION_LEVEL);
    }

    public boolean isChild() {
        return this.getDataManager().get(CHILD);
    }

    public void setHome(@Nullable BlockPos pos) {
        if (pos == null) {
            this.dataManager.set(HAS_HOME, false);
            this.dataManager.set(WORK_BLOCK_X, 0);
            this.dataManager.set(WORK_BLOCK_Y, 0);
            this.dataManager.set(WORK_BLOCK_Z, 0);
        } else {
            this.dataManager.set(HAS_HOME, true);
            this.dataManager.set(WORK_BLOCK_X, pos.getX());
            this.dataManager.set(WORK_BLOCK_Y, pos.getY());
            this.dataManager.set(WORK_BLOCK_Z, pos.getZ());
        }
    }

    @Nullable
    public BlockPos getHome() {
        if (!this.dataManager.get(HAS_HOME)) {
            return null;
        }
        return new BlockPos(
                this.dataManager.get(WORK_BLOCK_X),
                this.dataManager.get(WORK_BLOCK_Y),
                this.dataManager.get(WORK_BLOCK_Z)
        );
    }

    public enum VillagerProfession {
        UNEMPLOYED,
        NITWIT,
        ARMORER,
        BUTCHER,
        CARTOGRAPHER,
        CLERIC,
        FARMER,
        FISHERMAN,
        FLETCHER,
        LEATHERWORKER,
        LIBRARIAN,
        MASON,
        SHEPHERD,
        TOOLSMITH,
        WEAPONSMITH
    }

    public enum VillagerBiome {
        DESERT,
        JUNGLE,
        PLAINS,
        SAVANNA,
        SNOW,
        SWAMP,
        TAIGA
    }
}