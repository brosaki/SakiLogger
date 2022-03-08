package net.manosaki;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.manosaki.listeners.*;
import net.manosaki.utils.MultipleConfigs;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LoggerMain extends JavaPlugin {
    private static LoggerMain instance;
    private MultipleConfigs settings;
    private JDA api;

    @Override
    public void onEnable() {
        instance = this;

        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[SakiLogger] Started at " + this.getDescription().getVersion());
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "Thanks for downloading SakiLogger <3");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "If you got any error, create a 'Issue' in our GitHub Repository");
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + this.getDescription().getWebsite());
        Bukkit.getConsoleSender().sendMessage("");

        settings = new MultipleConfigs(this, null, "settings.yml", false);

        if(getSettings().getBoolean("discord-bot.toggle"))
            jdaBot();

        registerEvents();
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[SakiLogger] Disabled");
    }

    private void jdaBot(){
        try {
            JDABuilder jda = JDABuilder.createDefault(getSettings().getString("discord-bot.token"));
            jda.setAutoReconnect(true);
            api = jda.build();
        } catch (LoginException e) {
            Bukkit.getConsoleSender().sendMessage("§c[SakiLogger] Occured a error with loading the Discord Bot / Check your token in'settings.yml'");
        }
    }

    public void logToFile(String message)
    {
        try
        {
            File dataFolder = getDataFolder();
            if(!dataFolder.exists()) {
                dataFolder.mkdir();
            }
            File saveTo = new File(getDataFolder(), "logs.txt");
            if (!saveTo.exists()) {
                saveTo.createNewFile();
            }
            FileWriter fw = new FileWriter(saveTo, true);
            PrintWriter pw = new PrintWriter(fw);
            pw.println(message);
            pw.flush();
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerEvents(){
        if(getSettings().getBoolean("player-join.toggle")) Bukkit.getPluginManager().registerEvents(new onJoinEvent(), this);
        if(getSettings().getBoolean("player-quit.toggle")) Bukkit.getPluginManager().registerEvents(new onQuitEvent(), this);
        if(getSettings().getBoolean("player-gamemode.toggle")) Bukkit.getPluginManager().registerEvents(new onGamemodeEvent(), this);
        if(getSettings().getBoolean("player-chat.toggle")) Bukkit.getPluginManager().registerEvents(new onChatEvent(), this);
        if(getSettings().getBoolean("player-command.toggle")) Bukkit.getPluginManager().registerEvents(new onCommandEvent(), this);
        if(getSettings().getBoolean("player-block-place.toggle")) Bukkit.getPluginManager().registerEvents(new onBlockPlaceEvent(), this);
        if(getSettings().getBoolean("player-block-break.toggle")) Bukkit.getPluginManager().registerEvents(new onBlockBreakEvent(), this);
    }

    public static LoggerMain getInstance() {
        return instance;
    }

    public MultipleConfigs getSettings() {
        return settings;
    }

    public JDA getApi() {
        return api;
    }
}
