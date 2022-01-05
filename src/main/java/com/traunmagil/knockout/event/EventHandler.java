package com.traunmagil.knockout.event;

import java.util.ArrayList;

import org.bukkit.event.Listener;

import com.traunmagil.knockout.Main;
import com.traunmagil.knockout.event.events.BlockBreak;
import com.traunmagil.knockout.event.events.BlockPlace;
import com.traunmagil.knockout.event.events.EntityDamage;
import com.traunmagil.knockout.event.events.EntityDamageByEntity;
import com.traunmagil.knockout.event.events.PlayerDeath;
import com.traunmagil.knockout.event.events.PlayerInteract;
import com.traunmagil.knockout.event.events.PlayerInteractAtEntity;
import com.traunmagil.knockout.event.events.PlayerLeave;
import com.traunmagil.knockout.event.events.StopPosing;

public class EventHandler {
	
	private ArrayList<Listener> listener;
	private Main main;
	
	public EventHandler() {
		
		listener = new ArrayList<Listener>();
		main = Main.getInstance();
		
		
		listener.add(new EntityDamage());
		listener.add(new PlayerDeath());
		listener.add(new StopPosing());
		listener.add(new EntityDamageByEntity());
		listener.add(new PlayerLeave());
		listener.add(new BlockPlace());
		listener.add(new BlockBreak());
		listener.add(new PlayerInteract());
		listener.add(new PlayerInteractAtEntity());
	}
	
	public void register() {
		for(Listener l : listener) {
			main.getServer().getPluginManager().registerEvents(l, main);
		}
	}
	
}
