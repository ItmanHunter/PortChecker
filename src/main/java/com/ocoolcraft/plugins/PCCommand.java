package com.ocoolcraft.plugins;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;

public class PCCommand implements CommandExecutor {

    public static boolean available(int port) {

        ServerSocket ss = null;
        DatagramSocket ds = null;
        try {
            ss = new ServerSocket(port);
            ss.setReuseAddress(true);
            ds = new DatagramSocket(port);
            ds.setReuseAddress(true);
            return true;
        } catch (IOException e) {
        } finally {
            if (ds != null) {
                ds.close();
            }

            if (ss != null) {
                try {
                    ss.close();
                } catch (IOException e) {
                    /* should not be thrown */
                }
            }
        }

        return false;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command0, String label, String args[]) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (player.hasPermission("portchecker.portc")) {
                if (args.length != 2) {
                    player.sendMessage("usage /portc <min> <max>");
                    return true;
                }
                for(int port = Integer.parseInt(args[0]);port <= Integer.parseInt(args[1]);port++) {
                    if (available(port)) {
                        player.sendMessage("Port open: " + port);
                    }
                }

            } else {
                player.sendMessage(ChatColor.BLUE + " No permission ");
            }
        }
        return true;
    }

}
