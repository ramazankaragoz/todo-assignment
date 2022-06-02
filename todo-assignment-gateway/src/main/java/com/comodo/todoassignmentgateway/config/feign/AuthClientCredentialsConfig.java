package com.comodo.todoassignmentgateway.config.feign;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class AuthClientCredentialsConfig {

    @Value("${client.auth-server.token-url}")
    private String accessTokenUri;
    @Value("${client.auth-server.client-id}")
    private String clientId;
    @Value("${client.auth-server.secret}")
    private String clientSecret;
    @Value("${client.auth-server.scopes}")
    private List<String> scopes=new ArrayList<>(0);

    @Bean
    public AuthClientCredentialsRestTemplate createRestTemplate() {
        return new AuthClientCredentialsRestTemplate(getClientCredentialsResourceDetails(),
                new DefaultOAuth2ClientContext());
    }

    private ClientCredentialsResourceDetails getClientCredentialsResourceDetails() {
        return getClientCredentialsResourceDetails(accessTokenUri, scopes);
    }

    private ClientCredentialsResourceDetails getClientCredentialsResourceDetails(String accessTokenUri, List<String> scopes) {
        ClientCredentialsResourceDetails clientCredentialsResourceDetails =
                new ClientCredentialsResourceDetails();

        clientCredentialsResourceDetails.setAccessTokenUri(accessTokenUri);
        clientCredentialsResourceDetails.setAuthenticationScheme(AuthenticationScheme.header);
        clientCredentialsResourceDetails.setClientId(clientId);
        clientCredentialsResourceDetails.setClientSecret(clientSecret);
        clientCredentialsResourceDetails.setScope(scopes);
        return clientCredentialsResourceDetails;
    }
}
