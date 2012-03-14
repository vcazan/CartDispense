package org.vcazan.cartdispense;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CartDispense extends JavaPlugin {

	Logger log = Logger.getLogger("Minecraft");
	private static CartDispense instance = null;
	
	public static CartDispense getInstance() {
		return instance;
	}
	
	public void onEnable(){
		PluginManager pm = getServer().getPluginManager();
		instance = this;
		
		pm.registerEvents(new CartBlockListener(), this);
		pm.registerEvents(new CartCartListener(), this);
		
		this.getConfig().options().copyDefaults(true);
		this.getConfig().options().copyHeader(true);
		this.saveConfig();
		
		log.info("CartDispense v0.12 has loaded.");

	}
	
	public void loadConfig() {
		this.reloadConfig();
	}
	
	public void onDisable(){
		instance = null;
		log.info("CartDispense v0.12 has unloaded.");
	}
	
}