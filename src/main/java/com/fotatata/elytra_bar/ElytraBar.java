package com.fotatata.elytra_bar;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;

@Mod(ElytraBar.MOD_ID)
public class ElytraBar {
    public static final String MOD_ID = "elytra_bar";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ElytraBar(IEventBus modEventBus, ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.CLIENT, Config.SPEC);
    }
}
