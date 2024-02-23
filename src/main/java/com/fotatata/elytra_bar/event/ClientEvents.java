package com.fotatata.elytra_bar.event;

import com.fotatata.elytra_bar.ElytraBar;
import com.fotatata.elytra_bar.client.ElytraHudOverlay;
import com.fotatata.elytra_bar.data.DataHandler;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ClientEvents {
    @Mod.EventBusSubscriber(modid = ElytraBar.ModId, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static  class ClientModBusEvents {
        @SubscribeEvent
        public static void registerElytraOverlay(RegisterGuiOverlaysEvent event) {
            ElytraHudOverlay Hud = new ElytraHudOverlay();
            event.registerAbove(VanillaGuiOverlay.HOTBAR.id(),"elytrahud", Hud);
        }
        //@SubscribeEvent
        //public static void elytraRepaired(PlayerXpEvent.PickupXp event) {
        //    int durabilityPreXp = DataHandler.getElytraDurability();
        //}
    }
}
