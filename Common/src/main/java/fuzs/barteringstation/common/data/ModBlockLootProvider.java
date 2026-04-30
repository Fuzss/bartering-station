package fuzs.barteringstation.common.data;

import fuzs.barteringstation.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v2.AbstractLootProvider;
import fuzs.puzzleslib.common.api.data.v2.core.DataProviderContext;

public class ModBlockLootProvider extends AbstractLootProvider.Blocks {

    public ModBlockLootProvider(DataProviderContext context) {
        super(context);
    }

    @Override
    public void addLootTables() {
        this.add(ModRegistry.BARTERING_STATION_BLOCK.value(), this::createNameableBlockEntityTable);
    }
}
