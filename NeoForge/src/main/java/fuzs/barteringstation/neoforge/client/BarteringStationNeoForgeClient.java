package fuzs.barteringstation.neoforge.client;

import fuzs.barteringstation.common.BarteringStation;
import fuzs.barteringstation.common.client.BarteringStationClient;
import fuzs.barteringstation.common.data.client.ModLanguageProvider;
import fuzs.barteringstation.common.data.client.ModModelProvider;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.neoforge.api.data.v2.core.DataProviderHelper;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = BarteringStation.MOD_ID, dist = Dist.CLIENT)
public class BarteringStationNeoForgeClient {

    public BarteringStationNeoForgeClient() {
        ClientModConstructor.construct(BarteringStation.MOD_ID, BarteringStationClient::new);
        DataProviderHelper.registerDataProviders(BarteringStation.MOD_ID, ModLanguageProvider::new,
                ModModelProvider::new
        );
    }
}
