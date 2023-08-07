package trxsh.ontop.servercrystal.listener;

import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAnimationEvent;
import org.bukkit.event.player.PlayerAnimationType;
import trxsh.ontop.servercrystal.Main;

public class ArmEvent implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onAnimation(PlayerAnimationEvent e) {

        if(e.getAnimationType() == PlayerAnimationType.ARM_SWING) {

            for(Entity entity : e.getPlayer().getNearbyEntities(4, 4, 4)) {

                if(entity instanceof EnderCrystal) {

                    if(e.getPlayer().hasLineOfSight(entity)) {

                        if(!Main.enableGlobalCrystals)
                            return;

                        Main.crystalSweep.crystals.add(entity);

                    }

                }

            }

        }

    }

}
