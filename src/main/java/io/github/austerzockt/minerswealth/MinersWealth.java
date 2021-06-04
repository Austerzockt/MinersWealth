package io.github.austerzockt.minerswealth;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class MinersWealth extends JavaPlugin {
    private MyConfig config;
    private VaultHook vaultHook;
    private List<MinersConfigEntry> minersConfigEntryList = new ArrayList<>();
    @Override
    public void onEnable() {
        config = new MyConfig(this, "config");
        this.vaultHook = new VaultHook(this);

        this.getServer().getPluginManager().registerEvents(new BlockBreakListener(this), this);
        ConfigurationSection section = config.getConfigurationSection("blocks");
        for (String s : section.getKeys(false)) {
            getLogger().severe(s);
            ConfigurationSection entry = section.getConfigurationSection(s);
            minersConfigEntryList.add(new MinersConfigEntry(entry.getStringList("worlds")).money(entry.getInt("money")).material(Material.getMaterial(s)));
        }

    }

    public List<MinersConfigEntry> getMinersConfigEntryList() {
        return minersConfigEntryList;
    }

    public VaultHook getVaultHook() {
        return vaultHook;
    }
}
