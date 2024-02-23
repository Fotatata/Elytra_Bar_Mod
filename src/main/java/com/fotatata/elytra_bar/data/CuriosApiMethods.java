package com.fotatata.elytra_bar.data;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import top.theillusivec4.curios.api.CuriosApi;

public class CuriosApiMethods {
    public static boolean checkCuriosElytra(Player player){
        return (CuriosApi.getCuriosInventory(player).map(inventory -> inventory.findFirstCurio(Items.ELYTRA).isPresent()).orElse(false));
    }
    public static ItemStack getItemStack(Player player){
        var durability = new Object() {
            ItemStack durability;
        };
        CuriosApi.getCuriosInventory(player).ifPresent(inventory -> {
            ItemStack stack = inventory.findFirstCurio(Items.ELYTRA).map(result -> result.stack()).orElse(ItemStack.EMPTY);
            durability.durability = stack;
        });
        return durability.durability;
    }
}
