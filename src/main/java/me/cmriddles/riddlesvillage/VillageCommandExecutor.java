package me.cmriddles.riddlesvillage;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VillageCommandExecutor implements CommandExecutor {
    @Override
    public boolean onCommand(final CommandSender sender, final Command command, final String label, final String[] args) {
        if (command.getName().equalsIgnoreCase("village")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage(ChatColor.RED + "Only players can use this command!");
                return true;
            }

            Player player = (Player) sender;

            if (args.length > 0) {
                if (args[0].equals("create")) {
                    String size = args[1];
                    if (size.equals("small") || size.equals("medium") || size.equals("huge")) {
                        int villageSize = 0;
                        if (size.equals("small")) {
                            villageSize = 25;
                        } else if (size.equals("medium")) {
                            villageSize = 35;
                        } else if (size.equals("huge")) {
                            villageSize = 50;
                        }
                    } else {
                        player.sendMessage(ChatColor.RED + "Invalid village size. Available sizes: Small, Medium, Huge.");
                    }
                    Location location = player.getLocation();
                    Location blockBelow = location.clone();
                    blockBelow.subtract(0, -1, 0);
                    if (blockBelow.getBlock().getType().isSolid()) { {
                        player.sendMessage(ChatColor.GREEN + "Village created successfully!");
                    }
                        player.sendMessage(ChatColor.RED + "You must be standing on a solid block to create a village!");
                    }
                }
            }
            player.sendMessage(ChatColor.RED + "Invalid command usage. Use /village create (Size: Small, Medium, Huge)");
        }
        return true;
    }
    private void showVillageMessage(Player player, boolean entering) {
        if (entering) {
            player.sendMessage(ChatColor.YELLOW + "Welcome to Riddles Village!");
        } else {
            player.sendMessage(ChatColor.YELLOW + "Leaving Riddles Village. Come back soon!");
        }
    }
}
