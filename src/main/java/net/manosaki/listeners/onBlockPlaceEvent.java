package net.manosaki.listeners;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.manosaki.LoggerMain;
import net.manosaki.utils.LocationSerializer;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class onBlockPlaceEvent implements Listener {
    private final LoggerMain loggerMain = LoggerMain.getInstance();
    @EventHandler
    public void event(BlockPlaceEvent e) {
        if(!loggerMain.getSettings().getStringList("player-block-place.registered-blocks").contains(e.getBlock().getType().name())) return;

        if(loggerMain.getSettings().getBoolean("logs-settings.log-bypass")) if(e.getPlayer().hasPermission("sakilogger.bypass")) return;

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(loggerMain.getSettings().getString("logs-settings.time-format"));

        if(loggerMain.getApi() != null){
            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setTitle(loggerMain.getSettings().getString("player-block-place.embed.title"));
            embedBuilder.addField(loggerMain.getSettings().getString("player-block-place.embed.field.text"), loggerMain.getSettings().getString("player-block-place.embed.field.value")
                    .replace("%nl%", "\n")
                    .replace("{player_name}", e.getPlayer().getName())
                    .replace("{player_coords}", LocationSerializer.getSerializedLocation(e.getPlayer().getLocation()))
                    .replace("{block_material}", e.getBlock().getType().name())
                    .replace("{block_coords}", LocationSerializer.getSerializedLocation(e.getBlock().getLocation()))
                    .replace("{player_coords}", LocationSerializer.getSerializedLocation(e.getPlayer().getLocation()))
                    .replace("{server_ip}", e.getPlayer().getServer().getIp())
                    .replace("{date_time}", dateTimeFormatter.format(ZonedDateTime.now()))
                    .replace("{server_name}", e.getPlayer().getServer().getName()), false);
            embedBuilder.setFooter(loggerMain.getSettings().getString("player-block-place.embed.footer.text"), loggerMain.getSettings().getString("player-block-place.embed.footer.image"));
            TextChannel channel = loggerMain.getApi().getTextChannelById(loggerMain.getSettings().getString("discord-bot.discord-logs-channel"));
            if(channel != null){
                channel.sendMessageEmbeds(embedBuilder.build()).queue();
            }
        }

        if(loggerMain.getSettings().getBoolean("logs-file.toggle")){
            loggerMain.logToFile(loggerMain.getSettings().getString("player-block-place.file")
                    .replace("%nl%", "\n")
                    .replace("{player_name}", e.getPlayer().getName())
                    .replace("{player_coords}", LocationSerializer.getSerializedLocation(e.getPlayer().getLocation()))
                    .replace("{block_material}", e.getBlock().getType().name())
                    .replace("{block_coords}", LocationSerializer.getSerializedLocation(e.getBlock().getLocation()))
                    .replace("{server_ip}", e.getPlayer().getServer().getIp())
                    .replace("{date_time}", dateTimeFormatter.format(ZonedDateTime.now()))
                    .replace("{server_name}", e.getPlayer().getServer().getName()));
        }
    }
}
