package trxsh.ontop.servercrystal.loop;

import net.minecraft.network.protocol.game.ClientboundRemoveEntitiesPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerPlayerConnection;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEnderCrystal;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import trxsh.ontop.servercrystal.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CrystalSweepLoop {

    public List<Entity> crystals = new ArrayList<>();

    public CrystalSweepLoop() { }

    public void start() {

        Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.Instance, new Runnable() {
            @Override
            public void run() {

                if(!Main.enableGlobalCrystals)
                    return;

                for(Entity crystal : crystals) {

                    long pre = System.currentTimeMillis();

                    if(crystal.isDead()) {

                        EndCrystal NmsEntity = ((CraftEnderCrystal) crystal).getHandle();

                        for(Player p : Bukkit.getOnlinePlayers()) {

                            ServerPlayer sp = ((CraftPlayer) p).getHandle();
                            ServerPlayerConnection c = sp.connection;

                            c.send(new ClientboundRemoveEntitiesPacket(NmsEntity.getId()));

                        }

                        NmsEntity.kill();
                        NmsEntity.remove(net.minecraft.world.entity.Entity.RemovalReason.KILLED);
                        NmsEntity.onClientRemoval();

                        long post = System.currentTimeMillis();

                        for(UUID id : Main.stats.keySet()) {

                            Player p = Bukkit.getPlayer(id);

                            boolean bl = Main.stats.get(id);

                            if(p != null)
                                if(p.isOnline())
                                    if(p.isOp())
                                        if (bl) {

                                            long elapsed = (post - pre);

                                            p.sendMessage(ChatColor.GRAY + "crystal was swept (" + ChatColor.AQUA + elapsed + "ms" + ChatColor.GRAY + ")");

                                        }

                        }

                    }

                }

                crystals.clear();

            }
        }, 1L, 1L);

    }

}
