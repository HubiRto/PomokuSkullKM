package pl.pomoku.pomokuczaszki.ymls_config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import pl.pomoku.pomokuczaszki.PomokuCzaszki;

import java.io.File;
import java.io.IOException;

public class KillsYml {
    private static File file;
    private static FileConfiguration configFile;

    public static void setup(Plugin plugin) {
        if(plugin.getDataFolder().exists()) {
            file = new File(plugin.getDataFolder(), "kills.yml");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else {
            plugin.getDataFolder().mkdir();
            file = new File(plugin.getDataFolder(), "kills.yml");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        configFile = YamlConfiguration.loadConfiguration(file);
    }
    public static FileConfiguration get(){
        return configFile;
    }
    public static void save() {
        try{
            configFile.save(file);
        }catch (IOException e) {
            System.out.println("Nie mozna zapisac pliku!");
        }
    }
    public static void reload() {
        configFile = YamlConfiguration.loadConfiguration(file);
    }
}
