package ru.clevertec.userClient.config;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;
import ru.clevertec.userClient.aop.LoggAuthAspect;
import ru.clevertec.userClient.aop.LoggUserAspect;

import static org.assertj.core.api.Assertions.assertThat;

class LoggingAutoConfigurationTest {
    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner();

    @Test
    public void shouldReturnLoggAuthAspectBeanIfEnabledTrue() {
        contextRunner.withPropertyValues("aop.logging.enabled=true")
                .withUserConfiguration(LoggingAutoConfiguration.class)
                .run(context ->
                        assertThat(context).hasSingleBean(LoggAuthAspect.class)
                );
    }

    @Test
    public void shouldReturnLoggUserAspectBeanIfEnabledTrue() {
        contextRunner.withPropertyValues("aop.logging.enabled=true")
                .withUserConfiguration(LoggingAutoConfiguration.class)
                .run(context ->
                        assertThat(context).hasSingleBean(LoggUserAspect.class)
                );
    }

    @Test
    public void shouldCheckNotCreateLoggAuthAspectBeanIfEnabledFalse() {
        contextRunner.withPropertyValues("aop.logging.enabled=false")
                .withUserConfiguration(LoggingAutoConfiguration.class)
                .run(context ->
                        assertThat(context).doesNotHaveBean(LoggAuthAspect.class)
                );
    }

    @Test
    public void shouldCheckNotCreateLoggUserAspectBeanIfEnabledFalse() {
        contextRunner.withPropertyValues("aop.logging.enabled=false")
                .withUserConfiguration(LoggingAutoConfiguration.class)
                .run(context ->
                        assertThat(context).doesNotHaveBean(LoggUserAspect.class)
                );
    }
}