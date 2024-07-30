package ru.ikote.skullcustom.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Collections;
import java.util.Date;

public class Utils {

    public static ItemStack getHead(OfflinePlayer offlinePlayer) {
        ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD);
        ItemMeta itemMeta = itemStack.getItemMeta();
        SkullMeta meta = (SkullMeta) itemMeta;
        if (meta == null) return itemStack;
        meta.setOwnerProfile(offlinePlayer.getPlayerProfile());
        Date date = new Date();
        meta.setLore(Collections.singletonList(date.toString()));
        itemStack.setItemMeta(meta);
        return itemStack;
    }

    public static Player getOnlinePlayer(String name) {
        for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
            if (onlinePlayer.getName().equalsIgnoreCase(name)) {
                return onlinePlayer;
            }
        }
        return null;
    }
}
