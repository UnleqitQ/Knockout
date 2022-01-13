package com.traunmagil.knockout.event.events;

import com.traunmagil.knockout.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public class PlayerInteractAtEntity implements Listener {
	
	private Main main = Main.getInstance();
	
	@EventHandler
	public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent e) {
		
		Player p = e.getPlayer();
		
		if (!(e.getRightClicked() instanceof Player)) {
			return;
		}
		
		Player clicked = (Player) e.getRightClicked();
		if (!main.knManager.isKnockout(clicked))
			return;
		if (main.rvManager.wereRevived(clicked))
			return;
		if (main.rvManager.isReviving(p))
			return;
		
		
		main.rvManager.revivePlayer(p, clicked);
	}
	
}
