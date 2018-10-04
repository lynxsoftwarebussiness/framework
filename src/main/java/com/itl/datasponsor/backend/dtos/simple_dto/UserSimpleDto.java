package com.itl.datasponsor.backend.dtos.simple_dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UserSimpleDto extends BaseSimpleDto {

    private String username;
    private Boolean isActive;

    private List<RoleSimpleDto> roleSimpleDtos;
}
