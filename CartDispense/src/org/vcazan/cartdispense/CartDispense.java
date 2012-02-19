package org.vcazan.cartdispense;

import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CartDispense extends JavaPlugin {

	Logger log = Logger.getLogger("Minecraft");
    
	public void onEnable(){
		PluginManager pm = getServer().getPluginManager();
		
		pm.registerEvents(new CartBlockListener(), this);
		pm.registerEvents(new CartCartListener(), this);
		
		log.info("CartDispense v0.10f has loaded.");

	}
	
	public void onDisable(){
		log.info("CartDispense v0.10f has unloaded.");
	}
	
}