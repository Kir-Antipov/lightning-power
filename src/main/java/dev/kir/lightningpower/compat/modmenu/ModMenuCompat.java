package dev.kir.lightningpower.compat.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.kir.lightningpower.compat.cloth.LightningPowerClothConfig;
import me.shedaniel.autoconfig.AutoConfig;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;

@Environment(EnvType.CLIENT)
public final class ModMenuCompat implements ModMenuApi {
    private static final boolean IS_CLOTH_LOADED = FabricLoader.getInstance().isModLoaded("cloth-config");

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (!IS_CLOTH_LOADED) {
            return parent -> null;
        }

        return parent -> AutoConfig.getConfigScreen(LightningPowerClothConfig.class, parent).get();
    }
}
