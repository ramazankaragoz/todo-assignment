package com.comodo.todoapi.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Objects;
import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String name=null;

        if (Objects.nonNull(authentication)){
            name= authentication.getName();
        }

        return (name!=null)?Optional.of(name):Optional.of("SYSTEM");
    }
}
