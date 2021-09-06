package me.derpy.extraspawners.commands;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.EntityTypeArgument;
import dev.jorel.commandapi.arguments.IntegerArgument;
import dev.jorel.commandapi.arguments.PlayerArgument;
import dev.jorel.commandapi.exceptions.WrapperCommandSyntaxException;
import dev.jorel.commandapi.executors.CommandExecutor;
import me.derpy.extraspawners.Main;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import redempt.redlib.itemutils.ItemBuilder;
import redempt.redlib.itemutils.ItemUtils;

import java.util.Locale;

public class ExtraSpawners {



    public CommandAPICommand cmd(Main plugin){

        //HELP SUBCOMMAND
        CommandAPICommand esHelp = new CommandAPICommand("help")
                .withPermission("extraspawners.commands.extraspawners.help")
                .executes((sender, args) -> {
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&eHelp:"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "- help"));
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "- es"));
                });

        //GIVE SUBCOMMAND
        CommandAPICommand esGive = new CommandAPICommand("give")
                .withArguments(new PlayerArgument("player"))
                .withArguments(new EntityTypeArgument("entity"))
                .withArguments(new IntegerArgument("amount", 1, 64))
                .withPermission("extraspawners.commands.extraspawners.give")
                .executesPlayer((sender, args) -> {
                    Player target = (Player) args[0];
                    EntityType entity = (EntityType) args[1] ;
                    Integer amount = (Integer) args[2];

                    NamespacedKey key = new NamespacedKey(plugin, "spawner-mob-type");

                    String capName = entity.name().toLowerCase().substring(0,1).toUpperCase()+entity.name().toLowerCase().substring(1);

                    ItemStack spawner = new ItemBuilder(Material.SPAWNER)
                            .setName(ChatColor.translateAlternateColorCodes('&', "&e&lSpawner: &r&d"+capName))
                            .setCount(amount);
                    ItemMeta meta = spawner.getItemMeta();
                    meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, entity.name());
                    spawner.setItemMeta(meta);

                    target.getInventory().addItem(spawner);
                });



        //MAIN COMMAND
        CommandAPICommand cmd = new CommandAPICommand("extraspawners")
                .withPermission("*")
                .withPermission("extraspawners.*")
                .withPermission("extraspawners.commands.*")
                .withPermission("extraspawners.commands.extraspawners")
                .withPermission("extraspawners.commands.extraspawners.*")
                .withSubcommand(esHelp)
                .withSubcommand(esGive)
                .withAliases("es")
                .executes(new CommandExecutor() {
                    @Override
                    public void run(CommandSender sender, Object[] args) throws WrapperCommandSyntaxException {
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a=========||========="));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cVersion: &b0.1.1-beta"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cAuthor: &bderpy588"));
                        sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a=========||========="));
                    }
                });
        return cmd;
    }

}
