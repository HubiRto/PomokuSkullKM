package pl.pomoku.pomokuczaszki.cmds.tabcompliter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import pl.pomoku.pomokuczaszki.PomokuCzaszki;

import java.util.ArrayList;
import java.util.List;

public class SkullGetSetHaveCompliter implements TabCompleter {
    private final PomokuCzaszki plugin;

    public SkullGetSetHaveCompliter(PomokuCzaszki plugin) {
        this.plugin = plugin;
        plugin.getCommand("czaszka").setTabCompleter(this);
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(command.getName().equalsIgnoreCase("czaszka")){
            if(args.length == 1){
                List<String> arguments = new ArrayList<>();
                arguments.add("have");
                arguments.add("remove");
                arguments.add("set");
                return arguments;
            }
        }
        return null;
    }
}
