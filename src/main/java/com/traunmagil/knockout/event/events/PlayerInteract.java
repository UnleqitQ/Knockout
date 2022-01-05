package com.traunmagil.knockout.event.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.traunmagil.knockout.Main;

public class PlayerInteract implements Listener {

	private static Main main = Main.getInstance();
	
	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		if(main.knManager.isKnockout(p)) {
			e.setCancelled(true);
			return;
		}
		
	}
	
}
