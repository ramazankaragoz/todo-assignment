package com.comodo.todoassignmentgateway.config.swagger;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;


@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        final String securitySchemeName = "bearerAuth";
        final String apiTitle = String.format("%s API", StringUtils.capitalize("TODO"));

        return new OpenAPI().addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components().addSecuritySchemes(securitySchemeName,
                        new SecurityScheme().name(securitySchemeName)
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")))
                .info(new Info().title(apiTitle)
                        .version("1.0"));
    }

    @Bean
    public GroupedOpenApi defaultOpenApi() {
        String[] paths = {"/**"};
        return GroupedOpenApi.builder().group("default").pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi identityOpenApi() {
        String[] paths = {"/user/**"};
        return GroupedOpenApi.builder().group("user").pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi managementOpenApi() {
        String[] paths = {"/todo/**"};
        return GroupedOpenApi.builder().group("todo").pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi prescriptionOpenApi() {
        String[] paths = {"/group/**"};
        return GroupedOpenApi.builder().group("group").pathsToMatch(paths)
                .build();
    }
}
