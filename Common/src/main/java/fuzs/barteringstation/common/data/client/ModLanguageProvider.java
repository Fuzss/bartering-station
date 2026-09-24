package fuzs.barteringstation.common.data.client;

import fuzs.barteringstation.common.init.ModRegistry;
import fuzs.barteringstation.common.world.level.block.entity.BarteringStationBlockEntity;
import fuzs.puzzleslib.common.api.client.data.v3.language.AbstractLanguageProvider;
import fuzs.puzzleslib.common.api.data.v3.core.DataProviderContext;

public class ModLanguageProvider extends AbstractLanguageProvider {

    public ModLanguageProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addTranslations() {
        this.add(ModRegistry.BARTERING_STATION_BLOCK.value(), "Bartering Station");
        this.add(BarteringStationBlockEntity.CONTAINER_BARTERING_STATION, "Bartering Station");
    }
}
