package com.comodo.todoassignmentgateway.config.feign;

import feign.RequestInterceptor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class FeignClientConfig {
    private final AuthClientCredentialsRestTemplate restTemplate;

    public FeignClientConfig(AuthClientCredentialsRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> {

            requestTemplate.header(HeaderConstant.HEADER.AUTHORIZATION, HeaderConstant.HEADER.BEARER + getClientAccessToken());
        };
    }


    public String getClientAccessToken() {

        String accessToken = "";
        if (restTemplate != null && restTemplate.getAccessToken() != null)
            accessToken = restTemplate.getAccessToken().getValue();

        return accessToken;
    }
}
