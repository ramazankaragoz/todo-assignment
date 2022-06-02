package com.comodo.todoassignmentgateway.config.security;


import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import static com.comodo.todoassignmentgateway.config.security.JWTSecurityConstant.TOKEN_PREFIX;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {


    private Collection<? extends GrantedAuthority> grantedAuthorities;

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader(JWTSecurityConstant.HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
        if (Objects.nonNull(authentication))
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        chain.doFilter(request, response);
    }


    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(JWTSecurityConstant.HEADER_STRING);
        if (token != null) {

            String user = Jwts.parser()
                    .setSigningKey(JWTSecurityConstant.SECRET.getBytes())
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .getBody()
                    .getSubject();

            if (user != null) {
                if ((grantedAuthorities = UserDetailsServiceImpl.grantedAuthorities) == null) {
                    grantedAuthorities = new ArrayList<>();
                }
                return new UsernamePasswordAuthenticationToken(user, null, grantedAuthorities);
            }
            return null;
        }
        return null;
    }
}
