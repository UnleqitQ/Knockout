package com.traunmagil.knockout.event.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import com.traunmagil.knockout.Main;

public class BlockPlace implements Listener {

	private Main main = Main.getInstance();
	
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		Player p = e.getPlayer();
		
		if(main.knManager.isKnockout(p)) e.setCancelled(true);
		
	}
	
}
