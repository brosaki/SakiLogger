package net.manosaki.utils;

import org.bukkit.Location;

public class LocationSerializer {
    public static String getSerializedLocation(Location loc) {
        return loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + " " + loc.getWorld().getName();
    }
}