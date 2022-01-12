package com.traunmagil.knockout.event.events;

import com.traunmagil.knockout.Main;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class PickupItem implements Listener {
	
	@EventHandler
	public void onItemPickup(EntityPickupItemEvent event) {
		if (event.getEntityType() == EntityType.PLAYER) {
			Player player = (Player) event.getEntity();
			if (Main.getInstance().knManager.isKnockout(player))
				event.setCancelled(true);
		}
	}
	
}
