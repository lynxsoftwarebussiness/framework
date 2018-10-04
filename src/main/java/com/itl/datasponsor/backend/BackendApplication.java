package com.itl.datasponsor.backend;

import com.itl.datasponsor.backend.entities.Role;
import com.itl.datasponsor.backend.entities.User;
import com.itl.datasponsor.backend.repository.core.RoleRepository;
import com.itl.datasponsor.backend.repository.core.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
public class BackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(RoleRepository roleRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            Role role = new Role();
            role.setName("ROLE_ADMIN");
            roleRepository.save(role);
            List<Role> roles = roleRepository.findAll();
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("user"));
            user.setRoles(roles);
            userRepository.save(user);
        };
    }
}
