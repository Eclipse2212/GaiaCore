package fr.gaiacraft.gaiacore;

import fr.gaiacraft.gaiacore.util.customLogger;
import fr.gaiacraft.gaiacore.listener.PlayerListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Core extends JavaPlugin {

    @Override
    public void onEnable(){
        createConfig();
        this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
    }

    @Override
    public void onDisable(){

    }

    public void createConfig() {
        try {
            if (!this.getDataFolder().exists()) {
                getDataFolder().mkdirs();
            }
            File file = new File(getDataFolder(), "config.yml");
            if (!file.exists()) {
                customLogger.logInfo("Config.yml not found, creating!");
                saveDefaultConfig();
            } else {
                customLogger.logInfo("Config.yml found, loading!");
            }

        } catch (Exception e) {
            e.printStackTrace();

        }

    }
}
