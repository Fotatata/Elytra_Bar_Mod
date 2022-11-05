package com.fotatata.elytra_bar.bar;

import com.fotatata.elytra_bar.config.ClientConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class DataHandler {

    @SubscribeEvent
    public static Player getPlayer() {
        return Minecraft.getInstance().player;
    }

    public static int getElytraDurability() {
        int ElytraDurability = 0;
        if (IsWearingElytra()) {
            switch (ClientConfig.OVERLAY_TYPE.get()) {
                case 0 -> {
                    ElytraDurability = ((int) Math.round((432 - (getPlayer().getItemBySlot(EquipmentSlot.CHEST).getDamageValue())) / 21.6));
                    if (getPlayer().getItemBySlot(EquipmentSlot.CHEST).getDamageValue() <= 430 && ElytraDurability == 0) {
                        ElytraDurability = 1;
                    }
                }
                case 1 -> {
                    ElytraDurability = ((int) Math.round((432 - (getPlayer().getItemBySlot(EquipmentSlot.CHEST).getDamageValue())) / (432.0 / 79.0)));
                    if (getPlayer().getItemBySlot(EquipmentSlot.CHEST).getDamageValue() <= 430 && ElytraDurability == 0) {
                        ElytraDurability = 1;
                    }
                }
                case 2 -> {
                    ElytraDurability = ((int) Math.round((432 - (getPlayer().getItemBySlot(EquipmentSlot.CHEST).getDamageValue())) / (432.0 / 69.0)));
                    if (getPlayer().getItemBySlot(EquipmentSlot.CHEST).getDamageValue() <= 430 && ElytraDurability == 0) {
                        ElytraDurability = 1;
                    }
                }
            }
        }
        return ElytraDurability;
    }

    public static boolean isWearingArmor() {
        return (getPlayer().getArmorValue() != 0);
    }

    public static boolean hasAbsorption() {
        return (getPlayer().getAbsorptionAmount() != 0);
    }

    public static boolean IsWearingElytra() {
        return (getPlayer().getItemBySlot(EquipmentSlot.CHEST).getItem() == Items.ELYTRA) && !(getPlayer().isCreative() || getPlayer().isSpectator());
    }
}