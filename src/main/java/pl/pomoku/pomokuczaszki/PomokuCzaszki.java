package pl.pomoku.pomokuczaszki;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import pl.pomoku.pomokuczaszki.cmds.SkullGetSetHave;
import pl.pomoku.pomokuczaszki.cmds.SkullGuiCmd;
import pl.pomoku.pomokuczaszki.cmds.tabcompliter.SkullGetSetHaveCompliter;
import pl.pomoku.pomokuczaszki.events.JoinEvent;
import pl.pomoku.pomokuczaszki.events.MenuListener;
import pl.pomoku.pomokuczaszki.events.PlayerKillEvent;
import pl.pomoku.pomokuczaszki.menusystem.PlayerMenuUtility;
import pl.pomoku.pomokuczaszki.ymls_config.KillsYml;

import java.util.HashMap;
import java.util.logging.Logger;

public final class PomokuCzaszki extends JavaPlugin {

    private static PomokuCzaszki plugin;

    private static final Logger log = Logger.getLogger("Minecraft");
    private static Economy econ = null;

    private static final HashMap<Player, PlayerMenuUtility> playerMenuUtilityMap = new HashMap<>();

    public static PomokuCzaszki getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {

        plugin = this;

        if (!setupEconomy() ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        KillsYml.setup(this);
        KillsYml.save();

        new SkullGuiCmd(this);
        new SkullGetSetHave(this);

        new SkullGetSetHaveCompliter(this);

        getServer().getPluginManager().registerEvents(new PlayerKillEvent(this), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(this), this);
        getServer().getPluginManager().registerEvents(new MenuListener(this), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static PlayerMenuUtility getPlayerMenuUtility(Player p){
        PlayerMenuUtility playerMenuUtility;

        if(playerMenuUtilityMap.containsKey(p)){
            return playerMenuUtilityMap.get(p);
        }else {
            playerMenuUtility = new PlayerMenuUtility(p);
            playerMenuUtilityMap.put(p, playerMenuUtility);
            return playerMenuUtility;
        }
    }
}
