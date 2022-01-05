package com.traunmagil.knockout.event.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import com.traunmagil.knockout.Main;

public class BlockBreak implements Listener {

	private Main main = Main.getInstance();
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		Player p = e.getPlayer();
		
		if(main.knManager.isKnockout(p)) e.setCancelled(true);
	}
	
}
