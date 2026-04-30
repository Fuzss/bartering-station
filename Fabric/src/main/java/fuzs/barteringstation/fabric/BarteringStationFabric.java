package fuzs.barteringstation.fabric;

import fuzs.barteringstation.common.BarteringStation;
import fuzs.puzzleslib.common.api.core.v1.ModConstructor;
import net.fabricmc.api.ModInitializer;

public class BarteringStationFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        ModConstructor.construct(BarteringStation.MOD_ID, BarteringStation::new);
    }
}
