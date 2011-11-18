package org.vcazan.cartdispense;

import org.bukkit.event.vehicle.*;
import org.bukkit.block.Block;
import org.bukkit.entity.Vehicle;
import org.bukkit.Location;

public class CartCartListener extends VehicleListener {

	public final CartDispense plugin;

	public CartCartListener(CartDispense instance) {
		this.plugin = instance;
	}

	public void onVehicleBlockCollision(VehicleBlockCollisionEvent event){
		Vehicle cart = event.getVehicle();
		Block block = event.getBlock();
		Location loc = block.getLocation();
		
		loc.setY(loc.getY()+1);//stupid bukkit says the collision is one block under
		
		if (loc.getBlock().getTypeId() == 23){
			cart.remove();
		}
	}
	
}
