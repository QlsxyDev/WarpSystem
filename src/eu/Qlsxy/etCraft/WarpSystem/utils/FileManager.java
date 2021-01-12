package eu.Qlsxy.etCraft.WarpSystem.utils;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public class FileManager {

	private final File f;
	private final YamlConfiguration c;
	FileManager fm;

	public FileManager(String FilePath, String FileName) {
		this.f = new File(FilePath, FileName);
		this.c = YamlConfiguration.loadConfiguration(this.f);
	}

	public void setValue(String ValuePath, Object Value) {
		c.set(ValuePath, Value);
	}

	public boolean exist() {
		return f.exists();
	}

	public int getInt(String ValuePath) {
		return c.getInt(ValuePath);
	}

	public String getString(String ValuePath) {
		return c.getString(ValuePath);
	}

	public Double getDouble(String ValuePath) {
		return c.getDouble(ValuePath);
	}

	public Set<String> getKeys(boolean key) {
		return c.getKeys(key);
	}

	public long getLong(String ValuePath) {
		return c.getLong(ValuePath);
	}

	public void getMessage(Player p, String ValuePath, String Replace, String Replacement) {
		fm = new FileManager("plugins//WarpSystem//", "Config.yml");
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', ValuePath.replace(Replace, Replacement)
				.replace("${player}", p.getDisplayName()).replace("${perm}", fm.getString("Permission.UseWarps"))));
	}

	public void getMessageTp(Player p, String ValuePath, String Replace, String Replacement, String ReplaceCD,
			String ReplacementCD) {
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', ValuePath.replace(Replace, Replacement)
				.replace("${player}", p.getDisplayName()).replace(ReplaceCD, ReplacementCD)));
	}

	public void getMessagePerm(Player p, String ValuePath, String Replace, String Replacement, String Permission,
			String ReplacementPerm) {
		p.sendMessage(ChatColor.translateAlternateColorCodes('&', ValuePath.replace(Replace, Replacement)
				.replace("${player}", p.getDisplayName()).replace(Permission, ReplacementPerm)));
	}

	public void save() {
		try {
			this.c.save(this.f);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
