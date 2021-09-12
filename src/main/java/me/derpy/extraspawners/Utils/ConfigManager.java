package me.derpy.extraspawners.Utils;

import me.derpy.extraspawners.Main;

public class ConfigManager {

    Main plugin;
    public ConfigManager(Main pl) {
        plugin = pl;
    }

    public void saveDefaultConfig(){
        plugin.saveDefaultConfig();
    }


}
