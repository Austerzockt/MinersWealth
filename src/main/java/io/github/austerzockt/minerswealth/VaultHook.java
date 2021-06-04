package io.github.austerzockt.minerswealth;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class VaultHook {
    private JavaPlugin javaPlugin;
    private Economy econ;

    public VaultHook(JavaPlugin plugin) {
        this.javaPlugin = plugin;
        if (!setupEconomy()) {
            javaPlugin.getLogger().severe("Vault could not be loaded! Shutting down");
            javaPlugin.getPluginLoader().disablePlugin(javaPlugin);
        }
    }

    public Economy getEconomy() {
        return econ;
    }

    private boolean setupEconomy() {
        if (javaPlugin.getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = javaPlugin.getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
}
