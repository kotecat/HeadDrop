package ru.ikote.skullcustom.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import ru.ikote.skullcustom.SkullCustom;
import ru.ikote.skullcustom.utils.Config;
import ru.ikote.skullcustom.utils.Utils;

import java.util.List;
import java.util.Random;

public class PlayerDeath implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if (isDrop(event)) {
            event.getDrops().add(Utils.getHead(event.getEntity()));
        }
    }

    private boolean isDrop(PlayerDeathEvent event) {
        Config config = SkullCustom.getPlugin().config();

        boolean enableDrops = config.getBoolean("enable-drop");
        double chanceDrop = config.getDouble("chance-drop");
        List<String> gamemodes = config.getStringList("allowed-drop-gamemode");

        if (enableDrops) {
            if (new Random().nextDouble() <= chanceDrop) {
                return gamemodes.stream().anyMatch(
                        event.getEntity().getGameMode().name()::equalsIgnoreCase
                );
            }
        }
        return false;
    }
}
