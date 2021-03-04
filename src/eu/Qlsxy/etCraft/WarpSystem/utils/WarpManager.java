package eu.Qlsxy.etCraft.WarpSystem.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.Set;

public class WarpManager {

	FileManager fw;

	public WarpManager() {
		fw = new FileManager("plugins//WarpSystem//", "Warps.yml");
	}

	public boolean exist(String name) {
		return fw.getString(name.toLowerCase()) != null;
	}

	public void addWarp(Location loc, String nameL, String nameU, String permission) {
		if(loc != null && loc.getWorld() != null) {
			fw.setValue(nameL + ".displayname", nameU);
			fw.setValue(nameL + ".world", loc.getWorld().getName());
			fw.setValue(nameL + ".x", loc.getX());
			fw.setValue(nameL + ".y", loc.getY());
			fw.setValue(nameL + ".z", loc.getZ());
			fw.setValue(nameL + ".yaw", loc.getYaw());
			fw.setValue(nameL + ".pitch", loc.getPitch());
			fw.setValue(nameL + ".permission", permission);
			fw.setValue(nameL + ".Uses", 0);
			fw.save();
		}
	}

	public Set<String> getWarps() {
		return fw.getKeys(false);
	}

	public void delWarp(String name) {
		fw.setValue(name, null);
		fw.save();
	}

	public void addUse(String name) {
		fw.setValue(name + ".Uses", fw.getInt(name + ".Uses") + 1);
		fw.save();
	}

	public Location getLocation(String name) {
		return new Location(Bukkit.getWorld(fw.getString(name + ".world")), fw.getDouble(name + ".x"),
				fw.getDouble(name + ".y"), fw.getDouble(name + ".z"), fw.getLong(name + ".yaw"),
				fw.getLong(name + ".pitch"));
	}

}