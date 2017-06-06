package fr.HeyFactions.HeyRandomTP;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

/**
 * Créé par alexandre le 02/06/17.
 */
public class HeyRandomTP extends JavaPlugin implements CommandExecutor {

    @Override
    public void onEnable() {
        try {
            new Conf(this).init();
        } catch (Throwable t) {
            t.printStackTrace();
        }
        getCommand("randomtp").setExecutor(this);
    }

    public static Location getRandomLocation() {
        Random random = new Random();
        double angle = random.nextDouble() * 2 * Math.PI;
        int x = (int) (Math.cos(angle) * random.nextInt(Conf.maxRadius - Conf.minRadius) + Conf.minRadius);
        int z = (int) (Math.sin(angle) * random.nextInt(Conf.maxRadius - Conf.minRadius) + Conf.minRadius);
        Location l = getValidY(x, z);
        return l == null ? getRandomLocation() : l;
    }

    private static Location getValidY(int x, int z) {
        Location location = new Location(Bukkit.getWorld(Conf.world), x, 0, z);
        switch (location.getBlock().getBiome()) {
            case SKY:
            case HELL:
            case OCEAN:
            case RIVER:
            case JUNGLE:
            case JUNGLE_EDGE:
            case MUTATED_JUNGLE:
            case MUTATED_JUNGLE_EDGE:
            case JUNGLE_HILLS:
            case DEEP_OCEAN:
            case FROZEN_OCEAN:
            case EXTREME_HILLS:
            case BIRCH_FOREST_HILLS:
            case DESERT_HILLS:
            case EXTREME_HILLS_WITH_TREES:
            case FOREST_HILLS:
            case MUTATED_BIRCH_FOREST_HILLS:
            case MUTATED_EXTREME_HILLS:
            case MUTATED_EXTREME_HILLS_WITH_TREES:
            case MUTATED_REDWOOD_TAIGA_HILLS:
            case REDWOOD_TAIGA_HILLS:
            case SMALLER_EXTREME_HILLS:
            case TAIGA_COLD_HILLS:
            case TAIGA_HILLS:
                return null;
        }
        while (location.getBlock().getLightFromSky() < 3) location = location.add(0, 1, 0);
        if (location.getBlockY() > Conf.maxHeight) return null;
        return location;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Location l = getRandomLocation();
        String dst = String.valueOf((int) ((Player)sender).getLocation().distance(l));
        ((Player) sender).teleport(l);
        ((Player) sender).sendMessage(Conf.msg_success
                .replaceAll("%x", String.valueOf(l.getBlockX()))
                .replaceAll("%y", String.valueOf(l.getBlockY()))
                .replaceAll("%z", String.valueOf(l.getBlockZ()))
                .replaceAll("%dst", dst));
        return true;
    }


}
