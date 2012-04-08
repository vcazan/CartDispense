package org.vcazan.cartdispense;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.PoweredMinecart;
import org.bukkit.entity.StorageMinecart;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.material.Dispenser;
import org.bukkit.material.MaterialData;


public class CartBlockListener implements Listener {

	Logger log = Logger.getLogger("Minecraft");
	Location spawnCart;
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockDispense(BlockDispenseEvent event){
		//Check to see if we're dispensing a Mine Cart/Powered Cart/Storage Cart
		ItemStack dispenseItem = event.getItem();
		if (dispenseItem.getTypeId() == 328 ||dispenseItem.getTypeId() == 343 || dispenseItem.getTypeId() == 342) {
			
			//Cancel the dispense event, we're going to handle this (otherwise we'll have a loose cart + a cart on the track)
			event.setCancelled(true);
			
			Block block = event.getBlock();
			
			//Let's check which way the dispenser is facing. Result will be 'EAST', 'WEST', 'NORTH', or 'SOUTH'. We'll use that to
			//check for a rail in that direction or under the dispenser.
			MaterialData d = block.getState().getData();
			Dispenser disp = (Dispenser) d;
			BlockFace face = disp.getFacing();
			
			//Now let's figure out the block location for the dispenser and
			//the location under that.
			Location dest = block.getLocation();
			Location underDest = block.getLocation();
			underDest.setY(underDest.getY() - 1D);
			
			//Okay, now to find out which way the dispenser is facing and move our target that way.
			switch(face){
				case NORTH:
					dest.setX(dest.getX() - 1D);
					underDest.setX(underDest.getX() - 1D);
					break;
				case EAST:
					dest.setZ(dest.getZ() - 1D);
					underDest.setZ(underDest.getZ() - 1D);
					break;
				case SOUTH:
					dest.setX(dest.getX() + 1D);
					underDest.setX(underDest.getX() + 1D);
					break;
				case WEST:
					dest.setZ(dest.getZ() + 1D);
					underDest.setZ(underDest.getZ() + 1D);
					break;
			}
			
			//Now let's check to make sure a rail, powered rail, or detector rail is either target area.
			//We prefer a location on level to a location under.
			if(checkForTrack(dest) == true || checkForTrack(underDest) == true){
				World world = block.getLocation().getWorld();
				switch(dispenseItem.getType()){
					case MINECART:
						world.spawn(spawnCart, Minecart.class);
						break;
					case POWERED_MINECART:
						world.spawn(spawnCart, PoweredMinecart.class);
						break;
					case STORAGE_MINECART:
						world.spawn(spawnCart, StorageMinecart.class);
						break;
				}
				
				//Check for infinitecarts flag; some people don't want magic dispenser of a million entities
				if(CartDispense.getInstance().getConfig().getBoolean("infinitecarts") == false) {
					InventoryHolder e = (InventoryHolder)event.getBlock().getState();
					e.getInventory().removeItem(dispenseItem);
				}
			}
		}
	}

	// Boolean statement to check to see if track is in the target block. If so, return true and set spawn position to
	// that block.
	public boolean checkForTrack(Location loc){
		Block block = loc.getBlock();
		if (block.getTypeId() == 66|| block.getTypeId() == 27 || block.getTypeId() == 28) {
				spawnCart = loc;
				return true;
			}
		return false;
	}
}