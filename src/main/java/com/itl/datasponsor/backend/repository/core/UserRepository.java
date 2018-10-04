package com.itl.datasponsor.backend.repository.core;

import com.itl.datasponsor.backend.entities.User;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends BaseRepository<User> {

    @Query("SELECT u from User u where u.username = ?1")
    User getUserByUsername(String username);
}
