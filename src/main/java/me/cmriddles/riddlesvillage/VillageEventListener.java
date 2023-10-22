package me.cmriddles.riddlesvillage;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class VillageEventListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        Location to = event.getTo();
        Location from = event.getFrom();
        if(to == null) {
            return;
        }
        String villageNameTo = getVillageRegionName(to);
        String villageNameFrom = getVillageRegionName(from);

        if (villageNameTo != null && !villageNameTo.equals(villageNameFrom)) {
            player.sendMessage(ChatColor.GREEN + "You have entered the village: " + villageNameTo);
        } else if (villageNameFrom != null && !villageNameFrom.equals(villageNameTo)) {
            player.sendMessage(ChatColor.RED + "You have left the village: " + villageNameFrom);
        }
    }

    private String getVillageRegionName(Location location) {
        World world = location.getWorld();
        if (world != null) {
            RegionManager regionManager = WorldGuard.getInstance().getPlatform().getRegionContainer().get(BukkitAdapter.adapt(world));

            if (regionManager != null) {
                for (ProtectedRegion region : regionManager.getRegions().values()) {
                    if (region.contains(location.getBlockX(), location.getBlockY(), location.getBlockZ())) {
                        return region.getId();
                    }
                }
            }
        }
        return null;
    }
}