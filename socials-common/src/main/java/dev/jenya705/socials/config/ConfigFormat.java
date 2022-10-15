package dev.jenya705.socials.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ConfigFormat {

    YML("yml"),
    JSON("json"),
    TOML("toml"),
    ;

    private final String extension;

}
