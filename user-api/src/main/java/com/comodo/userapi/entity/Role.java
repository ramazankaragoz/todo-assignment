package com.comodo.userapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Ramazan Karagöz
 * @date 9/2/2019
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "role")
public class Role extends BaseEntity{

    @Column(name = "role_name")
    private String roleName;

    @ManyToMany(cascade = CascadeType.PERSIST,fetch = FetchType.EAGER)
    @JoinTable(name = "privileges_roles",
            joinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id",referencedColumnName = "id"))
    private Collection<Privilege> privileges=new ArrayList<>();

}
