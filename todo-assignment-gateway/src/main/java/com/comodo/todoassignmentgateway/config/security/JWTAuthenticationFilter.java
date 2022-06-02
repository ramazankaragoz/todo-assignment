package com.comodo.todoassignmentgateway.config.security;

import com.comodo.todoassignmentgateway.dto.UserInformationDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    private final AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            UserInformationDto user=new ObjectMapper().readValue(request.getInputStream(), UserInformationDto.class);
            return  authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword(),new ArrayList<>())
            );

        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {

        String token= Jwts.builder()
                .setSubject(((org.springframework.security.core.userdetails.User)authResult.getPrincipal()).getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+JWTSecurityConstant.EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512,JWTSecurityConstant.SECRET.getBytes()).compact();

        response.addHeader(JWTSecurityConstant.HEADER_STRING,JWTSecurityConstant.TOKEN_PREFIX+token);

    }
}
