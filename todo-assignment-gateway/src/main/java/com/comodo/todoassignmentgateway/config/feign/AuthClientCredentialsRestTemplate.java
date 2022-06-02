package com.comodo.todoassignmentgateway.config.feign;

import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;

public class AuthClientCredentialsRestTemplate extends OAuth2RestTemplate {

    public AuthClientCredentialsRestTemplate(OAuth2ProtectedResourceDetails resource) {
        super(resource);
    }

    public AuthClientCredentialsRestTemplate(OAuth2ProtectedResourceDetails resource, OAuth2ClientContext context) {
        super(resource, context);
    }
}
