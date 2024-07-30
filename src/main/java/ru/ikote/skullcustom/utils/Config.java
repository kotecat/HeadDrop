package ru.ikote.skullcustom.utils;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;
import java.util.List;

public class Config {

    private FileConfiguration fileConfiguration;
    private final JavaPlugin plugin;

    public Config(JavaPlugin plugin) {
        fileConfiguration = plugin.getConfig();
        this.plugin = plugin;
    }

    public int getInteger(String... path) {
        return fileConfiguration.getInt(String.join(".", path));
    }

    public List<Integer> getIntegerList(String... path) {
        return fileConfiguration.getIntegerList(String.join(".", path));
    }

    public double getDouble(String... path) {
        return fileConfiguration.getDouble(String.join(".", path));
    }

    public List<Double> getDoubleList(String... path) {
        return fileConfiguration.getDoubleList(String.join(".", path));
    }

    public boolean getBoolean(String... path) {
        return fileConfiguration.getBoolean(String.join(".", path));
    }

    public List<Boolean> getBooleanList(String... path) {
        return fileConfiguration.getBooleanList(String.join(".", path));
    }

    public String getString(String... path) {
        return fileConfiguration.getString(String.join(".", path));
    }

    public List<String> getStringList(String... path) {
        return fileConfiguration.getStringList(String.join(".", path));
    }

    public void generateConfig() {
        fileConfiguration.addDefault("enable-drop", true);

        fileConfiguration.addDefault("chance-drop", 0.5);
        fileConfiguration.setComments("chance-drop", Arrays.asList(
                "Chance drop head, minimum -> 0 (0%), maximum -> 1 (100%)"
        ));

        fileConfiguration.addDefault("allowed-drop-gamemode", Arrays.asList(
                "adventure",
                "survival"
        ));

        fileConfiguration.options().copyDefaults(true);
        plugin.saveConfig();
    }

    public void reloadConfig() {
        plugin.reloadConfig();
        fileConfiguration = plugin.getConfig();
    }
}
