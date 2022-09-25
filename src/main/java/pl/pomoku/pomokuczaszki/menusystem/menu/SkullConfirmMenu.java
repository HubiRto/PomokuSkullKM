package pl.pomoku.pomokuczaszki.menusystem.menu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import pl.pomoku.pomokuczaszki.Api;
import pl.pomoku.pomokuczaszki.menusystem.Menu;
import pl.pomoku.pomokuczaszki.menusystem.PlayerMenuUtility;

import java.util.ArrayList;

public class SkullConfirmMenu extends Menu {
    public SkullConfirmMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return "Potwierdz: Usunac czaszke?";
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        switch (e.getCurrentItem().getType()){
            case EMERALD:
                OfflinePlayer target = super.playerMenuUtility.getPlayerWithSkull();
                Api.setSkull(target, false);

                e.getWhoClicked().sendMessage(ChatColor.GREEN + "Usunales czaszke gracza " + target.getName());
                new SkullMenu(playerMenuUtility).open();
                break;
            case BARRIER:
                new SkullMenu(playerMenuUtility).open();
                break;
        }
    }

    @Override
    public void setMenuItems() {

        ItemStack yes = new ItemStack(Material.EMERALD, 1);
        ItemMeta yes_meta = yes.getItemMeta();
        yes_meta.setDisplayName(ChatColor.GREEN + "Tak");
        ArrayList<String> yes_lore = new ArrayList<>();
        yes_lore.add(ChatColor.AQUA + "Czy chcesz, aby ten ");
        yes_lore.add(ChatColor.AQUA + "gracz nie mial juz czaszki?");
        yes_meta.setLore(yes_lore);
        yes.setItemMeta(yes_meta);
        ItemStack no = new ItemStack(Material.BARRIER, 1);
        ItemMeta no_meta = no.getItemMeta();
        no_meta.setDisplayName(ChatColor.DARK_RED + "Nie");
        no.setItemMeta(no_meta);

        inventory.setItem(3, yes);
        inventory.setItem(5, no);

    }
}
