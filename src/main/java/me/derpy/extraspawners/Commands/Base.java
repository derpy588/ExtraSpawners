package me.derpy.extraspawners.Commands;

import me.derpy.extraspawners.Main;
import me.devtec.theapi.TheAPI;
import me.devtec.theapi.apis.ItemCreatorAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.BlockState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class Base implements CommandExecutor {
    Main plugin;
    public Base(Main pl){
        plugin = pl;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String cmds, String[] args) {
        if (!(sender instanceof Player)){
            TheAPI.bcMsg("Sorry only players can run this command.");
            return true;
        }
        Player player = (Player) sender;
        NamespacedKey key = new NamespacedKey(plugin, "spawner-mob-type");
        if (args.length == 0){
            sender.sendMessage(TheAPI.colorize("§cVersion:§b 0.1.0"));
        }else if (args.length == 1) {
            if (args[0].equalsIgnoreCase("help")){
                sender.sendMessage(TheAPI.colorize("§bHelp Menu:"));
                sender.sendMessage(TheAPI.colorize("§b/es help: §8Get info about all commands."));
                sender.sendMessage(TheAPI.colorize("§b/es give <player> <entity> <amount>: §8Give a player a spawner"));
            }else if (args[0].equalsIgnoreCase("give")){
                sender.sendMessage(TheAPI.colorize("§4Invalid arguments. Correct usage: /es give <player> <entity> <amount>"));
            }
        }else if (args.length == 4) {
            if (args[0].equalsIgnoreCase("give")){
                Player plr = TheAPI.getPlayer(args[1]);
                if (plr == null){
                    sender.sendMessage("Invalid Player!");
                    return true;
                }
                EntityType type = null;

                try{
                    type = EntityType.valueOf(args[2]);
                }catch(IllegalArgumentException exp){
                    sender.sendMessage("Invalid mob");
                    return  true;
                }
                ItemStack spawner = ItemCreatorAPI.create(Material.SPAWNER, Integer.parseInt(args[3]), "&e&lSpawner: &r&c"+type.name());
                ItemMeta meta = spawner.getItemMeta();
                meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, args[2]);
                spawner.setItemMeta(meta);
                TheAPI.giveItem(plr, spawner);
            }
        }
        return true;
    }
}
