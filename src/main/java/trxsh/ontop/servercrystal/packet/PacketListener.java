package trxsh.ontop.servercrystal.packet;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.ListeningWhitelist;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketEvent;
import net.minecraft.network.protocol.game.ClientboundRemoveEntitiesPacket;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerPlayerConnection;
import net.minecraft.world.entity.boss.enderdragon.EndCrystal;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftEnderCrystal;
import org.bukkit.craftbukkit.v1_20_R1.entity.CraftPlayer;
import org.bukkit.entity.EnderCrystal;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import trxsh.ontop.servercrystal.Main;

import java.util.UUID;

public class PacketListener {

    public PacketListener() { }

    public void listen() {

        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(Main.Instance, ListenerPriority.HIGHEST,
                PacketType.Play.Client.USE_ENTITY,
                PacketType.Play.Client.ARM_ANIMATION,
                PacketType.Play.Client.USE_ITEM) {
            @Override
            public void onPacketReceiving(PacketEvent event) {

                if(event.getPacketType() == PacketType.Play.Client.USE_ENTITY
                        || event.getPacketType() == PacketType.Play.Client.USE_ITEM
                        || event.getPacketType() == PacketType.Play.Client.ARM_ANIMATION) {

                    long pre = System.currentTimeMillis();

                    Player p = event.getPlayer();

                    for(Entity entity : p.getLocation().getChunk().getEntities()) {

                        if(entity instanceof EnderCrystal) {

                            if(p.hasLineOfSight(entity)) {

                                if(!Main.enableGlobalCrystals)
                                    return;

                                ServerPlayer sp = ((CraftPlayer) p).getHandle();
                                ServerPlayerConnection c = sp.connection;

                                EndCrystal NmsEntity = ((CraftEnderCrystal) entity).getHandle();

                                c.send(new ClientboundRemoveEntitiesPacket(NmsEntity.getId()));

                                NmsEntity.kill();
                                NmsEntity.remove(net.minecraft.world.entity.Entity.RemovalReason.KILLED);
                                NmsEntity.onClientRemoval();

                                long post = System.currentTimeMillis();

                                for(UUID id : Main.stats.keySet()) {

                                    Player player = Bukkit.getPlayer(id);

                                    boolean bl = Main.stats.get(id);

                                    if(player != null)
                                        if(player.isOnline())
                                            if(player.isOp())
                                                if (bl) {

                                                    long elapsed = (post - pre);

                                                    p.sendMessage(ChatColor.AQUA + p.getName() + ChatColor.GRAY + " used fast crystal (PacketRawAlgorhythm) (" + ChatColor.AQUA + elapsed + "ms" + ChatColor.GRAY + ")");

                                                }

                                }

                            }

                        }

                    }

                }

            }
        });

    }

}
