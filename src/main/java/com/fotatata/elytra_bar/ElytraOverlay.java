package com.fotatata.elytra_bar;

import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.LayeredDraw;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ElytraItem;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.capabilities.EntityCapability;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

@EventBusSubscriber(modid = ElytraBar.MOD_ID, value = Dist.CLIENT)
public class ElytraOverlay implements LayeredDraw.Layer {
    private static Minecraft minecraft;
    private static ResourceLocation ELYTRA_EMPTY_ICON_SPRITE;
    private static ResourceLocation ELYTRA_HALF_ICON_SPRITE;
    private static ResourceLocation ELYTRA_FULL_ICON_SPRITE;
    private static ResourceLocation ELYTRA_BAR_FULL_SPRITE;
    private static ResourceLocation ELYTRA_BAR_EMPTY_SPRITE;
    private static boolean wingSprites;
    private static Config.Overlays overlayType;
    private final RandomSource random = RandomSource.create();
    private static int tickCount = 0;
    private double previousDurability;
    private static int frame;

    @Override
    public void render(@NotNull GuiGraphics guiGraphics, @NotNull DeltaTracker deltaTracker) {
        Player player = minecraft.getCameraEntity() instanceof Player dummy ? dummy : null;
        this.random.setSeed(tickCount);
        ItemStack elytraItem = maybeElytraItem(player);
        if (elytraItem.getItem() instanceof ElytraItem) {
            double durability = (elytraItem.getMaxDamage() - elytraItem.getDamageValue() - 1d) / (elytraItem.getMaxDamage() - 1d);

            String blinkString = (frame % 4 == 1 || frame % 4 == 2) ? "_blinking" : "";
            String iconString = wingSprites ? "wings" : "elytra";
            String barString = (overlayType != Config.Overlays.BAR) ? "long" : "short";
            
            ELYTRA_EMPTY_ICON_SPRITE = ResourceLocation.fromNamespaceAndPath(ElytraBar.MOD_ID, "hud/" + iconString + "_empty" + blinkString);
            ELYTRA_HALF_ICON_SPRITE = ResourceLocation.fromNamespaceAndPath(ElytraBar.MOD_ID, "hud/" + iconString + "_half" + blinkString);
            ELYTRA_FULL_ICON_SPRITE = ResourceLocation.fromNamespaceAndPath(ElytraBar.MOD_ID, "hud/" + iconString + "_full" + blinkString);
            ELYTRA_BAR_EMPTY_SPRITE = ResourceLocation.fromNamespaceAndPath(ElytraBar.MOD_ID, "hud/bar_" + barString + "_empty" + blinkString);
            ELYTRA_BAR_FULL_SPRITE = ResourceLocation.fromNamespaceAndPath(ElytraBar.MOD_ID, "hud/bar_" + barString + "_full" + blinkString);


            if (previousDurability > durability) frame = -3;
            if (previousDurability < durability) frame = 6;

            switch (overlayType) {
                case Config.Overlays.BAR -> renderBar(guiGraphics, durability);
                case Config.Overlays.BOTH -> renderMix(guiGraphics, durability);
                default -> renderIcons(guiGraphics, durability);
            }
            minecraft.gui.leftHeight += 10;
            this.previousDurability = durability;
        }
    }

    public ElytraOverlay(){
        minecraft = Minecraft.getInstance();
        wingSprites = Config.WING_SPRITES.get();
        overlayType = Config.DURABILITY_INDICATOR.get();
    }


    private ItemStack maybeElytraItem(Player player){
        if (player != null && minecraft.gameMode.canHurtPlayer()){
            if (ModList.get().isLoaded("elytraslot")) {
                IItemHandler elytraHandler = player.getCapability(CuriosCompatibility.CURIOS_INVENTORY);
                if (elytraHandler != null) {
                    for (int i = 0; i < elytraHandler.getSlots(); i++)
                        if (elytraHandler.getStackInSlot(i).getItem() instanceof ElytraItem)
                            return elytraHandler.getStackInSlot(i);
                }
            }
            return player.getInventory().getArmor(2);
        }
        return ItemStack.EMPTY;
    }

    private void renderIcons(GuiGraphics guiGraphics, double durabilityPercentage){
        int leftHeight = minecraft.gui.leftHeight;
        double durability = Math.ceil(durabilityPercentage * 20d);
        for (int i = 0; i < 10; i++) {
            int x = guiGraphics.guiWidth() / 2 - 91 + i * 8;
            int y = guiGraphics.guiHeight() - leftHeight;
            if (durabilityPercentage < 0.2 || frame < 0) y += this.random.nextInt(3) - 1;
            if (i * 2 + 1 < durability) guiGraphics.blitSprite(ELYTRA_FULL_ICON_SPRITE, x, y, 9, 9);
            if (i * 2 + 1 == durability) guiGraphics.blitSprite(ELYTRA_HALF_ICON_SPRITE, x, y, 9, 9);
            if (i * 2 + 1 > durability) guiGraphics.blitSprite(ELYTRA_EMPTY_ICON_SPRITE, x, y, 9, 9);
        }
    }

    private void renderBar(GuiGraphics guiGraphics, double durabilityPercentage){
        int leftHeight = minecraft.gui.leftHeight;
        int durability = (int) Math.ceil(durabilityPercentage * 79d) + 1;
        int x = guiGraphics.guiWidth() / 2 - 91;
        int y = guiGraphics.guiHeight() - leftHeight + 5;

        guiGraphics.blitSprite(ELYTRA_BAR_EMPTY_SPRITE,x,y,81,4);
        if (durability > 0) guiGraphics.blitSprite(ELYTRA_BAR_FULL_SPRITE,81,4,0,0,x,y,durability,4);
    }

    private void renderMix(GuiGraphics guiGraphics, double durabilityPercentage){
        int leftHeight = minecraft.gui.leftHeight;
        int durability = (int) Math.ceil(durabilityPercentage * 69d);
        int x = guiGraphics.guiWidth() / 2 - 81;
        int y = guiGraphics.guiHeight() - leftHeight + 5;

        if (durabilityPercentage > 0.5) guiGraphics.blitSprite(ELYTRA_FULL_ICON_SPRITE, x - 10, y - 5,9,9);
        else if (durabilityPercentage > 0) guiGraphics.blitSprite(ELYTRA_HALF_ICON_SPRITE, x - 10, y - 5,9,9);
        else guiGraphics.blitSprite(ELYTRA_EMPTY_ICON_SPRITE, x - 10, y - 5,9,9);

        guiGraphics.blitSprite(ELYTRA_BAR_EMPTY_SPRITE,x,y,71,4);
        if (durability > 0) guiGraphics.blitSprite(ELYTRA_BAR_FULL_SPRITE,71,4,0,0,x,y,durability,4);
    }

    @SubscribeEvent
    static void updateConfig(ModConfigEvent.Reloading event){
        wingSprites = Config.WING_SPRITES.get();
        overlayType = Config.DURABILITY_INDICATOR.get();
    }

    @SubscribeEvent
    static void tick(ClientTickEvent.Pre event){
        if (!minecraft.isPaused()) tickCount++;
        if (tickCount > 1000) tickCount = 0;
        if (frame < 0) frame++;
        if (frame > 0) frame--;
    }

    public static class CuriosCompatibility {

        public static final EntityCapability<IItemHandler, Void> CURIOS_INVENTORY =
                EntityCapability.createVoid(ResourceLocation.fromNamespaceAndPath("curios", "item_handler"), IItemHandler.class);
    }
}
