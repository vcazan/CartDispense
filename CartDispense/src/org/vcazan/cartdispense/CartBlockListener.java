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
	int track = 0;
	
	public CartBlockListener(CartDispense instance) {
		this.plugin = instance;
	}


	public void onBlockDispense(BlockDispenseEvent event){
		track = 0;
		ItemStack despenseItem = event.getItem();
		event.setCancelled(true);
		Block block = event.getBlock();

		if (despenseItem.getTypeId() == 328 ){
			Location loc = block.getLocation();

			for(BlockFace face : BlockFace.values()) {
				
				if (block.getFace(face).getTypeId() == 66) {
					log.info(Integer.toString(face.getModZ()));
					loc.setY(block.getY() + face.getModY());
					loc.setZ(block.getZ() + face.getModZ());
					loc.setX(block.getX() + face.getModX());
					track = 1;
				}
				
				}

			if (track ==1){
				this.plugin.getServer().getWorld("world").spawnMinecart(loc);

			}
		}


	}
}