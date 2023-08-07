package trxsh.ontop.servercrystal.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import trxsh.ontop.servercrystal.Main;

public class GlobalCrystalToggle implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {

            sender.sendMessage(ChatColor.RED + "Only players are able to use this command.");
            return true;

        }

        if(!(sender.isOp())) {

            sender.sendMessage(ChatColor.RED + "Only operators are able to use this command.");
            return true;

        }

        if(Main.enableGlobalCrystals) {

            Main.enableGlobalCrystals = false;
            Bukkit.broadcastMessage(ChatColor.RED + "Fast crystals is globally disabled.");

        } else {

            Main.enableGlobalCrystals = true;
            Bukkit.broadcastMessage(ChatColor.GREEN + "Fast crystals is globally enabled.");

        }

        return true;

    }

}
