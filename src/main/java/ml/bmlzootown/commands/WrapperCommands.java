package ml.bmlzootown.commands;

import ml.bmlzootown.udp.UDPClient;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WrapperCommands implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(ChatColor.BLUE + "[Wrapper] " + ChatColor.WHITE + "Command(s):");
            sender.sendMessage("/wrapper status - Shows remaining time until restart");
            if (sender.hasPermission("wrapper.admin")) {
                sender.sendMessage(ChatColor.RED + "/wrapper [set|reschedule] [h|m|s] - Reschedules/sets restart");
                sender.sendMessage(ChatColor.RED + "/wrapper cancel - Cancels restart");
                sender.sendMessage(ChatColor.RED + "/wrapper stop - Stops server AND wrapper");
                sender.sendMessage(ChatColor.RED + "/wrapper [enable|disable|remove] [plugin] - Enables/disables/removes plugin on next restart");
            }
            return true;
        }
        if (args.length == 1) {
            if (sender.hasPermission("wrapper.admin")) {
                if (args[0].equalsIgnoreCase("stop")) {
                    UDPClient.sendPacket("stop");
                    //sender.sendMessage(ChatColor.BLUE + "[Wrapper] " + ChatColor.WHITE + "*yawns* Have you tried /stop?");
                }
                if (args[0].equalsIgnoreCase("cancel")) {
                    UDPClient.sendPacket("cancel");
                    sender.sendMessage(ChatColor.BLUE + "[Wrapper] " + ChatColor.WHITE + "Restart canceled!");
                }
            }
            if (args[0].equalsIgnoreCase("status")) {
                String info = UDPClient.sendReceivePacket("status");
                String[] time = info.split(":");
                if (time[0].equalsIgnoreCase("[NULL]")) {
                    sender.sendMessage(ChatColor.BLUE + "[Wrapper] " + ChatColor.WHITE + "No restart scheduled!");
                } else {
                    String hms = time[0] + "h " + time[1] + "m " + time[2] + "s";
                    sender.sendMessage(ChatColor.BLUE + "[Wrapper] " + ChatColor.WHITE + "Restarting in " + ChatColor.AQUA + hms);
                }
            }
            return true;
        }
        if (args.length == 2) {
            if (sender.hasPermission("wrapper.admin")) {
                if (args[0].equalsIgnoreCase("enable")) {
                    UDPClient.sendPacket("enable:" + args[1]);
                    sender.sendMessage(ChatColor.BLUE + "[Wrapper] " + ChatColor.WHITE + "Plugin will be enabled after a restart.");
                }
                if (args[0].equalsIgnoreCase("disable")) {
                    UDPClient.sendPacket("disable:" + args[1]);
                    sender.sendMessage(ChatColor.BLUE + "[Wrapper] " + ChatColor.WHITE + "Plugin will be disabled after a restart.");
                }
                if (args[0].equalsIgnoreCase("remove")) {
                    UDPClient.sendPacket("remove:" + args[1]);
                    sender.sendMessage(ChatColor.BLUE + "[Wrapper] " + ChatColor.WHITE + "Plugin will be removed after a restart.");
                }
            }
        }
        if (args.length == 4) {
            if (args[0].equalsIgnoreCase("reschedule") || args[0].equalsIgnoreCase("set")) {
                if (sender.hasPermission("wrapper.admin")) {
                    String h = args[1];
                    String m = args[2];
                    String s = args[3];
                    UDPClient.sendPacket("reschedule:" + h + ":" + m + ":" + s);
                    sender.sendMessage(ChatColor.BLUE + "[Wrapper] " + ChatColor.WHITE + "Restart rescheduled");
                }
            }
            return true;
        }
        return false;
    }

}
