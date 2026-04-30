package fuzs.barteringstation.common.client.gui.screens.inventory;

import fuzs.barteringstation.common.BarteringStation;
import fuzs.barteringstation.common.client.gui.components.ItemStackDisplayButton;
import fuzs.barteringstation.common.config.ClientConfig;
import fuzs.barteringstation.common.config.ServerConfig;
import fuzs.barteringstation.common.world.inventory.BarteringStationMenu;
import fuzs.barteringstation.common.world.level.block.entity.BarteringStationBlockEntity;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class BarteringStationScreen extends AbstractContainerScreen<BarteringStationMenu> {
    public static final Identifier BARTERING_STATION_LOCATION = BarteringStation.id(
            "textures/gui/container/bartering_station.png");
    private static final Identifier RIGHT_ARROW_SPRITE = BarteringStation.id("container/bartering_station/right_arrow");
    private static final Identifier LEFT_ARROW_SPRITE = BarteringStation.id("container/bartering_station/left_arrow");
    private static final int ARROW_SIZE_X = 24;
    private static final int ARROW_SIZE_Y = 18;

    public BarteringStationScreen(BarteringStationMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        this.titleLabelX = (this.imageWidth - this.font.width(this.title)) / 2;
        this.addRenderableWidget(new ItemStackDisplayButton(this.leftPos + 53,
                this.topPos + 20,
                new ItemStack(Items.PIGLIN_HEAD),
                (Button button) -> {
                    if (this.menu.clickMenuButton(this.minecraft.player, 0)) {
                        this.minecraft.gameMode.handleInventoryButtonClick(this.menu.containerId, 0);
                        this.onClose();
                    }
                }) {
            @Override
            public Component getMessage() {
                int nearbyPiglins = BarteringStationScreen.this.menu.getNearbyPiglins();
                return Component.literal(String.valueOf(nearbyPiglins))
                        .withStyle(nearbyPiglins > 0 ? ChatFormatting.WHITE : ChatFormatting.RED);
            }

            @Override
            public void setMessage(Component message) {
                throw new UnsupportedOperationException();
            }

            @Override
            protected void extractContents(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
                this.active = BarteringStationScreen.this.menu.getNearbyPiglins() > 0;
                super.extractContents(guiGraphics, mouseX, mouseY, partialTick);
            }
        }).setTooltip(Tooltip.create(EntityType.PIGLIN.getDescription()));
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.extractRenderState(guiGraphics, mouseX, mouseY, partialTick);
        if (BarteringStation.CONFIG.get(ClientConfig.class).cooldownRenderType.overlay()) {
            this.extractCooldownOverlays(guiGraphics);
        }
    }

    private void extractCooldownOverlays(GuiGraphicsExtractor guiGraphics) {
        float cooldownProgress = this.getCooldownProgress();
        if (cooldownProgress > 0.0F && cooldownProgress < 1.0F) {
            guiGraphics.pose().pushMatrix();
            guiGraphics.pose().translate(this.leftPos, this.topPos);
            for (int i = 0; i < BarteringStationBlockEntity.CURRENCY_SLOTS && i < this.menu.slots.size(); i++) {
                Slot slot = this.menu.slots.get(i);
                if (slot.isActive() && slot.hasItem()) {
                    int startY = Mth.floor(16.0F * (1.0F - cooldownProgress));
                    guiGraphics.fill(RenderPipelines.GUI,
                            slot.x,
                            slot.y + startY,
                            slot.x + 16,
                            slot.y + startY + Mth.ceil(16.0F * cooldownProgress),
                            0X7FFFFFFF);
                }
            }

            guiGraphics.pose().popMatrix();
        }
    }

    private float getCooldownProgress() {
        return this.menu.getBarterDelay() / (float) BarteringStation.CONFIG.get(ServerConfig.class).barterDelay;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.extractBackground(guiGraphics, mouseX, mouseY, partialTick);
        guiGraphics.blit(RenderPipelines.GUI_TEXTURED,
                BARTERING_STATION_LOCATION,
                this.leftPos,
                this.topPos,
                0,
                0,
                this.imageWidth,
                this.imageHeight,
                256,
                256);
        if (BarteringStation.CONFIG.get(ClientConfig.class).cooldownRenderType.arrows()) {
            this.extractCooldownArrows(guiGraphics);
        }
    }

    private void extractCooldownArrows(GuiGraphicsExtractor guiGraphics) {
        int topArrowWidth = this.getTopArrowWidth();
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED,
                RIGHT_ARROW_SPRITE,
                ARROW_SIZE_X,
                ARROW_SIZE_Y,
                0,
                0,
                this.leftPos + 49,
                this.topPos + 40,
                topArrowWidth,
                ARROW_SIZE_Y);
        int bottomArrowWidth = this.getBottomArrowWidth();
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED,
                LEFT_ARROW_SPRITE,
                ARROW_SIZE_X,
                ARROW_SIZE_Y,
                ARROW_SIZE_X - bottomArrowWidth,
                0,
                this.leftPos + 49 + ARROW_SIZE_X - bottomArrowWidth,
                this.topPos + 53,
                bottomArrowWidth,
                ARROW_SIZE_Y);
    }

    private int getTopArrowWidth() {
        int maxBarterDelay = BarteringStation.CONFIG.get(ServerConfig.class).barterDelay;
        int width = ((maxBarterDelay - this.menu.getBarterDelay()) * 2 * BarteringStationScreen.ARROW_SIZE_X)
                / maxBarterDelay;
        return Math.clamp(width, 0, BarteringStationScreen.ARROW_SIZE_X);
    }

    private int getBottomArrowWidth() {
        int maxBarterDelay = BarteringStation.CONFIG.get(ServerConfig.class).barterDelay;
        int width = ((maxBarterDelay - this.menu.getBarterDelay()) * 2 * BarteringStationScreen.ARROW_SIZE_X)
                / maxBarterDelay - BarteringStationScreen.ARROW_SIZE_X;
        return Math.clamp(width, 0, BarteringStationScreen.ARROW_SIZE_X);
    }
}
