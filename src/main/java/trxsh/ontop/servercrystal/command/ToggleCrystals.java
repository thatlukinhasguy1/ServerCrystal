package trxsh.ontop.servercrystal.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import trxsh.ontop.servercrystal.Main;

public class ToggleCrystals implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {

            sender.sendMessage(ChatColor.RED + "Only players are able to use this command.");
            return true;

        }

        Player p = (Player) sender;

        if(!Main.enableGlobalCrystals) {

            sender.sendMessage(ChatColor.RED + "Fast crystals are currently globally disabled.");
            return true;

        }

        boolean b = Main.players.get(p.getUniqueId());

        if(b) {

            Main.players.put(p.getUniqueId(), false);
            p.sendMessage(ChatColor.GRAY + "Fast crystals are now " + ChatColor.RED + "disabled.");

        } else {

            Main.players.put(p.getUniqueId(), true);
            p.sendMessage(ChatColor.GRAY + "Fast crystals are now " + ChatColor.GREEN + "enabled.");

        }

        return true;

    }

}
