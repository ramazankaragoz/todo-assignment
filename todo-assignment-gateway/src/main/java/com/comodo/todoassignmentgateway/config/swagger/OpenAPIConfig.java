package com.comodo.todoassignmentgateway.config.swagger;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {



        return new OpenAPI()
                .components(new Components().addSecuritySchemes("JWT", new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .name("bearerAuth")
                        ))
                .info(new Info()
                        .title("ToDo Gateway API")
                        .version("v3")
                        .description("Gateway API reference for developers")
                        .termsOfService("https://java.com/")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
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
