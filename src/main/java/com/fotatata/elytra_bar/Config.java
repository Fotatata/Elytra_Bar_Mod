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

    static final ModConfigSpec SPEC = BUILDER.build();

    public enum Overlays{
        ICONS, BAR, BOTH
    }
}
