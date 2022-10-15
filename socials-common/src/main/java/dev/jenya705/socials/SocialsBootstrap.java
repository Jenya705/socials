package dev.jenya705.socials;

import dev.jenya705.socials.config.ConfigFormat;
import org.slf4j.Logger;

import java.nio.file.Path;

public interface SocialsBootstrap {

    Path getDirectoryPath();

    Logger getLogger();

    ConfigFormat getConfigFormat();

}
