package com.library.server.models;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.library.server.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    
    User findByPhone(String phone);

    Page<User> findUserByName(String name, Pageable page);

    Page<User> findAll(Pageable page);
}
