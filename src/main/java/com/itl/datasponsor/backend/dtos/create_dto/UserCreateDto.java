package com.itl.datasponsor.backend.dtos.create_dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserCreateDto extends BaseCreateDto {
    private String username;
    private String password;
    private List<Long> roleIds;
}
