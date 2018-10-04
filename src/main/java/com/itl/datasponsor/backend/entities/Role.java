package com.itl.datasponsor.backend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Setter
@Getter
public class Role extends BaseEntity {

    private String name;
}
