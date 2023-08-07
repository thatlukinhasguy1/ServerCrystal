package trxsh.ontop.servercrystal.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import trxsh.ontop.servercrystal.Main;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {

        Player p = e.getPlayer();

        if(!Main.players.containsKey(p.getUniqueId())) {

            Main.players.put(p.getUniqueId(), false);
            p.sendMessage(ChatColor.GRAY + "Fast crystals is now " + ChatColor.RED + "disabled." + ChatColor.GRAY + " Use /togglefastcrystals to toggle fast crystals.");

        } else {

            boolean b = Main.players.get(p.getUniqueId());

            if (b) {
                p.sendMessage(ChatColor.GRAY + "Fast crystals is now " + ChatColor.GREEN + "enabled." + ChatColor.GRAY + " Use /togglefastcrystals to toggle fast crystals.");
            } else {
                p.sendMessage(ChatColor.GRAY + "Fast crystals is now " + ChatColor.RED + "disabled." + ChatColor.GRAY + " Use /togglefastcrystals to toggle fast crystals.");
            }

        }

    }

}
