package com.traunmagil.knockout.event.events;

import com.traunmagil.knockout.Main;
import dev.geco.gsit.api.event.PrePlayerGetUpPoseEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class StopPosing implements Listener {
	
	private Main main = Main.getInstance();
	
	@EventHandler
	public void onStopPosing(PrePlayerGetUpPoseEvent e) {
		if (main.knManager.isKnockout(e.getPlayer()))
			e.setCancelled(true);
		/*try {
			main.knManager.resetPose(e.getPlayer());
		} catch (Exception ex) {
		
		}
		
		Bukkit.getScheduler().runTaskLater(main, () -> {
			/*PosePluginPlayer posePluginPlayer = PosePluginAPI.getAPI().getPlayerMap().getPosePluginPlayer(p);
			IPluginPose pose = PoseBuilder.builder(EnumPose.LYING).option(EnumPoseOption.HANDTYPE, HandType.LEFT).build(
					p);
			posePluginPlayer.changePose(pose);*
		GSitAPI.createPose(e.getPlayer().getLocation().getBlock(), e.getPlayer(), Pose.DYING);
		},8L);*/
	}
	
}
