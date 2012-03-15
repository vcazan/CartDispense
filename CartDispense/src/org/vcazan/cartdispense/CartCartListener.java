package org.vcazan.cartdispense;

import org.bukkit.event.vehicle.*;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.PoweredMinecart;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Vehicle;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

public class CartCartListener implements Listener {
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onVehicleBlockCollision(VehicleBlockCollisionEvent event){
		Vehicle cart = event.getVehicle();
		Block block = event.getBlock();
		Location loc = block.getLocation();

		if(cart.getType() == EntityType.MINECART) {
			if (loc.getBlock().getState().getType() == Material.DISPENSER) {
					
				if(CartDispense.getInstance().getConfig().getBoolean("infinitecarts") == false) {
					Dispenser d = (Dispenser)event.getBlock().getState();
					ItemStack i = null;
					if(cart instanceof PoweredMinecart) {
						i = new ItemStack(Material.POWERED_MINECART, 1);
					} else if (cart instanceof StorageMinecart) {
						i = new ItemStack(Material.STORAGE_MINECART, 1);
					} else {
						i = new ItemStack(Material.MINECART, 1);
					}
					
					d.getInventory().addItem(i);
				}
			
				cart.remove();
			}
		}
	}
	
}
