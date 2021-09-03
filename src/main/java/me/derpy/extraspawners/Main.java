package me.derpy.extraspawners;

import me.derpy.extraspawners.Commands.Base;
import me.derpy.extraspawners.Events.BlockPlace;
import me.devtec.theapi.TheAPI;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        //Command Register
        PluginCommand es = TheAPI.createCommand("ExtraSpawners", this);
        es.setAliases(Arrays.asList("es", "extraspawners"));
        es.setExecutor(new Base(this));
        TheAPI.registerCommand(es);
        //End
        //Event Register
        Bukkit.getPluginManager().registerEvents(new BlockPlace(this), this);
        //end

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
