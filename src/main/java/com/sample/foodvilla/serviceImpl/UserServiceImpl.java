package com.sample.foodvilla.serviceImpl;

import com.sample.foodvilla.entity.User;
import com.sample.foodvilla.repository.UserRepository;
import com.sample.foodvilla.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User authenticateUser(String username, String password, String userType) {
        return userRepository.findByUsernameAndPasswordAndUserType(username, password, userType);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }
}
