package de.julianweinelt.ubm.misc.texturesystem.rp;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.data.IMetadataSection;
import net.minecraft.client.resources.data.MetadataSerializer;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class MCResourcePack implements IResourcePack {

    private final File baseDir;
    private final String packName;

    public MCResourcePack(File baseDir, String packName) {
        this.baseDir = baseDir;
        this.packName = packName;
    }


    @Override
    @Nonnull
    public InputStream getInputStream(ResourceLocation location) throws IOException {
        File file = new File(baseDir, "assets/" + location.getResourceDomain() + "/" + location.getResourcePath());
        if (!file.exists()) {
            throw new FileNotFoundException(file.toString());
        }
        return Files.newInputStream(file.toPath());
    }

    @Override
    public boolean resourceExists(ResourceLocation location) {
        File file = new File(baseDir, "assets/" + location.getResourceDomain() + "/" + location.getResourcePath());
        return file.exists() && file.isFile();
    }

    @Override
    @Nonnull
    public Set<String> getResourceDomains() {
        Set<String> domains = new HashSet<>();
        domains.add("minecraft");
        domains.add("ubm");
        return domains;
    }

    @Nullable
    @Override
    public <T extends IMetadataSection> T getPackMetadata(@Nonnull MetadataSerializer metadataSerializer, @Nonnull String metadataSectionName) throws IOException {
        File meta = new File(baseDir, "pack.mcmeta");
        if (meta.exists()) {
            try (FileReader r = new FileReader(meta)) {
                return metadataSerializer.parseMetadataSection(metadataSectionName, Objects.requireNonNull(JsonUtils.fromJson(new Gson(), r, JsonObject.class)));
            }
        }
        return null;
    }

    @Override
    @Nonnull
    public BufferedImage getPackImage() throws IOException {
        File img = new File(baseDir, "pack.png");
        if (img.exists()) {
            return javax.imageio.ImageIO.read(img);
        }
        return new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB);
    }

    @Override
    @Nonnull
    public String getPackName() {
        return packName;
    }
}
