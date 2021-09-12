package me.derpy.extraspawners.Items;

import me.derpy.extraspawners.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import redempt.redlib.itemutils.ItemBuilder;

import java.util.List;

public class Spawner {

    public ItemStack giveSpawner(Main plugin, EntityType type, int amount) {
        ItemStack spawner = null;
        //Entity enable check
        List<String> enabledMobs = plugin.getConfig().getStringList("EnabledMobs");
        for (String mob : enabledMobs) {
            EntityType entityMob = EntityType.valueOf(mob);
            if (type.equals(entityMob)){
                NamespacedKey key = new NamespacedKey(plugin, "spawner-mob-type");

                String capName = type.name().toLowerCase().substring(0,1).toUpperCase()+type.name().toLowerCase().substring(1);

                spawner = new ItemBuilder(Material.SPAWNER)
                        .setName(ChatColor.translateAlternateColorCodes('&', "&e&lSpawner: &r&d"+capName))
                        .setCount(amount);
                ItemMeta meta = spawner.getItemMeta();
                meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, type.name());
                spawner.setItemMeta(meta);
                
            }
        }
        return spawner;
    }

}
