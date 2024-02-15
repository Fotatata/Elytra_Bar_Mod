package com.fotatata.elytra_bar.client;

import com.fotatata.elytra_bar.ElytraBar;
import com.fotatata.elytra_bar.bar.DataHandler;
import com.fotatata.elytra_bar.config.ClientConfig;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.ForgeGui;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class ElytraHudOverlay implements IGuiOverlay {
    private static final ResourceLocation ELYTRA_ICONS = new ResourceLocation(ElytraBar.ModId,"textures/bar/icons.png");

    @Override
    public void render(ForgeGui gui, GuiGraphics graphics, float partialTick, int screenWidth, int screenHeight) {
        int x = screenWidth/2;
        int y = 0;
        int v;

        if (DataHandler.isWearingArmor()) {
            y -= 10;
        }
        if (DataHandler.hasAbsorption()) {
            y -= 10;
        }

        if (ClientConfig.ICON_TYPE.get()){
            v = 9;
        }else{
            v = 0;
        }
        if (DataHandler.IsWearingElytra()) {
            switch (ClientConfig.OVERLAY_TYPE.get()) {
                case 0 -> {
                    for (int i = 0; i < 10; i++) {
                        graphics.blit(ELYTRA_ICONS, x - 91 + (i * 8), y + screenHeight - 49, 0, v, 9, 9,32,32);
                    }
                    for (int i = 0; i < DataHandler.getElytraDurability() / 2; i++) {
                        graphics.blit(ELYTRA_ICONS, x - 91 + (i * 8), y + screenHeight - 49, 9, v, 9, 9,32,32);
                    }
                    if ((DataHandler.getElytraDurability() % 2) == 1) {
                        graphics.blit(ELYTRA_ICONS, x - 95 + (4 * DataHandler.getElytraDurability()), y + screenHeight - 49, 18, v, 9, 9,32,32);
                    }
                }
                case 1 -> {
                    for (int i = 0; i < 79; i++) {
                        graphics.blit(ELYTRA_ICONS, x - 90 + i, y + screenHeight - 44, 1, 18, 1, 4,32,32);
                    }
                    for (int i = 0; i < 2; i++) {
                        graphics.blit(ELYTRA_ICONS, x - 91 + (i*80), y + screenHeight - 44, 0, 18, 1, 4,32,32);
                    }
                    for (int i = 1; i < DataHandler.getElytraDurability(); i++) {
                        graphics.blit(ELYTRA_ICONS, x - 90 + i, y + screenHeight - 44, 3, 18, 1, 4,32,32);
                    }
                    if (DataHandler.getElytraDurability() >= 1) {
                        graphics.blit(ELYTRA_ICONS, x - 90, y + screenHeight - 44, 2, 18, 1, 4,32,32);
                    }
                }
                case 2 -> {
                    graphics.blit(ELYTRA_ICONS, x - 91, y + screenHeight - 49, 0, v, 9 ,9,32,32);
                    if (DataHandler.getElytraDurability() >= 35)
                        graphics.blit(ELYTRA_ICONS, x - 91, y + screenHeight - 49, 9, v, 9, 9,32,32);
                    if (DataHandler.getElytraDurability() >= 1 && DataHandler.getElytraDurability() <= 34)
                        graphics.blit(ELYTRA_ICONS, x - 91, y + screenHeight - 49, 18, v, 9, 9,32,32);
                    for (int i = 0; i < 69; i++) {
                        graphics.blit(ELYTRA_ICONS, x - 80 + i, y + screenHeight - 44, 1, 18, 1, 4,32,32);
                    }
                    for (int i = 0; i < 2; i++) {
                        graphics.blit(ELYTRA_ICONS, x - 81 + (i*70), y + screenHeight - 44, 0, 18, 1, 4,32,32);
                    }
                    for (int i = 1; i < DataHandler.getElytraDurability(); i++) {
                        graphics.blit(ELYTRA_ICONS, x - 80 + i, y + screenHeight - 44, 3, 18, 1, 4,32,32);
                    }
                    if (DataHandler.getElytraDurability() >= 1) {
                        graphics.blit(ELYTRA_ICONS, x - 80, y + screenHeight - 44, 2, 18, 1, 4,32,32);
                    }
                }
            }
        }
    }
}
