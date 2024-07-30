package ru.ikote.skullcustom.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.StringUtil;
import ru.ikote.skullcustom.SkullCustom;
import ru.ikote.skullcustom.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class CustomHeadSkullCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;

        Player player = (Player) sender;

        if (!player.hasPermission("playerskull.cmd.skull")) {
            player.sendMessage(ChatColor.RED + "You have no rights.");
            return true;
        }

        String ownerName = null;
        String receiverName = null;

        OfflinePlayer offlinePlayer;
        if (args.length == 0) {
            offlinePlayer = player;
        } else {
            offlinePlayer = Bukkit.getOfflinePlayer(args[0]);
            ownerName = offlinePlayer.getName();
        }

        ItemStack head = Utils.getHead(offlinePlayer);

        Player playerReceiver;
        if (args.length > 1) {
            playerReceiver = Utils.getOnlinePlayer(args[1]);
            receiverName = args[1];
        } else {
            playerReceiver = player;
        }

        if (playerReceiver == null) {
            sender.sendMessage(ChatColor.RED + "The recipient of the head is not on the server!");
            return true;
        }

        playerReceiver.getInventory().addItem(head);

        sender.sendMessage(getInfoMessage(ownerName, receiverName));

        return true;
    }

    private String getInfoMessage(String ownerName, String receiverName) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ChatColor.DARK_GREEN);
        stringBuilder.append("[!] > ");
        stringBuilder.append(ChatColor.GREEN);

        if (receiverName == null) {
            stringBuilder.append(ChatColor.GOLD);
            stringBuilder.append("To you");
        } else {
            stringBuilder.append("To player ");
            stringBuilder.append(ChatColor.GOLD);
            stringBuilder.append(receiverName);
        }

        stringBuilder.append(ChatColor.GREEN);
        stringBuilder.append(" was given ");

        if (ownerName == null) {
            stringBuilder.append(ChatColor.GOLD);
            stringBuilder.append("Yours ");
            stringBuilder.append(ChatColor.GREEN);
            stringBuilder.append("head");
        } else {
            stringBuilder.append("player head ");
            stringBuilder.append(ChatColor.GOLD);
            stringBuilder.append(ownerName);
        }

        return stringBuilder.toString();
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        ArrayList<String> result = new ArrayList<>();
        int a = 0;

        if (args.length <= 1) {
            for (OfflinePlayer offlinePlayer : Bukkit.getOfflinePlayers()) {
                String name = offlinePlayer.getName();
                if (name == null) continue;
                result.add(name);
            }
        }

        if (args.length == 2) {
            a = 1;
            for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                String name = onlinePlayer.getName();
                result.add(name);
            }
        }

        ArrayList<String> returned = new ArrayList<>();
        StringUtil.copyPartialMatches(args[a], result, returned);
        return returned;
    }

    public static void register() {
        SkullCustom plugin = SkullCustom.getPlugin();
        PluginCommand command = plugin.getCommand("skull");
        CustomHeadSkullCommand executor = new CustomHeadSkullCommand();
        command.setExecutor(executor);
        command.setTabCompleter(executor);
    }
}
