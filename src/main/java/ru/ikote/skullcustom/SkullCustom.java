package ru.ikote.skullcustom;

import org.bukkit.plugin.java.JavaPlugin;
import ru.ikote.skullcustom.command.CustomHeadSkullCommand;
import ru.ikote.skullcustom.listener.PlayerDeath;
import ru.ikote.skullcustom.utils.Config;

public final class SkullCustom extends JavaPlugin {

    private static SkullCustom plugin;

    private Config config;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        config = new Config(this);
        config.generateConfig();
        CustomHeadSkullCommand.register();
        getServer().getPluginManager().registerEvents(new PlayerDeath(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static SkullCustom getPlugin() {
        return plugin;
    }

    public Config config() {
        return config;
    }
}
