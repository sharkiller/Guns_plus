package team.GunsPlus.Item;

import java.util.List;

import org.bukkit.block.Block;
import org.bukkit.plugin.Plugin;
import org.getspout.spoutapi.block.SpoutBlock;
import org.getspout.spoutapi.material.item.GenericCustomItem;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.griefcraft.util.ProtectionFinder;
import com.griefcraft.util.matchers.DoorMatcher;
import com.griefcraft.util.matchers.DoubleChestMatcher;

import team.GunsPlus.GunsPlus;

public class Ammo extends GenericCustomItem {

	private int damage = 0;
	
	public Ammo(Plugin plugin, String name, String texture, int dmg) {
		super(plugin, name, texture);
		damage = dmg;
	}

	public boolean onItemInteract(SpoutPlayer player, SpoutBlock block,
			org.bukkit.block.BlockFace face) {
		try {
			if (GunsPlus.lwc != null) {
				GunsPlus.lwc.wrapPlayer(player.getPlayer());
				Block b;
				if (block != null)
					b = block;
				else
					return true;
				DoubleChestMatcher chestMatcher = new DoubleChestMatcher();
				DoorMatcher doorMatcher = new DoorMatcher();
				if (GunsPlus.lwc.getProtectionSet(b.getWorld(), b.getX(),
						b.getY(), b.getZ()).size() >= 2) {
					ProtectionFinder pf = new ProtectionFinder(GunsPlus.lwc);
					pf.addBlock(b);
					if (chestMatcher.matches(pf) || doorMatcher.matches(pf)) {
						List<Block> list = pf.getBlocks();
						for (Block bl : list) {
							if (!GunsPlus.lwc.canAccessProtection(player, bl)) {
								return false;
							}
						}
					}
				}
				if (!GunsPlus.lwc.canAccessProtection(player, b)) {
					return false;
				} else
					return true;
			} else
				return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public int getDamage(){
		return damage;
	}
	
	public void setDamage(int dmg){
		damage = dmg;
	}
}
