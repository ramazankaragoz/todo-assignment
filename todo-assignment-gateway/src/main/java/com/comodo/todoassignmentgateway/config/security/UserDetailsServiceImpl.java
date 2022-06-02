package com.comodo.todoassignmentgateway.config.security;


import com.comodo.todoassignmentgateway.client.UserClient;
import com.comodo.todoassignmentgateway.dto.UserInformationDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserClient userClient;
    public static Collection<? extends GrantedAuthority> grantedAuthorities;

    public UserDetailsServiceImpl(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        ResponseEntity<UserInformationDto> userInformationDtoResponse = userClient.getInformation(s);
        UserInformationDto user=userInformationDtoResponse.getBody();

        if (user==null){
            throw new UsernameNotFoundException(s+" berlitilen kullanıcı bulunamadı!!");
        }

        grantedAuthorities=getAuthorities(user.getPrivileges());

        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(), user.getEnabled(),
                user.getEnabled(),
                user.getEnabled(),
                user.getEnabled(),
                getAuthorities(user.getPrivileges()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<String> privileges){
        return getGrantedAuthority(privileges);
    }

    private List<GrantedAuthority> getGrantedAuthority(List<String> privileges){
        List<GrantedAuthority> authorities=new ArrayList<>();
        for (String privilege:privileges){
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }
}
