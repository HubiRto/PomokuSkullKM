package pl.pomoku.pomokuczaszki.cmds;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.pomoku.pomokuczaszki.Api;
import pl.pomoku.pomokuczaszki.PomokuCzaszki;

import java.util.Objects;

public class SkullGetSetHave implements CommandExecutor {
    private final PomokuCzaszki plugin;

    public SkullGetSetHave(PomokuCzaszki plugin) {
        this.plugin = plugin;
        Objects.requireNonNull(plugin.getCommand("czaszka")).setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0){
            if(sender.hasPermission("czaszki.all") || sender.hasPermission("czaszki.listakomend")) {
                Player p = (Player) sender;
                p.sendMessage("Lista komend:");
                p.sendMessage("/czaszak have [player] - pokazuje czy gracz ma czaszke");
                p.sendMessage("/czaszak remove [player] - usuwa graczowi czaszke");
                p.sendMessage("/czaszak set [player] - daje graczowi czaszke");
            }else {
                sender.sendMessage(ChatColor.RED + "Nie masz uprawnien do tej komendy!");
            }
        }else if(args.length == 2){
            Player target = Bukkit.getPlayer(args[1]);
            if(target != null) {
                switch (args[0]) {
                    case "have":
                        if(sender.hasPermission("czaszki.all") || sender.hasPermission("czaszki.have")) {
                            if (Api.haveSkull(target)) {
                                sender.sendMessage("Gracz " + target.getDisplayName() + " ma czaszke.");
                            } else {
                                sender.sendMessage("Gracz " + target.getDisplayName() + " nie ma czaszki.");
                            }
                        }else {
                            sender.sendMessage(ChatColor.RED + "Nie masz uprawnien do tej komendy!");
                        }
                        break;
                    case "remove":
                        if(sender.hasPermission("czaszki.all") || sender.hasPermission("czaszki.remove")) {
                            if (Api.haveSkull(target)) {
                                Api.setSkull(target, false);
                                target.setPlayerListName(target.getName());
                                sender.sendMessage("Usunales czaszke graczowi " + target.getDisplayName());
                                target.sendMessage("Gracz " + sender.getName() + " usunal ci czaszke.");
                            } else {
                                sender.sendMessage("Nie mozesz tego zrobic, bo gracz nie posiada czaszki");
                            }
                        }else {
                            sender.sendMessage(ChatColor.RED + "Nie masz uprawnien do tej komendy!");
                        }
                        break;
                    case "set":
                        if(sender.hasPermission("czaszki.all") || sender.hasPermission("czaszki.set")) {
                            if (Api.haveSkull(target)) {
                                sender.sendMessage("Ten gracz juz posiada czaszke.");
                            } else {
                                Api.setSkull(target, true);
                                sender.sendMessage("Dodales graczowi " + target.getDisplayName() + " czaszke.");
                                target.sendTitle("☠", "", 0, 20 * 2, (int) (20 * 0.5));
                                target.setPlayerListName(target.getName() + " ☠");
                            }
                        }else {
                            sender.sendMessage(ChatColor.RED + "Nie masz uprawnien do tej komendy!");
                        }
                }
            }
        }
        return true;
    }
}
