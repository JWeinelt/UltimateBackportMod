package de.julianweinelt.ubm.core;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

import javax.annotation.Nullable;
import java.util.Map;

@IFMLLoadingPlugin.MCVersion("1.12.2")
public class UBMCore implements IFMLLoadingPlugin {
    @Override
    public String[] getASMTransformerClass() {
        return new String[]{"de.julianweinelt.ubm.core.ChunkTransformer"};
    }

    @Override
    public String getModContainerClass() {
        return "";
    }

    @Nullable
    @Override
    public String getSetupClass() {
        return "";
    }

    @Override
    public void injectData(Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass() {
        return "";
    }
}
