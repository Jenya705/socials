package dev.jenya705.socials;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.plugin.Plugin;
import com.velocitypowered.api.plugin.annotation.DataDirectory;
import dev.jenya705.socials.config.ConfigFormat;
import lombok.Getter;
import org.slf4j.Logger;

import java.nio.file.Path;

@Plugin(
        name = "Socials",
        id = "socials",
        authors = "Jenya705",
        description = "Socializing server",
        version = "1.0-SNAPSHOT"
)
@Getter
public class SocialsVelocityPlugin implements SocialsBootstrap {

    private final Logger logger;
    private final Path directoryPath;

    private SocialsApplication application;

    @Inject
    public SocialsVelocityPlugin(Logger logger, @DataDirectory Path directoryPath) {
        this.logger = logger;
        this.directoryPath = directoryPath;
        directoryPath.toFile().mkdirs();
    }

    @Override
    public ConfigFormat getConfigFormat() {
        return ConfigFormat.TOML;
    }

    @Subscribe
    public void initialization(ProxyInitializeEvent event) {
        application = new SocialsApplication(this);
    }

}
