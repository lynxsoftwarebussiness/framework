package com.itl.datasponsor.backend.service.impl;

import com.itl.datasponsor.backend.dtos.create_dto.UserCreateDto;
import com.itl.datasponsor.backend.dtos.simple_dto.RoleSimpleDto;
import com.itl.datasponsor.backend.dtos.simple_dto.UserSimpleDto;
import com.itl.datasponsor.backend.entities.User;
import com.itl.datasponsor.backend.repository.core.RoleRepository;
import com.itl.datasponsor.backend.repository.core.UserRepository;
import com.itl.datasponsor.backend.service.core.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@Log4j2
public class UserServiceImpl extends BaseServiceImpl<User, UserSimpleDto, UserSimpleDto, UserCreateDto> implements UserService {

    @Autowired
    private RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository) {
        super(userRepository);
    }

    @Override
    protected Logger getLogger() {
        return log;
    }

    @Override
    protected User createEntity(UserCreateDto userCreateDto) {
        User user = new User();
        user.setUsername(userCreateDto.getUsername());
        user.setPassword(userCreateDto.getPassword());
        user.setRoles(roleRepository.findAllById(userCreateDto.getRoleIds()));
        return user;
    }

    @Override
    protected User createEntity(Long id) {
        User user = new User();
        user.setId(id);
        return user;
    }

    @Override
    protected UserSimpleDto createSimpleDto(User entity) {
        UserSimpleDto userSimpleDto = new UserSimpleDto();
        userSimpleDto.setUsername(entity.getUsername());
        userSimpleDto.setIsActive(entity.getIsActive());
        userSimpleDto.setRoleSimpleDtos(entity.getRoles().stream().map(role -> {
            RoleSimpleDto roleSimpleDto = new RoleSimpleDto();
            roleSimpleDto.setId(role.getId());
            roleSimpleDto.setName(role.getName());
            return roleSimpleDto;
        }).collect(Collectors.toList()));
        return userSimpleDto;
    }

    @Override
    protected UserSimpleDto createDetailDto(User entity) {
        return createSimpleDto(entity);
    }
}
