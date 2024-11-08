package ru.clevertec.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.exeption.ExeptionHandler;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "exception", name = "enabled", havingValue = "true")
public class ExeptionAutoConfiguration {

    @PostConstruct
    void init() {
        log.info("Инициализация ExeptionAutoConfiguration");
    }

    @Bean
    public ExeptionHandler exeptionHandler() {
        return new ExeptionHandler();
    }
}
