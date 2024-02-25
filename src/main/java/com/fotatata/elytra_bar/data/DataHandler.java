package com.fotatata.elytra_bar.data;

import com.fotatata.elytra_bar.config.ClientConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;

public class DataHandler {

    @SubscribeEvent
    public static Player getPlayer() {
        return Minecraft.getInstance().player;
    }

    public static int getElytraDurability() {
        int iconAmount = 0;
        if (IsWearingElytra()) {
            switch (ClientConfig.OVERLAY_TYPE.get()) {
                case 0 -> {
                    iconAmount = (int) Math.round((432 - getPreciseDurability()) / 21.6);
                    if (getPreciseDurability() <= 430 && iconAmount == 0) {
                        iconAmount = 1;
                    }
                }
                case 1 -> {
                    iconAmount = (int) Math.round((432 - getPreciseDurability()) / (432.0 / 79.0));
                    if (getPreciseDurability() <= 430 && iconAmount == 0) {
                        iconAmount = 1;
                    }
                }
                case 2 -> {
                    iconAmount = (int) Math.round((432 - getPreciseDurability()) / (432.0 / 69.0));
                    if (getPreciseDurability() <= 430 && iconAmount == 0) {
                        iconAmount = 1;
                    }
                }
            }
        }
        return iconAmount;
    }
    public static int getPreciseDurability(){
        return ModList.get().isLoaded("curios") ? (CuriosApiMethods.getItemStack(getPlayer()).getItem() == Items.ELYTRA ? CuriosApiMethods.getItemStack(getPlayer()).getDamageValue() : getPlayer().getItemBySlot(EquipmentSlot.CHEST).getDamageValue()) : getPlayer().getItemBySlot(EquipmentSlot.CHEST).getDamageValue();
    }
    public static boolean isWearingArmor() {
        return (getPlayer().getArmorValue() != 0);
    }

    public static boolean hasAbsorption() {
        return (getPlayer().getAbsorptionAmount() != 0);
    }

    public static boolean IsWearingElytra() {
        return (ModList.get().isLoaded("curios") ? (CuriosApiMethods.checkCuriosElytra(getPlayer()) || getPlayer().getItemBySlot(EquipmentSlot.CHEST).getItem() == Items.ELYTRA) : getPlayer().getItemBySlot(EquipmentSlot.CHEST).getItem() == Items.ELYTRA) &&
                !(getPlayer().isCreative() || getPlayer().isSpectator());
    }
}