package org.vcazan.cartdispense;

import java.util.logging.Logger;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockListener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Location;

public class CartBlockListener extends BlockListener{

	public final CartDispense plugin;
	Logger log = Logger.getLogger("Minecraft");

	
	public CartBlockListener(CartDispense instance) {
		this.plugin = instance;
	}


	public void onBlockDispense(BlockDispenseEvent event){
		boolean trackAround = false;
		
		ItemStack dispenseItem = event.getItem();
		Block block = event.getBlock();
		event.setCancelled(true);

		if (dispenseItem.getTypeId() == 328){

			Location loc = block.getLocation();
				
			for(BlockFace face : BlockFace.values()) {
				if (block.getFace(face).getTypeId() == 66|| block.getFace(face).getTypeId() == 27 || block.getFace(face).getTypeId() == 28) {
					loc.setY(block.getY() + face.getModY());
					loc.setZ(block.getZ() + face.getModZ());
					loc.setX(block.getX() + face.getModX());
					trackAround = true;
				}
			}

			if (trackAround = true){
				this.plugin.getServer().getWorld("world").spawnMinecart(loc);

			}
		}


	}
}