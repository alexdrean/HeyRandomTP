package fr.HeyFactions.HeyRandomTP;

import eu.BullCheat.net.cubespace.Yamler.Config.ConfigMode;
import eu.BullCheat.net.cubespace.Yamler.Config.YamlConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Créé par alexandre le 02/06/17.
 */
class Conf extends YamlConfig {

    Conf(JavaPlugin plugin) {
        CONFIG_FILE = new File(plugin.getDataFolder(), "config.yml");
        CONFIG_MODE = ConfigMode.PATH_BY_UNDERSCORE;
    }

    static int minRadius = 1000;
    static int maxRadius = 4000;
    static String world = Bukkit.getWorlds().get(0).getName();
    static int maxHeight = 100;
    static String msg_success = "§aVous avez été téléporté en %x %y %z (distance = %dstm)";

}
