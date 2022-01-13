package com.traunmagil.knockout.event.events;

import com.traunmagil.knockout.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerJoin implements Listener {
	
	@EventHandler
	public void onJoin(@NotNull PlayerLoginEvent event) {
		if (Main.getInstance().knManager.isKnockout(event.getPlayer()))
			Main.getInstance().knManager.resumeKnockout(event.getPlayer());
	}
	
}
