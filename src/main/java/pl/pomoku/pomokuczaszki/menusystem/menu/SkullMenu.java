package pl.pomoku.pomokuczaszki.menusystem.menu;

import org.bukkit.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import pl.pomoku.pomokuczaszki.PomokuCzaszki;
import pl.pomoku.pomokuczaszki.menusystem.PaginatedMenu;
import pl.pomoku.pomokuczaszki.menusystem.PlayerMenuUtility;
import pl.pomoku.pomokuczaszki.ymls_config.KillsYml;

import java.util.*;

public class SkullMenu extends PaginatedMenu {
    public SkullMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    @Override
    public String getMenuName() {
        return ChatColor.GREEN + "Gracze z czaszka " + ChatColor.DARK_GRAY + "Strona: " + ChatColor.AQUA + page;
    }

    @Override
    public int getSlots() {
        return 54;
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        ArrayList<OfflinePlayer> players = new ArrayList<>();

        KillsYml.reload();
        Set<String> keys = KillsYml.get().getConfigurationSection("kills.").getKeys(false);
        List<String> strings = new ArrayList<>(keys);

        for (String string : strings) {
            if (KillsYml.get().getBoolean("kills." + string + ".skull")) {
                if (!players.contains(Bukkit.getOfflinePlayer(UUID.fromString(string)))) {
                    players.add(Bukkit.getOfflinePlayer(UUID.fromString(string)));
                }
            }
        }

        switch (Objects.requireNonNull(e.getCurrentItem()).getType()){
            case PLAYER_HEAD:
                playerMenuUtility.setPlayerWithSkull(Bukkit.getOfflinePlayer(UUID.fromString(Objects.requireNonNull(Objects.requireNonNull(e.getCurrentItem().getItemMeta()).getPersistentDataContainer().get(new NamespacedKey(PomokuCzaszki.getPlugin(), "uuid"), PersistentDataType.STRING)))));
                new SkullConfirmMenu(playerMenuUtility).open();
                break;
            case BARRIER:
                e.getWhoClicked().closeInventory();
                break;
            case ARROW:
                if(ChatColor.stripColor(Objects.requireNonNull(e.getCurrentItem().getItemMeta()).getDisplayName()).equalsIgnoreCase("Poprzednia strona")){
                    if(page == 0){
                        e.getWhoClicked().sendMessage(ChatColor.GRAY + "Jestes juz na pierwszej stronie.");
                    }else {
                        page = page - 1;
                        super.open();
                    }
                }else if(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getDisplayName()).equalsIgnoreCase("Nastepna strona")){
                    if(!((index + 1) >= players.size())){
                        page = page + 1;
                        super.open();
                    }else {
                        e.getWhoClicked().sendMessage(ChatColor.GRAY + "Jestes juz na ostaniej stronie.");
                    }
                }

        }
    }

    @Override
    public void setMenuItems() {

        addMenuBorder();
        //The thing you will be looping through to place items
        ArrayList<OfflinePlayer> players = new ArrayList<>();

        KillsYml.reload();
        Set<String> keys = KillsYml.get().getConfigurationSection("kills.").getKeys(false);
        List<String> strings = new ArrayList<>(keys);

        for (String string : strings) {
            if (KillsYml.get().getBoolean("kills." + string + ".skull")) {
                if (!players.contains(Bukkit.getOfflinePlayer(UUID.fromString(string)))) {
                    players.add(Bukkit.getOfflinePlayer(UUID.fromString(string)));
                }
            }
        }

        // Pagination loop template
        if(players != null && !players.isEmpty()) {
            for(int i = 0; i < super.maxItemsPerPage; i++) {
                index = super.maxItemsPerPage * page + i;
                if(index >= players.size()) break;
                if (players.get(index) != null){

                    ItemStack playerItem = new ItemStack(Material.PLAYER_HEAD, 1);
                    ItemMeta playerMeta = playerItem.getItemMeta();
                    playerMeta.setDisplayName(ChatColor.RED + players.get(i).getName());

                    playerMeta.getPersistentDataContainer().set(
                            new NamespacedKey(PomokuCzaszki.getPlugin(), "uuid"),
                            PersistentDataType.STRING,
                            players.get(index).getUniqueId().toString()
                    );

                    playerItem.setItemMeta(playerMeta);

                    inventory.addItem(playerItem);

                }
            }
        }
    }
}
