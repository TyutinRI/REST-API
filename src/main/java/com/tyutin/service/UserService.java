package com.tyutin.service;

import com.tyutin.model.User;

import java.util.List;

public interface UserService {

    User getUserById(Long id);

    List<User> getAll();

    void createUser(User user);

    void deleteUserById(Long id);

    User updateUser(User user, Long id);

}
