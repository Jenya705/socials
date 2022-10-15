package dev.jenya705.socials.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ConfigDefaults {

    @Getter
    private final CommentedFileConfig config;

    public ConfigDefaults value(String key, Object value) {
        if (config.get(key) == null) config.add(key, value);
        return this;
    }

    public ConfigDefaults comment(String key, String... value) {
        config.setComment(key, " " + String.join("\n ", value));
        return this;
    }

    public ConfigDefaults value(String key, Object value, String... comments) {
        return comment(key, comments).value(key, value);
    }

}
