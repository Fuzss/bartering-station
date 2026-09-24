package fuzs.barteringstation.common.data;

import fuzs.barteringstation.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.data.v3.loot.AbstractBlockLootSubProvider;
import net.minecraft.data.loot.LootTableSubProvider;

public class ModBlockLootProvider extends AbstractBlockLootSubProvider {

    public ModBlockLootProvider(LootTableSubProvider.Context context) {
        super(context);
    }

    @Override
    public void generate() {
        this.add(ModRegistry.BARTERING_STATION_BLOCK.value(), this::createNameableBlockEntityTable);
    }
}
