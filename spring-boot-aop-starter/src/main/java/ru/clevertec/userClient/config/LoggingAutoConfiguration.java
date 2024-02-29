package ru.clevertec.userClient.config;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.clevertec.userClient.aop.LoggAuthAspect;
import ru.clevertec.userClient.aop.LoggUserAspect;

@Slf4j
@Configuration
@ConditionalOnProperty(prefix = "aop.logging", name = "enabled", havingValue = "true", matchIfMissing = true)
public class LoggingAutoConfiguration {
    /**
     * Initializes the LoggingAutoConfiguration class and logs the initialization message.
     */
    @PostConstruct
    void init() {
        log.info("LoggingAutoConfiguration initialized");
    }
    @Bean
    @ConditionalOnMissingBean
    public LoggAuthAspect loggAuthAspect() {

        return new LoggAuthAspect();
    }
    @Bean
    @ConditionalOnMissingBean
    public LoggUserAspect loggUserAspect(){
        return new LoggUserAspect();
    }
}
