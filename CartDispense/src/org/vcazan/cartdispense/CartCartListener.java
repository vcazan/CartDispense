package org.vcazan.cartdispense;

import org.bukkit.event.vehicle.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Vehicle;
import org.bukkit.Location;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

public class CartCartListener implements Listener {
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onVehicleBlockCollision(VehicleBlockCollisionEvent event){
		Vehicle cart = event.getVehicle();
		Block block = event.getBlock();
		Location loc = block.getLocation();
		
		if (loc.getBlock().getTypeId() == 23){
			cart.remove();
		}
	}
	
}
