package com.fotatata.elytra_bar.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.ConfigValue<Integer> OVERLAY_TYPE;
    public static final ForgeConfigSpec.BooleanValue ICON_TYPE;

    static {
        BUILDER.push("Configs for Elytra Bar");
        OVERLAY_TYPE = BUILDER.comment("Select the type of overlay you prefer!","0 for Icons, 1 for Bar, 2 for Both")
                .defineInRange("Overlay_Type",0,0,2);
        ICON_TYPE = BUILDER.comment("Select the type of icons you prefer!", "true for elytra-like, false for armor-like")
                .define("use_elytra_icons", true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
