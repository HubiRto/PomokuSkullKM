package pl.pomoku.pomokuczaszki;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import pl.pomoku.pomokuczaszki.ymls_config.KillsYml;

public class Api {
    public static void setSkull(OfflinePlayer p, Boolean b){
        KillsYml.get().set("kills." + p.getUniqueId() + "." + "skull", b);
        KillsYml.save();
    }
    public static void setSkull(Player p, Boolean b){
        KillsYml.get().set("kills." + p.getUniqueId() + "." + "skull", b);
        KillsYml.save();
    }
    public static boolean haveSkull(Player p){
        return KillsYml.get().getBoolean("kills." + p.getUniqueId() + "." + "skull");
    }
}
