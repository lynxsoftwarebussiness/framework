package com.itl.datasponsor.backend.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Setter
@Getter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //metadata
    private Date create_on;
    private Date update_on;
    private Boolean isDeleted;

    private Long create_by;
    private Long update_by;
    private Long delete_by;
}
