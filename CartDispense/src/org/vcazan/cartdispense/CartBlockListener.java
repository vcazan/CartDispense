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
	
	public CartBlockListener(CartDispense instance) {
		this.plugin = instance;
	}
	Logger log = Logger.getLogger("Minecraft");


	public void onBlockDispense(BlockDispenseEvent event){
		boolean trackAround = false;
		
		ItemStack dispenseItem = event.getItem();
		Block block = event.getBlock();

		if (dispenseItem.getTypeId() == 328){
			event.setCancelled(true);

			Location loc = block.getLocation();
				
			for(BlockFace face : BlockFace.values()) {
				if (block.getFace(face).getTypeId() == 66|| block.getFace(face).getTypeId() == 27 || block.getFace(face).getTypeId() == 28) {
					loc.setY(block.getY() + face.getModY() );
					loc.setZ(block.getZ() + face.getModZ() );
					loc.setX(block.getX() + face.getModX() );
					trackAround = true;
				}
				
			}
			Location under = block.getLocation();
			under.setY(under.getY()-1);
			
			Block underBlock = under.getBlock();
			for(BlockFace face : BlockFace.values()) {
				if (underBlock.getFace(face).getTypeId() == 66|| underBlock.getFace(face).getTypeId() == 27 || underBlock.getFace(face).getTypeId() == 28) {
					loc.setY(block.getY() + face.getModY() );
					loc.setZ(block.getZ() + face.getModZ() );
					loc.setX(block.getX() + face.getModX() );
					trackAround = true;
				}
				
			}

			if (trackAround == true){
				this.plugin.getServer().getWorld("world").spawnMinecart(loc);

			}
		}


	}
}