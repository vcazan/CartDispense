package org.vcazan.cartdispense;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
import org.bukkit.craftbukkit.entity.CraftMinecart;
import org.bukkit.craftbukkit.entity.CraftPoweredMinecart;
import org.bukkit.craftbukkit.entity.CraftStorageMinecart;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.Listener;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;


public class CartBlockListener implements Listener {

	Logger log = Logger.getLogger("Minecraft");
	Location spawnCart;
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockDispense(BlockDispenseEvent event){
		ItemStack dispenseItem = event.getItem();
		if (dispenseItem.getTypeId() == 328 ||dispenseItem.getTypeId() == 343 || dispenseItem.getTypeId() == 342) {
			event.setCancelled(true);
			Block block = event.getBlock();
			Location under = block.getLocation();
			under.setY(block.getY()-1);

			if (checkForTrack(block.getLocation()) == true || checkForTrack(under) == true){
				World world = block.getLocation().getWorld();
				switch (dispenseItem.getType()) {
					 case MINECART:
						 world.spawn(spawnCart, CraftMinecart.class); 
						 break;
					 case POWERED_MINECART:
						 world.spawn(spawnCart, CraftPoweredMinecart.class); 
						 break;
					 case STORAGE_MINECART:
						 world.spawn(spawnCart, CraftStorageMinecart.class); 
						 break;
				 }
				
				if(CartDispense.getInstance().getConfig().getBoolean("infinitecarts") == false) {
					Dispenser d = (Dispenser)event.getBlock().getState();
					d.getInventory().removeItem(dispenseItem);
				}
			}			
		}
	}
	
	public boolean checkForTrack(Location loc){
		Block block = loc.getBlock();
		for(BlockFace face : BlockFace.values()) {
			if (block.getRelative(face).getTypeId() == 66|| block.getRelative(face).getTypeId() == 27 || block.getRelative(face).getTypeId() == 28) {
				loc.setY(block.getY() + face.getModY() );
				loc.setZ(block.getZ() + face.getModZ() );
				loc.setX(block.getX() + face.getModX() );
				spawnCart = loc;
				return true;
			}
			
		}
		return false;
	}
}