package pl.pomoku.pomokuczaszki.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;
import pl.pomoku.pomokuczaszki.PomokuCzaszki;
import pl.pomoku.pomokuczaszki.menusystem.Menu;

public class MenuListener implements Listener {
    private final PomokuCzaszki plugin;

    public MenuListener(PomokuCzaszki plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMenuClick(InventoryClickEvent e){
        InventoryHolder holder = e.getClickedInventory().getHolder();
        if(holder instanceof Menu){
            e.setCancelled(true);

            if(e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR){
                return;
            }

            Menu menu = (Menu) holder;
            menu.handleMenu(e);
        }
    }
}
