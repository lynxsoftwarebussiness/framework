package com.itl.datasponsor.backend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.List;

@Entity
@Setter
@Getter
public class User extends BaseEntity {

    public User() {
        isActive = true;
    }

    @Column(unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Boolean isActive;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles;
}
