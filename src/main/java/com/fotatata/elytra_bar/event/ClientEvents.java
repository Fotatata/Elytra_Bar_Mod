package com.fotatata.elytra_bar.event;

import com.fotatata.elytra_bar.ElytraBar;
import com.fotatata.elytra_bar.client.ElytraHudOverlay;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = ElytraBar.ModId, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static  class ClientModBusEvents {

        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event){
            event.registerBelowAll("elytra_bar", ElytraHudOverlay.HUD_ELYTRA);
        }

    }
}
