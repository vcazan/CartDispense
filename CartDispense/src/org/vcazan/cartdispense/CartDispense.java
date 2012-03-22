package org.vcazan.cartdispense;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;



public class CartDispense extends JavaPlugin {

	Logger log = Logger.getLogger("Minecraft");
	private static CartDispense instance = null;
	private PluginDescriptionFile pdffile = this.getDescription();
	
	public static CartDispense getInstance() {
		return instance;
	}
	
	
	public void onEnable(){
		//Register with PluginManager and register events.
		PluginManager pm = getServer().getPluginManager();
		instance = this;
		
		pm.registerEvents(new CartBlockListener(), this);
		pm.registerEvents(new CartCartListener(), this);
		
		this.getConfig().options().copyDefaults(true);
		this.getConfig().options().copyHeader(true);
		this.saveConfig();
		
		//Output to console that we've loaded.
		log.info(pdffile.getName() + " v" + pdffile.getVersion() + " has loaded.");

	}
	
	public void loadConfig() {
		this.reloadConfig();
	}
	
	public void onDisable(){
		//Unload instance of CartDispense
		instance = null;
		
		//Output to console we're done here.
		log.info(pdffile.getName() + "  v" + pdffile.getVersion() + " has unloaded.");
	}
	
	//Check to see if we're sent a command
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = null;
		
		if(sender instanceof Player) {
			player = (Player)sender;
		}
		
		if(cmd.getName().equalsIgnoreCase("cartdispensereload")) {
			CartDispense.getInstance().reloadConfig();
			log.info(pdffile.getName() + " configuration reloaded!");
			if(player != null ) {
				player.sendMessage(ChatColor.BLUE + pdffile.getName() + " configuration reloaded");
			}
			return true;
		}
		
		return false;
	}
	
}