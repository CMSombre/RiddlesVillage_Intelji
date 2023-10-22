package me.cmriddles.riddlesvillage;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class RiddlesVillage extends JavaPlugin {

    private boolean loaded;

    private WorldEditPlugin worldEdit;
    private WorldGuardPlugin worldGuard;
    public static RiddlesVillage INSTANCE;
    @Override
    public void onEnable() {
        loaded = false;
        INSTANCE = this;
        worldEdit = (WorldEditPlugin) getServer().getPluginManager().getPlugin("WorldEdit");
        worldGuard = (WorldGuardPlugin) getServer().getPluginManager().getPlugin("WorldGuard");
        if (worldEdit == null) {
            getLogger().severe("WorldEdit not found! Disabling the plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        if(worldGuard == null) {
            getLogger().severe("WorldGuard not found! Disabling the plugin.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        getLogger().info("Loading plugin");
        getServer().getPluginManager().registerEvents(new VillageEventListener(), this);
        Objects.requireNonNull(this.getCommand("village")).setExecutor(new VillageCommandExecutor());
        loaded = true;
    }
    @Override
    public void onDisable() {
        getLogger().info("Plugin is disabled");
    }
    public boolean isLoaded() {
        return loaded;
    }
}