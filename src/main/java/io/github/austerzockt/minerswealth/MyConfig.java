package io.github.austerzockt.minerswealth;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;



    public class MyConfig extends YamlConfiguration {
        private final Plugin plugin;
        private final String name;
        private final File file;
        public MyConfig(Plugin plugin, String name) {
            this.name = name;
            this.plugin = plugin;
            file = new File(plugin.getDataFolder(), name.endsWith(".yml") ? name : name + ".yml");
            if (!file.exists()) {
                InputStream is = plugin.getResource(name + ".yml");
                plugin.saveResource(name + ".yml", false);
            }
            try {
                loadConfig();
            } catch (IOException | InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }

        private void loadConfig() throws IOException, InvalidConfigurationException {


            if (!file.exists()) {
                try {
                    // Save the config file if found inside the jar
                    plugin.saveResource(file.getName(), false);
                } catch (IllegalArgumentException e) {
                    // File not found in resources, creating a blank one
                    file.createNewFile();
                }
            }

            load(file);
        }

        public void save() {
            try {
                save(file);
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "There was an error while saving the " + file.getName() + " configuration!", e);
            }

        }

        public void reload() {
            try {
                loadConfig();
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "There was an error while creating the " + file.getName() + " configuration file!", e);
            } catch (InvalidConfigurationException e) {
                plugin.getLogger().log(Level.SEVERE, "The configuration \"" + file.getName() + "\" is invalid!", e);
            }
        }

        @Override
        public String getName() {
            return name;
        }
    }


