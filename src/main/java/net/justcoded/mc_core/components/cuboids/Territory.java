package net.justcoded.mc_core.components.cuboids;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Territory implements Serializable {

    private String world;

    private CLocation min;
    private CLocation max;

    public Territory(Location firstLocation, Location secondLocation) {
        sortLocations(firstLocation, secondLocation);
    }

    private void sortLocations(Location location, Location location2) {
        this.world = location.getWorld().getName();
        int xMin = Math.min(location.getBlockX(), location2.getBlockX());
        int yMin = Math.min(location.getBlockY(), location2.getBlockY());
        int zMin = Math.min(location.getBlockZ(), location2.getBlockZ());

        int xMax = Math.max(location.getBlockX(), location2.getBlockX());
        int yMax = Math.max(location.getBlockY(), location2.getBlockY());
        int zMax = Math.max(location.getBlockZ(), location2.getBlockZ());
        this.min = new CLocation(new Location(Bukkit.getWorld(world), xMin, yMin, zMin));
        this.max = new CLocation(new Location(Bukkit.getWorld(world), xMax, yMax, zMax));
    }

    private boolean isLocationInside3d(Location location) {
        int x = location.getBlockX();
        int y = location.getBlockY();
        int z = location.getBlockZ();
        World world = location.getWorld();

        return  (! (x >= max.getX() ||
                y >= max.getY() ||
                z >= max.getZ() ||
                x <= min.getX() ||
                y <= min.getY() ||
                z <= min.getZ() ||
                !Objects.equals(world, Bukkit.getWorld(this.world))));
    }

    private boolean isLocationInside2d(Location location) {
        int x = location.getBlockX();
        int z = location.getBlockZ();
        World world = location.getWorld();

        return  (! (x >= max.getX() ||
                z >= max.getZ() ||
                x <= min.getX() ||
                z <= min.getZ() ||
                !Objects.equals(world, Bukkit.getWorld(this.world))));
    }

    public boolean isLocationInsideTerritory3d(Location location) {
        return isLocationInside3d(location);
    }

    public boolean isLocationInsideTerritory2d(Location location) {
        return isLocationInside2d(location);
    }

    public String getWorld() {
        return world;
    }

    public CLocation getMin() {
        return min;
    }

    public CLocation getMax() {
        return max;
    }

    @Override
    public String toString() {
        return "world: " + world +
                " x1: " + min.getX() +
                " y1: " + min.getY() +
                " z1: " + min.getZ() +
                " x2: " + max.getX() +
                " y2: " + max.getY() +
                " z2: " + max.getZ();
    }

    public List<String> toStringArray() {
        return Arrays.asList("world: " + world,
                " x1: " + min.getX(),
                " y1: " + min.getY(),
                " z1: " + min.getZ(),
                " x2: " + max.getX(),
                " y2: " + max.getY(),
                " z2: " + max.getZ());
    }
}
