package com.sample.foodvilla.serviceImpl;

import com.sample.foodvilla.entity.User;
import com.sample.foodvilla.repository.UserRepository;
import com.sample.foodvilla.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public User authenticateUser(String username, String password, String userType) {
        User user = userRepository.findByUsername(username);
        System.err.println(user.getPassword());
        byte[] decodedBytes = Base64.getDecoder().decode(password);
        String decodedPassword = new String(decodedBytes);
        if (user != null && bCryptPasswordEncoder.matches(decodedPassword, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerUser(User restUser) {
        byte[] decodedBytes = Base64.getDecoder().decode(restUser.getPassword());
        String decodedPassword = new String(decodedBytes);
        // Encrypt the password using BCrypt
        String encryptedPassword = bCryptPasswordEncoder.encode(decodedPassword);

        User user = new User();
        user.setUsername(restUser.getUsername());
        user.setPassword(encryptedPassword);
        user.setEmail(restUser.getEmail()); // Set email ID
        user.setUserType(restUser.getUserType());
        user.setAddress(restUser.getAddress());
        return userRepository.save(user);
    }
}
