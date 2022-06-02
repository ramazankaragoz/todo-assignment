package com.comodo.todoassignmentgateway.config.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (authentication == null) {
            log.warn("Permission denied because of illegal 'authentication' argument (null)");
            return false;
        }

        if (targetDomainObject != null && !(targetDomainObject instanceof String)) {
            log.warn("Permission denied because of illegal 'targetClientId' argument ({})", targetDomainObject.getClass().getName());
            return false;
        }

        if (permission != null && !(permission instanceof String)) {
            log.warn("Permission denied because of illegal 'resourceName' argument ({})", permission.getClass().getName());
            return false;
        }
        Set<String> authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
        return authorities.contains(permission);
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
