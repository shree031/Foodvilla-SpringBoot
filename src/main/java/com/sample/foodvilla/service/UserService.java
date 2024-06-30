package com.sample.foodvilla.service;

import com.sample.foodvilla.entity.User;
import com.sample.foodvilla.entity.dto.UserDto;
import com.sample.foodvilla.model.UserType;

import java.util.List;

public interface UserService {
    UserDto authenticateUser(String username, String password, UserType userType);

    List<User> getUsers();

    User registerUser(User user);

    void deleteUser(Long userId);
}
