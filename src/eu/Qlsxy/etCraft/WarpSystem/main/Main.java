package eu.Qlsxy.etCraft.WarpSystem.main;

import eu.Qlsxy.etCraft.WarpSystem.commands.WarpCommand;
import eu.Qlsxy.etCraft.WarpSystem.utils.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class Main extends JavaPlugin {

	public static Main instance;
	FileManager fm;

	@Override
	public void onEnable() {
		instance = this;

		Objects.requireNonNull(getCommand("warp")).setExecutor(new WarpCommand());
		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new WarpCommand(), this);

		fm = new FileManager("plugins//WarpSystem//", "Config.yml");
		if (!fm.exist()) {
			fm.setValue("Plugin.Name", instance.getDescription().getName());
			fm.setValue("Plugin.Version", instance.getDescription().getVersion());
			fm.setValue("Plugin.Author", "Qlsxy");
			fm.setValue("Plugin.Website", instance.getDescription().getWebsite());
			fm.setValue("Permission.SetupWarps", "Warps.Admin");
			fm.setValue("Permission.UseWarps", "Warps.Use");
			fm.setValue("Messages.NoWarpsSet", "&cThere are currently no Warps!");
			fm.setValue("Messages.WarpDoesNotExist", "&cThe Warp ${warp} does not exist!");
			fm.setValue("Messages.setWarp", "&7You have set the Warp &e${warp} &7with the permission &e${perm}&7!");
			fm.setValue("Messages.setPermWarp", "&7You have set the Warp &e${warp}&7 with the permission &e${perm}&7!");
			fm.setValue("Messages.deleteWarp", "&7You have deleted the Warp &e${warp}&7!");
			fm.setValue("Messages.WrongInput", "&cUse /warp <name>");
			fm.setValue("Messages.NoPerms", "&cYou have no permission to do that!");
			fm.setValue("Messages.NoPermsToWarp", "&cYou have no permission to teleport there!");
			fm.setValue("WarpsCMD.Messages", "&7Warps: ");
			fm.setValue("WarpsCMD.WarpsColor", "&e");
			fm.setValue("WarpsCMD.WarpsSeparator", "&7, ");
			fm.setValue("Teleport.Messages.Cancelled", "&cThe teleport has been canceled!");
			fm.setValue("Teleport.Messages.Countdown", "&7You will be teleported in &e${time} Seconds&7!");
			fm.setValue("Teleport.Messages.Done", "&7You have been teleported to &e${warp}&7!");
			fm.setValue("Teleport.Settings.Countdown", 3);
			fm.save();
		}
		getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "The WarpSystem has been successfully activated!");
	}

	@Override
	public void onDisable() {
		getServer().getConsoleSender().sendMessage(ChatColor.RED + "The WarpSystem has been deactivated!");
	}
}