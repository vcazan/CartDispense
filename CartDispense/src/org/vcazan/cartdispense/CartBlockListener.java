package org.vcazan.cartdispense;

import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockListener;
import org.bukkit.inventory.ItemStack;

public class CartBlockListener extends BlockListener{

	public final CartDispense plugin;
	
	public CartBlockListener(CartDispense instance) {
		this.plugin = instance;
	}
	Logger log = Logger.getLogger("Minecraft");
	Location spawnCart;


	public void onBlockDispense(BlockDispenseEvent event){
		ItemStack dispenseItem = event.getItem();
		if (dispenseItem.getTypeId() == 328 ||dispenseItem.getTypeId() == 343 || dispenseItem.getTypeId() == 342){
			event.setCancelled(true);
			Block block = event.getBlock();
			Location under = block.getLocation();
			under.setY(block.getY()-1);
			
			if (checkForTrack(block.getLocation()) == true || checkForTrack(under) == true){
				for(World world : this.plugin.getServer().getWorlds()) {
					if(world.getBlockAt(block.getLocation()) == block){
						 switch (dispenseItem.getTypeId()) {
						 case 328:world.spawnMinecart(spawnCart); break;
						 case 343:world.spawnPoweredMinecart(spawnCart); break;
						 case 342:world.spawnStorageMinecart(spawnCart); break;
						 }
					}
				}
			}			
		}
	}
	public boolean checkForTrack(Location loc){
		Block block = loc.getBlock();
		for(BlockFace face : BlockFace.values()) {
			if (block.getFace(face).getTypeId() == 66|| block.getFace(face).getTypeId() == 27 || block.getFace(face).getTypeId() == 28) {
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