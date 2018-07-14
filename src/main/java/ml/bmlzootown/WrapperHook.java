package ml.bmlzootown;

import ml.bmlzootown.commands.WrapperCommands;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class WrapperHook extends JavaPlugin {
    private static Logger log = Bukkit.getLogger();
    private static WrapperHook instance;

    public void onEnable() {
        instance = this;
        getCommand("wrapper").setExecutor(new WrapperCommands());
    }

    public void onDisable() {

    }

    public static WrapperHook getInstance() {
        return instance;
    }
}
