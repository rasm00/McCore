package net.justcoded.mc_core.utilities;

import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

public class Sounds {

    public static void playSound(List<Player> players, Sound sound) {
        players.forEach(p -> p.playSound(p.getLocation(), sound, 3, 1));
    }

    public static void playSound(List<Player> players, Sound sound, int volume, int pitch) {
        players.forEach(p -> p.playSound(p.getLocation(), sound, volume, pitch));
    }
}
