package me.derpy.extraspawners;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPIConfig;
import me.derpy.extraspawners.Utils.ConfigManager;
import me.derpy.extraspawners.Utils.RecipeUtil.RecipeManager;
import me.derpy.extraspawners.commands.ExtraSpawners;
import me.derpy.extraspawners.events.BreakBlock;
import me.derpy.extraspawners.events.PlaceBlock;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {


    @Override
    public void onLoad() {
        CommandAPI.onLoad(new CommandAPIConfig().silentLogs(true));

    }

    @Override
    public void onEnable() {
        CommandAPI.onEnable(this);
        //Register Commands
        new ExtraSpawners().cmd(this).register();
        //==================
        //Register Listeners
        Bukkit.getPluginManager().registerEvents(new PlaceBlock(this), this);
        Bukkit.getPluginManager().registerEvents(new BreakBlock(this), this);
        //==================
        //Loading Config File
        new ConfigManager(this).saveDefaultConfig();
        //==================
        //Register Crafting Manager
        RecipeManager recipeManger = new RecipeManager(this).initlize();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
