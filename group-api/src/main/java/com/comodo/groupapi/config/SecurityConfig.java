package com.comodo.groupapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .mvcMatcher("/todo/**")
                .authorizeRequests()
                .mvcMatchers("/group/**").access("hasAuthority('SCOPE_todo.read')")
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }

}
