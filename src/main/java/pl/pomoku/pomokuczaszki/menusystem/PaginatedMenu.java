package pl.pomoku.pomokuczaszki.menusystem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class PaginatedMenu extends Menu{
    protected int page = 0;
    protected int maxItemsPerPage = 9;
    protected int index = 0;

    public PaginatedMenu(PlayerMenuUtility playerMenuUtility) {
        super(playerMenuUtility);
    }

    public void addMenuBorder(){
        ItemStack left = new ItemStack(Material.ARROW, 1);
        ItemMeta left_meta = left.getItemMeta();
        left_meta.setDisplayName(ChatColor.GREEN + "Poprzednia strona");
        left.setItemMeta(left_meta);
        
        inventory.setItem(48, left);

        ItemStack right = new ItemStack(Material.ARROW, 1);
        ItemMeta right_meta = right.getItemMeta();
        right_meta.setDisplayName(ChatColor.GREEN + "Nastepna strona");
        right.setItemMeta(right_meta);

        inventory.setItem(50, right);

        ItemStack close = new ItemStack(Material.BARRIER, 1);
        ItemMeta close_meta = close.getItemMeta();
        close_meta.setDisplayName(ChatColor.RED + "Zamknij");
        close.setItemMeta(close_meta);

        inventory.setItem(49, close);

        for(int i = 0; i < 10; i++){
            if(inventory.getItem(i) == null){
                inventory.setItem(i, super.FILLER_GLASS);
            }
        }

        inventory.setItem(17, super.FILLER_GLASS);
        inventory.setItem(18, super.FILLER_GLASS);
        inventory.setItem(26, super.FILLER_GLASS);
        inventory.setItem(27, super.FILLER_GLASS);
        inventory.setItem(35, super.FILLER_GLASS);
        inventory.setItem(36, super.FILLER_GLASS);

        for(int i = 44; i < 54; i++){
            if(inventory.getItem(i) == null){
                inventory.setItem(i, super.FILLER_GLASS);
            }
        }
    }
}
