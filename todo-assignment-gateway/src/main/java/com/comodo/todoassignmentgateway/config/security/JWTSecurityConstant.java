package com.comodo.todoassignmentgateway.config.security;

public class JWTSecurityConstant {
    public static final String SECRET="COMODO_JWT";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final long EXPIRATION_TIME = 1_300_000;
}
