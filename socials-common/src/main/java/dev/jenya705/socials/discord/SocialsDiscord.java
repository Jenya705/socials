package dev.jenya705.socials.discord;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import dev.jenya705.socials.SocialsApplication;
import dev.jenya705.socials.config.ConfigDefaults;
import discord4j.core.DiscordClient;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.object.presence.Activity;
import discord4j.core.object.presence.ClientActivity;
import discord4j.core.object.presence.ClientPresence;
import discord4j.core.object.presence.Status;
import lombok.Getter;

public class SocialsDiscord {

    public static CommentedFileConfig loadConfig(SocialsApplication application) {
        return new ConfigDefaults(application.loadConfig("discord"))
                .value("token", "", "Discord bot token")
                .comment("presence", "Presence of discord bot")
                .value("presence.status", "online", "Possible values: online, dnd, idle, invisible, offline")
                .value("presence.type", "playing", "Type of activity", "Possible values: playing, streaming, listening, watching, competing")
                .value("presence.name", "Minecraft", "Value for activity")
                .value("presence.url", "", "Used only for streaming")
                .getConfig();
    }

    public static SocialsDiscord create(SocialsApplication application) {
        try (CommentedFileConfig discordConfig = loadConfig(application)) {
            String token = discordConfig.get("token");
            if (token.isBlank()) return null;
            return new SocialsDiscord(application, discordConfig);
        }
    }

    @Getter
    private final GatewayDiscordClient client;

    private SocialsDiscord(SocialsApplication application, CommentedFileConfig discordConfig) {
        client = DiscordClient
                .create(discordConfig.get("token").toString())
                .login()
                .blockOptional()
                .orElseThrow(() -> new IllegalArgumentException("Bad discord bot token"));
        client.updatePresence(ClientPresence.of(
                Status.of(discordConfig.<String>get("presence.status").toLowerCase()),
                ClientActivity.of(
                        Activity.Type.valueOf(discordConfig.<String>get("presence.type").toUpperCase()),
                        discordConfig.get("presence.name"),
                        discordConfig.get("presence.url")
                )
        )).block();
    }

}
