package fuzs.barteringstation.common.client;

import fuzs.barteringstation.common.client.gui.screens.inventory.BarteringStationScreen;
import fuzs.barteringstation.common.client.renderer.blockentity.BarteringStationRenderer;
import fuzs.barteringstation.common.init.ModRegistry;
import fuzs.puzzleslib.common.api.client.core.v1.ClientModConstructor;
import fuzs.puzzleslib.common.api.client.core.v1.context.BlockEntityRenderersContext;
import fuzs.puzzleslib.common.api.client.core.v1.context.MenuScreensContext;

public class BarteringStationClient implements ClientModConstructor {

    @Override
    public void onRegisterMenuScreens(MenuScreensContext context) {
        context.registerMenuScreen(ModRegistry.BARTERING_STATION_MENU_TYPE.value(), BarteringStationScreen::new);
    }

    @Override
    public void onRegisterBlockEntityRenderers(BlockEntityRenderersContext context) {
        context.registerBlockEntityRenderer(ModRegistry.BARTERING_STATION_BLOCK_ENTITY_TYPE.value(), BarteringStationRenderer::new);
    }
}
