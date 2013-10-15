package fr.kyriog.bukkit.signtools;

import org.bukkit.World;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import fr.kyriog.bukkit.signtools.commands.UnsignCommand;

public class SignToolsPlugin extends JavaPlugin {
	@Override
	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		World defaultWorld = getServer().getWorlds().get(0);
		pm.registerEvents(new SignToolsListener(getLogger(), defaultWorld), this);

		getCommand("unsign").setExecutor(new UnsignCommand());
	}
}
