package ru.parsing.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ConfigurationProperties(
        prefix = "bot")
@Data
@EnableScheduling
public class BotConfiguration {

    String name;
    String token;
    Long owner;
}
