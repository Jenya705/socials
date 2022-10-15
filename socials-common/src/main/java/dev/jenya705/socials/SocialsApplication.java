package dev.jenya705.socials;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import dev.jenya705.socials.discord.SocialsDiscord;
import lombok.Getter;

import java.util.Optional;

public class SocialsApplication {

    private final SocialsDiscord discord;

    @Getter
    private final SocialsBootstrap bootstrap;

    public SocialsApplication(SocialsBootstrap bootstrap) {
        this.bootstrap = bootstrap;
        discord = SocialsDiscord.create(this);
        getDiscord().ifPresentOrElse(
                it -> bootstrap.getLogger().info("Discord linkage activated"),
                () -> bootstrap.getLogger().warn("No discord linkage, because token wasn't given")
        );
    }

    public CommentedFileConfig loadConfig(String name) {
        CommentedFileConfig config = CommentedFileConfig
                .builder(bootstrap.getDirectoryPath().resolve(name + "." + bootstrap.getConfigFormat().getExtension()))
                .autosave()
                .build();
        config.load();
        return config;
    }

    public Optional<SocialsDiscord> getDiscord() {
        return Optional.ofNullable(discord);
    }

}
