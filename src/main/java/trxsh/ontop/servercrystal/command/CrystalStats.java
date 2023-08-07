package trxsh.ontop.servercrystal.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import trxsh.ontop.servercrystal.Main;

public class CrystalStats implements CommandExecutor {

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

        Player p = (Player) sender;

        boolean b = Main.stats.get(p.getUniqueId());

        if(b) {

            Main.stats.put(p.getUniqueId(), false);
            p.sendMessage(ChatColor.GRAY + "Your global crystal stats are now " + ChatColor.AQUA + "disabled.");

        } else {

            Main.stats.put(p.getUniqueId(), true);
            p.sendMessage(ChatColor.GRAY + "Your global crystal stats are now " + ChatColor.AQUA + "enabled.");

        }

        return true;

    }

}
