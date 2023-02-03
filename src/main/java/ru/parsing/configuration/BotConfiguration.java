package ru.parsing.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(
        prefix = "bot")
@Data
public class BotConfiguration {

    String name;
    String token;
    Long owner;
}
