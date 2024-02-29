package ru.clevertec.userClient.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(info = @Info(
        description = "OpenApi documentation",
        title = "OpenApi specification - userClient",
        termsOfService = "Term of service"
),
        servers = @Server(
                description = "Local ENV",
                url = "http://localhost:8081"
        ))
@Configuration
public class SwaggerConfig {
}
