package org.vcazan.cartdispense;

import java.util.logging.Logger;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CartDispense extends JavaPlugin {
	
    private final CartBlockListener blockListener = new CartBlockListener(this);
    private final CartCartListener cartListener = new CartCartListener(this);

	Logger log = Logger.getLogger("Minecraft");
	
	public void main(){
		PluginManager pm = this.getServer().getPluginManager();
	    pm.registerEvent(Event.Type.VEHICLE_COLLISION_BLOCK, cartListener, Event.Priority.Normal, this);
	    pm.registerEvent(Event.Type.BLOCK_DISPENSE, blockListener, Event.Priority.Normal, this);
	}
    
	public void onEnable(){
		log.info("CartDispense v0.10 has loaded.");
		main();

	}
	
	public void onDisable(){
		log.info("CartDispense v0.10 has unloaded.");
	}
	
}