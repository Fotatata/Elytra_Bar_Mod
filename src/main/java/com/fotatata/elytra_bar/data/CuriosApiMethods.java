package com.fotatata.elytra_bar.data;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import top.theillusivec4.curios.api.CuriosApi;

public class CuriosApiMethods {
    public static boolean checkCuriosElytra(Player player){
        return CuriosApi.getCuriosHelper().findFirstCurio(player, Items.ELYTRA).isPresent();
    }
    public static ItemStack getItemStack(Player player){
        var durability = new Object() {
            ItemStack durability;
        };
        CuriosApi.getCuriosHelper().findFirstCurio(player, Items.ELYTRA).ifPresent(inventory -> {
            ItemStack stack = inventory.stack();
            durability.durability = stack;
        });
        return durability.durability;
    }
}
