package com.demo.service;

import java.util.List;

import com.demo.model.User;
import com.demo.model.UserDto;

public interface UserService {

    User save(UserDto user);
    List<User> findAll();
    void delete(long id);
    User findOne(String username);

    User findById(Long id);
}
