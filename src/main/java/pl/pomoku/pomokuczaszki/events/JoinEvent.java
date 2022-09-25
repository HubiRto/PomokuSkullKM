package pl.pomoku.pomokuczaszki.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import pl.pomoku.pomokuczaszki.PomokuCzaszki;
import pl.pomoku.pomokuczaszki.ymls_config.KillsYml;

import java.util.ArrayList;
import java.util.List;

public class JoinEvent implements Listener {
    private final PomokuCzaszki plugin;

    public JoinEvent(PomokuCzaszki plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void OnJoin(PlayerJoinEvent e){
        if(KillsYml.get().getString("kills." + e.getPlayer().getUniqueId()) == null) {
            KillsYml.get().set("kills." + e.getPlayer().getUniqueId() + ".player", e.getPlayer());
            KillsYml.get().set("kills." + e.getPlayer().getUniqueId() + ".name", e.getPlayer().getDisplayName());
            KillsYml.get().set("kills." + e.getPlayer().getUniqueId() + ".skull", false);
            KillsYml.save();
        }
    }
}
