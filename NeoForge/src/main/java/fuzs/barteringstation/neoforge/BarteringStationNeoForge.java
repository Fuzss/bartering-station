package fuzs.barteringstation.neoforge;

import fuzs.barteringstation.common.BarteringStation;
import fuzs.barteringstation.common.data.ModBlockLootProvider;
import fuzs.barteringstation.common.data.ModBlockTagProvider;
import fuzs.barteringstation.common.data.ModRecipeProvider;
import fuzs.barteringstation.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import fuzs.puzzleslib.neoforge.api.init.v3.capability.NeoForgeCapabilityHelper;
import net.neoforged.fml.common.Mod;

@Mod(BarteringStation.MOD_ID)
public class BarteringStationNeoForge {

    public BarteringStationNeoForge() {
        ModConstructor.construct(BarteringStation.MOD_ID, BarteringStation::new);
        NeoForgeCapabilityHelper.registerWorldlyBlockEntityContainer(ModRegistry.BARTERING_STATION_BLOCK_ENTITY_TYPE);
        DataProviderHelper.registerDataProviders(BarteringStation.MOD_ID, ModBlockLootProvider::new,
                ModBlockTagProvider::new, ModRecipeProvider::new
        );
    }
}
