package com.sample.foodvilla.service;

import com.sample.foodvilla.entity.User;

import java.util.List;

public interface UserService {
    User authenticateUser(String username, String password, String userType);

    List<User> getUsers();


    User registerUser(User user);
}
