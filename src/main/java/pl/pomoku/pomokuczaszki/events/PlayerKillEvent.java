package pl.pomoku.pomokuczaszki.events;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import pl.pomoku.pomokuczaszki.Api;
import pl.pomoku.pomokuczaszki.PomokuCzaszki;
import pl.pomoku.pomokuczaszki.ymls_config.KillsYml;

public class PlayerKillEvent implements Listener {
    private final PomokuCzaszki plugin;

    public PlayerKillEvent(PomokuCzaszki plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void OnPlayerKill(EntityDeathEvent e){
        if(e.getEntity().getKiller() != null) {
            if (e.getEntity().getType() == EntityType.PLAYER) {
                if (e.getEntity().getKiller().getType() == EntityType.PLAYER) {
                    //Set economy
                    Economy eco = PomokuCzaszki.getEconomy();
                    //Add killer and killed player's
                    Player killed = (Player) e.getEntity();
                    Player killer = e.getEntity().getKiller();

                    if (KillsYml.get().getBoolean("kills." + killed.getUniqueId() + "." + "skull")) {
                        EconomyResponse response = eco.depositPlayer(killer, 100);
                        if(response.transactionSuccess()) {
                            Api.setSkull(killed, false);
                            for (Player p : plugin.getServer().getOnlinePlayers()) {
                                p.sendMessage(ChatColor.GRAY + "Gracz " + ChatColor.GREEN + killer.getDisplayName() + ChatColor.GRAY + " zabil agresywnego gracza " + ChatColor.RED + killed.getDisplayName() + ChatColor.GREEN + " - nagroda 100$");
                            }
                            killed.setPlayerListName(killed.getName());
                        }
                    }else {
                        EconomyResponse response = eco.withdrawPlayer(killer, 100);
                        if(response.transactionSuccess()) {
                            Api.setSkull(killed, true);
                            for (Player p : plugin.getServer().getOnlinePlayers()) {
                                p.sendMessage(ChatColor.GRAY + "Gracz " + ChatColor.RED + killer.getDisplayName() + ChatColor.GRAY + " zabil pokojowego gracza " + ChatColor.GREEN + killed.getDisplayName() + ChatColor.RED + " - kara 100$ i czaszka");
                            }
                            killer.sendTitle("☠", "", 0, 20*2, (int) (20*0.5));
                            killer.setPlayerListName(killer.getName() + " ☠");
                        }
                    }
                    KillsYml.save();
                }
            }
        }
    }
}
