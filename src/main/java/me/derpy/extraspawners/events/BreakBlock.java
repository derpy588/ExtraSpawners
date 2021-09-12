package me.derpy.extraspawners.events;

import me.derpy.extraspawners.Items.Spawner;
import me.derpy.extraspawners.Main;
import org.bukkit.*;
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
import redempt.redlib.itemutils.ItemBuilder;

public class BreakBlock implements Listener {

    Main plugin;
    public BreakBlock(Main pl){
        plugin = pl;
    }


    @EventHandler
    public void onBreakBlock(BlockBreakEvent e) {
        Block block = e.getBlock();
        if (block.getType() == Material.SPAWNER){
            //Check for creative
            Player player = e.getPlayer();
            if (player.getGameMode() != GameMode.CREATIVE){
                //Check for silk touch
                int minLevel = plugin.getConfig().getInt("minSilkTouchLevel");
                ItemStack playerHand = e.getPlayer().getInventory().getItemInMainHand();
                if (playerHand.containsEnchantment(Enchantment.SILK_TOUCH)){
                    if (playerHand.getEnchantmentLevel(Enchantment.SILK_TOUCH) >= minLevel){
                        BlockState state = block.getState();
                        CreatureSpawner spawnerBlock = ((CreatureSpawner) state);
                        EntityType type = spawnerBlock.getSpawnedType();
                        //ItemStack Spawner
                        ItemStack spawner = new Spawner().giveSpawner(plugin, type, 1);
                        //Dropping Item
                        e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), spawner);
                    }
                }
            }
        }
    }

}
