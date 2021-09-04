package me.derpy.extraspawners.Events;

import me.derpy.extraspawners.Main;
import me.devtec.theapi.TheAPI;
import me.devtec.theapi.apis.ItemCreatorAPI;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.CreatureSpawner;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class BlockBreak implements Listener {

    Main plugin;
    public BlockBreak(Main pl){
        plugin = pl;
    }

    @EventHandler
    public void onSpawnerBreak(BlockBreakEvent e){
        Block block = e.getBlock();
        if (block.getType() == Material.SPAWNER){
            //Check for creative
            Player player = e.getPlayer();
            if (player.getGameMode() != GameMode.CREATIVE){
                //Check for silk touch
                ItemStack playerHand = e.getPlayer().getInventory().getItemInMainHand();
                if (playerHand.containsEnchantment(Enchantment.SILK_TOUCH)){
                    NamespacedKey key = new NamespacedKey(plugin, "spawner-mob-type");
                    BlockState state = block.getState();
                    CreatureSpawner spawnerBlock = ((CreatureSpawner) state);
                    EntityType type = spawnerBlock.getSpawnedType();
                    //ItemStack Spawner
                    ItemStack spawnerItem = ItemCreatorAPI.create(Material.SPAWNER, 1, "&e&lSpawner: &r&c"+type.name());
                    ItemMeta meta = spawnerItem.getItemMeta();
                    meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, type.name());
                    spawnerItem.setItemMeta(meta);
                    //Dropping Item
                    e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), spawnerItem);
                }
            }
        }
    }
}
