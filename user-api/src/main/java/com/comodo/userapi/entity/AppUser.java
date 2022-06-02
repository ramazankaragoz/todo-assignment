package com.comodo.userapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usr_user")
public class AppUser extends BaseEntity{

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "password",nullable = false)
    private String password;

    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id",nullable = false),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id",nullable = false))
    private Collection<Role> roles=new ArrayList<>();
}
