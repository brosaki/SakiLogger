package net.manosaki.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.manosaki.LoggerMain;
import net.manosaki.utils.LocationSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class onQuitEvent implements Listener {
    private final LoggerMain loggerMain = LoggerMain.getInstance();
    @EventHandler (ignoreCancelled = true)
    public void event(PlayerQuitEvent e) {
        if(loggerMain.getSettings().getBoolean("logs-settings.log-bypass")) if(e.getPlayer().hasPermission("sakilogger.bypass")) return;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(loggerMain.getSettings().getString("logs-settings.time-format"));

        if(loggerMain.getApi() != null){
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle(loggerMain.getSettings().getString("player-quit.embed.title"));
            embedBuilder.addField(loggerMain.getSettings().getString("player-quit.embed.field.text"), loggerMain.getSettings().getString("player-quit.embed.field.value")
                    .replace("%nl%", "\n")
                    .replace("{player_name}", e.getPlayer().getName())
                    .replace("{player_coords}", LocationSerializer.getSerializedLocation(e.getPlayer().getLocation()))
                    .replace("{server_ip}", e.getPlayer().getServer().getIp())
                    .replace("{date_time}", dateTimeFormatter.format(ZonedDateTime.now()))
                    .replace("{server_name}", e.getPlayer().getServer().getName()), false);
            embedBuilder.setFooter(loggerMain.getSettings().getString("player-quit.embed.footer.text"), loggerMain.getSettings().getString("player-quit.embed.footer.image"));
            TextChannel channel = loggerMain.getApi().getTextChannelById(loggerMain.getSettings().getString("discord-bot.discord-logs-channel"));
            if(channel != null){
                channel.sendMessageEmbeds(embedBuilder.build()).queue();
            }
        }

        if(loggerMain.getSettings().getBoolean("logs-file.toggle")){
            loggerMain.logToFile(loggerMain.getSettings().getString("player-quit.file")
                    .replace("%nl%", "\n")
                    .replace("{player_name}", e.getPlayer().getName())
                    .replace("{player_coords}", LocationSerializer.getSerializedLocation(e.getPlayer().getLocation()))
                    .replace("{server_ip}", e.getPlayer().getServer().getIp())
                    .replace("{date_time}", dateTimeFormatter.format(ZonedDateTime.now()))
                    .replace("{server_name}", e.getPlayer().getServer().getName()));
        }
    }
}
