package pl.pomoku.pomokuczaszki.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.pomoku.pomokuczaszki.PomokuCzaszki;
import pl.pomoku.pomokuczaszki.menusystem.menu.SkullMenu;

import java.util.Objects;

public class SkullGuiCmd implements CommandExecutor {
    private final PomokuCzaszki plugin;

    public SkullGuiCmd(PomokuCzaszki plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("czaszki")).setExecutor(this);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player p){
            new SkullMenu(PomokuCzaszki.getPlayerMenuUtility(p)).open();
        }
        return true;
    }
}
