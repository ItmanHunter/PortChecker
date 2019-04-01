package com.ocoolcraft.plugins;

import org.bukkit.plugin.java.JavaPlugin;

public class PortChecker extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("portc").setExecutor(new PCCommand());
        getLogger().info("Enabled PortChecker");
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabled PortChecker");
    }

}
