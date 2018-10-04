package com.itl.datasponsor.backend.controller.rest_controller;

import com.itl.datasponsor.backend.dtos.create_dto.UserCreateDto;
import com.itl.datasponsor.backend.dtos.simple_dto.UserSimpleDto;
import com.itl.datasponsor.backend.service.core.BaseService;
import com.itl.datasponsor.backend.service.core.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController<UserSimpleDto, UserSimpleDto, UserCreateDto> {

    @Autowired
    private UserService userService;

    @Override
    protected BaseService<UserSimpleDto, UserSimpleDto, UserCreateDto> getService() {
        return userService;
    }

    @Override
    protected Logger getLogger() {
        return log;
    }
}
