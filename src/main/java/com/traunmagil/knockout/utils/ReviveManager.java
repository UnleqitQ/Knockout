package com.traunmagil.knockout.utils;

import java.util.HashMap;
import java.util.Map;

import javax.print.CancelablePrintJob;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import com.traunmagil.knockout.Main;
import com.traunmagil.knockout.countdown.ReviveCountdown;

import ru.armagidon.poseplugin.PosePlugin;
import ru.armagidon.poseplugin.api.PosePluginAPI;
import ru.armagidon.poseplugin.api.player.PosePluginPlayer;
import ru.armagidon.poseplugin.api.poses.AbstractPose;

public class ReviveManager {

	private Main main = Main.getInstance();
	private HashMap<Player, Player> revivers;
	private HashMap<Player, ReviveCountdown> revives;
	
	public ReviveManager() {
		revives = new HashMap<Player, ReviveCountdown>();
		revivers = new HashMap<Player, Player>();
	}
	
	public void revivePlayer(Player reviver, Player toRevived) {
		if(isReviving(reviver)) return;
		main.knManager.pauseKnockout(toRevived);
		
		resetPose(toRevived);
		Bukkit.getScheduler().runTaskLater(main, ()-> {
			reviver.addPassenger(toRevived);
			checkPlayerPassenger(reviver, toRevived);
			revivers.put(reviver, toRevived);
			ReviveCountdown cd = new ReviveCountdown(main.getConfig().getInt("revive-time"), toRevived);
			cd.run();
			revives.put(toRevived, cd);
		}, 5);
	}
	
	public void stopRevivePlayer(Player reviver) {
		if(!revivers.containsKey(reviver)) return;
		Player toRevive = revivers.get(reviver);
		revives.remove(toRevive);
		revivers.remove(reviver);
		
		Bukkit.getScheduler().runTaskLater(main, ()->{
			reviver.removePassenger(toRevive);
		}, 10);
		
		
		main.knManager.resumeKnockout(toRevive);
		
		
	}
	
	public void endRevivePlayer(Player toRevive) {
		main.knManager.removeKnockout(toRevive);
		revives.remove(toRevive);
		Player reviver = getReviver(toRevive);
		if(reviver == null) return;
		revivers.remove(reviver);
		reviver.removePassenger(toRevive);
		Bukkit.getScheduler().runTaskLater(main, () -> {
			toRevive.teleport(reviver.getLocation());
		}, 8L);
		
	}
	
	private void checkPlayerPassenger(Player reviver, Player toRevive) {
		new BukkitRunnable() {
			@Override
			public void run() {
				if(!wereRevived(toRevive)) {
					reviver.getPassengers().remove(toRevive);
					cancel();
				} else {
					if(!reviver.getPassengers().contains(toRevive)) {
						reviver.addPassenger(toRevive);
					}
				}
				
			}
		}.runTaskTimer(main, 0, 10L);
	}
	
	public Player getReviver(Player toRevive) {
		Player reviver = null;
		
		for (Map.Entry<Player, Player> entry : revivers.entrySet()) {
			if(entry.getValue().equals(toRevive)) {
				reviver = entry.getKey();
				break;
			}
	    }
		
		return reviver;
	}
	
	public boolean isReviving(Player reviver) {
		return revivers.containsKey(reviver);
	}
	
	public boolean wereRevived(Player toRevive) {
		return revives.containsKey(toRevive);
	}
	
	public void resetPose(Player p) {
		
		PosePluginPlayer posePluginPlayer = PosePluginAPI.getAPI().getPlayerMap().getPosePluginPlayer(p);
		posePluginPlayer.resetCurrentPose();
		posePluginPlayer.stopPosingSilently();
		PosePlugin.PLAYERS_POSES.remove(p);
		
		try {
			ReflectionUtil.setInstanceValue(posePluginPlayer, "pose", AbstractPose.STANDING);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
