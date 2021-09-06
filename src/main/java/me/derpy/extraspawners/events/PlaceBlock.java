package me.derpy.extraspawners.events;

import me.derpy.extraspawners.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PlaceBlock implements Listener {

    Main plugin;
    public PlaceBlock(Main pl){
        plugin = pl;
    }

    @EventHandler
    public void onPlaceBlock(BlockPlaceEvent e){
        Block b = e.getBlockPlaced();
        ItemStack item = e.getItemInHand();
        ItemMeta meta = item.getItemMeta();
        if (b.getType() == Material.SPAWNER){
            NamespacedKey key = new NamespacedKey(plugin, "spawner-mob-type");
            PersistentDataContainer container = meta.getPersistentDataContainer();
            BlockState state = b.getState();
            CreatureSpawner spawner = ((CreatureSpawner) state);
            if (container.has(key, PersistentDataType.STRING)){
                EntityType type = EntityType.valueOf(container.get(key, PersistentDataType.STRING));
                spawner.setSpawnedType(type);
                state.update();
            }else{
                e.getItemInHand().setType(Material.AIR);
                e.setCancelled(true);
            }
        }
    }
}
