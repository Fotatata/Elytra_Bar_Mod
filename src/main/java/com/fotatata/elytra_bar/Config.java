package com.fotatata.elytra_bar;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue WING_SPRITES = BUILDER
            .comment("Whether to use wing-like sprites")
            .define("wingSprites", false);

    public static final ModConfigSpec.EnumValue<Overlays> DURABILITY_INDICATOR = BUILDER
            .comment("Which durability indicator to use")
            .defineEnum("durabilityIndicator", Overlays.ICONS);

public static final ModConfigSpec.IntValue HUD_OFFSET = BUILDER
        .comment("Adjust this slider to change the height at which the HUD is rendered")
        .defineInRange("",0,-10,50);

    static final ModConfigSpec SPEC = BUILDER.build();

    public enum Overlays{
        ICONS, BAR, BOTH
    }
}
