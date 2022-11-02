package com.fotatata.elytra_bar.client;

import com.fotatata.elytra_bar.ElytraBar;
import com.fotatata.elytra_bar.bar.DataHandler;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

public class ElytraHudOverlay {
    private static final ResourceLocation ELYTRA_ICONS = new ResourceLocation(ElytraBar.ModId,"textures/bar/icons.png");

    public static final IGuiOverlay HUD_ELYTRA = (gui, poseStack, partialTick, width, height) -> {
        int x = width/2;
        int y;

        if (DataHandler.isWearingArmor() ^ DataHandler.hasAbsorption()) {
            y = 0;
        } else if (DataHandler.isWearingArmor() && DataHandler.hasAbsorption()) {
            y = -10;
        } else {
            y = 10;
        }

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0, ELYTRA_ICONS);
        for (int i = 0; i < 10; i++) {
            if (DataHandler.IsWearingElytra()) {
                    GuiComponent.blit(poseStack, x - 91 + (i * 8), y + height - 59, 0, 0, 9, 9, 32, 32);
                }
            }
        for(int i = 0; i < DataHandler.getElytraDurability()/2 ; i++){
            if (DataHandler.IsWearingElytra()){
                GuiComponent.blit(poseStack, x - 91 + (i * 8), y + height - 59, 9, 0, 9, 9, 32, 32);
            }
        }
        if (DataHandler.IsWearingElytra() && (DataHandler.getElytraDurability() % 2) == 1){
            GuiComponent.blit(poseStack, x - 95 + (4 * DataHandler.getElytraDurability()), y + height - 59, 18, 0, 9, 9, 32, 32);
        }
    };
}