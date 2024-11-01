package com.adolfo.cordoba.test.gs.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("default|dev")
public class OpenApiConfiguration {
    @Value("${info.app.name}")
    private String projectName;
    @Value("${info.app.description}")
    private String projectDescription;

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI().info(new Info().title(projectName)
                .description(projectDescription).version("1.0.0"));
    }
}
