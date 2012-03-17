package org.vcazan.cartdispense;

import java.util.logging.Logger;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class CartDispense extends JavaPlugin {

	Logger log = Logger.getLogger("Minecraft");
	private static CartDispense instance = null;
	private static final float plugin_version = 0.13F; 
	
	public static CartDispense getInstance() {
		return instance;
	}
	
	public static float getVersion() {
		return plugin_version;
	}
	
	
	public void onEnable(){
		PluginManager pm = getServer().getPluginManager();
		instance = this;
		
		pm.registerEvents(new CartBlockListener(), this);
		pm.registerEvents(new CartCartListener(), this);
		
		this.getConfig().options().copyDefaults(true);
		this.getConfig().options().copyHeader(true);
		this.saveConfig();
		
		log.info("CartDispense v" + CartDispense.getVersion() + " has loaded.");

	}
	
	public void loadConfig() {
		this.reloadConfig();
	}
	
	public void onDisable(){
		instance = null;
		log.info("CartDispense v" + CartDispense.getVersion() + " has unloaded.");
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = null;
		
		if(sender instanceof Player) {
			player = (Player)sender;
		}
		
		if(cmd.getName().equalsIgnoreCase("cartdispensereload")) {
			CartDispense.getInstance().reloadConfig();
			log.info("CartDispense v" + CartDispense.getVersion() + " configuration reloaded!");
			if(player != null ) {
				player.sendMessage("CartDispense configuration reloaded");
			}
			return true;
		}
		
		return false;
	}
	
}