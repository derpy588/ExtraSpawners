package me.derpy.extraspawners.Utils.RecipeUtil;

import me.derpy.extraspawners.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;

public class RecipeManager implements Listener {

    Main plugin;
    public RecipeManager(Main pl) {
        plugin = pl;
    }

    @EventHandler
    public void onCraftingItem(PrepareItemCraftEvent e) {

    }

    public RecipeManager initlize() {
        Bukkit.getPluginManager().registerEvents(this, plugin);
        return this;
    }

}
