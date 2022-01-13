package com.traunmagil.knockout.utils;

import com.traunmagil.knockout.Main;
import com.traunmagil.knockout.countdown.KnockoutCountdown;
import dev.geco.gsit.api.GSitAPI;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.entity.Pose;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class KnockoutManager {
	
	private HashMap<UUID, KnockoutCountdown> knockout;
	private HashMap<DamageCause, Integer> countdowns;
	private Main main;
	
	public KnockoutManager() {
		this.knockout = new HashMap<>();
		this.countdowns = new HashMap<>();
		this.main = Main.getInstance();
		
		loadCountdowns();
	}
	
	private void loadCountdowns() {
		FileConfiguration cfg = main.fileManager.get("countdowns");
		
		for (String key : cfg.getKeys(false)) {
			countdowns.put(DamageCause.valueOf(key), cfg.getInt(key));
		}
	}
	
	public boolean isKnockout(Player p) {
		return knockout.containsKey(p.getUniqueId());
	}
	
	public void setKnockout(Player p, DamageCause cause) {
		if (main.rvManager.isReviving(p))
			main.rvManager.stopRevivePlayer(p);
		
		int time = countdowns.get(cause);
		KnockoutCountdown cd = new KnockoutCountdown(time, p);
		knockout.put(p.getUniqueId(), cd);
		cd.run();
		Main.sendMsg(p, main.langManager.getMessage("Knockout-Message").replace("%time%", String.valueOf(time)));
		
		/*PosePluginPlayer posePluginPlayer = PosePluginAPI.getAPI().getPlayerMap().getPosePluginPlayer(p);
		IPluginPose pose = PoseBuilder.builder(EnumPose.LYING).option(EnumPoseOption.HANDTYPE, HandType.LEFT).build(p);
		posePluginPlayer.changePose(pose);*/
		//GSitAPI.createPose(p.getLocation().getBlock(), p, Pose.FALL_FLYING);
		GSitAPI.createPose(p.getLocation().clone().add(0, -1, 0).getBlock(), p,
				(Math.random() < 0.5) ? Pose.SLEEPING : Pose.SWIMMING,
				p.getLocation().getX() - p.getLocation().getBlockX(),
				p.getLocation().getY() - p.getLocation().getBlockY(),
				p.getLocation().getZ() - p.getLocation().getBlockZ(), 0, false, false);
		p.setGameMode(GameMode.ADVENTURE);
		p.setHealth(20.0);
	}
	
	public void pauseKnockout(Player p) {
		knockout.get(p.getUniqueId()).pause();
	}
	
	public void resumeKnockout(Player p) {
		knockout.get(p.getUniqueId()).run();
		try {
			resetPose(p);
		} catch (Exception e) {
		
		}
		
		Bukkit.getScheduler().runTaskLater(main, () -> {
			/*PosePluginPlayer posePluginPlayer = PosePluginAPI.getAPI().getPlayerMap().getPosePluginPlayer(p);
			IPluginPose pose = PoseBuilder.builder(EnumPose.LYING).option(EnumPoseOption.HANDTYPE, HandType.LEFT).build(
					p);
			posePluginPlayer.changePose(pose);*/
			GSitAPI.createPose(p.getLocation().clone().add(0, -1, 0).getBlock(), p,
					(Math.random() < 0.5) ? Pose.SLEEPING : Pose.SWIMMING,
					p.getLocation().getX() - p.getLocation().getBlockX(),
					p.getLocation().getY() - p.getLocation().getBlockY(),
					p.getLocation().getZ() - p.getLocation().getBlockZ(), 0, false, false);
			p.setGameMode(GameMode.ADVENTURE);
		}, 8L);
		
		
	}
	
	public int getCountdown(Player p) {
		if (!knockout.containsKey(p.getUniqueId()))
			return -1;
		return knockout.get(p.getUniqueId()).getCurrent();
	}
	
	public void killAllInKnockout() {
		for (Map.Entry<UUID, KnockoutCountdown> entry : knockout.entrySet()) {
			Player p = Bukkit.getPlayer(entry.getKey());
			if (p == null)
				continue;
			p.setHealth(0.0);
			entry.getValue().cancel();
			p.setGameMode(GameMode.SURVIVAL);
		}
	}
	
	public void removeKnockout(Player p) {
		if (!knockout.containsKey(p.getUniqueId()))
			return;
		resetPose(p);
		knockout.get(p.getUniqueId()).cancel();
		knockout.remove(p.getUniqueId());
		p.setGameMode(GameMode.SURVIVAL);
	}
	
	public void resetPose(Player p) {
		Bukkit.getScheduler().runTaskLater(main, () -> {
			/*PosePluginPlayer posePluginPlayer = PosePluginAPI.getAPI().getPlayerMap().getPosePluginPlayer(p);
			PosePlugin.PLAYERS_POSES.remove(p);
			posePluginPlayer.stopPosingSilently();
			posePluginPlayer.resetCurrentPose();*/
			//GSitAPI.getPose(p).remove();
			try {
				GSitAPI.getPose(p).remove();
			} catch (Exception e) {
			}
		}, 5L);
		
	}
	
}
