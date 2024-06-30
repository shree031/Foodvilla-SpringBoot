package com.sample.foodvilla.serviceImpl;

import com.sample.foodvilla.entity.User;
import com.sample.foodvilla.entity.dto.UserDto;
import com.sample.foodvilla.model.UserType;
import com.sample.foodvilla.repository.CartItemRepository;
import com.sample.foodvilla.repository.ProductDistributorRepository;
import com.sample.foodvilla.repository.UserRepository;
import com.sample.foodvilla.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductDistributorRepository productDistributorRepository;
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto authenticateUser(String username, String password, UserType userType) {
        User user = userRepository.findByUsername(username);
        System.err.println(user.getPassword());
        byte[] decodedBytes = Base64.getDecoder().decode(password);
        String decodedPassword = new String(decodedBytes);
        if (user != null && bCryptPasswordEncoder.matches(decodedPassword, user.getPassword())) {
            return new UserDto(user.getId(), user.getUsername(), user.getUserType(), user.getEmail(), user.getAddress());
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

    @Transactional
    public void deleteUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserType userType = user.getUserType(); // Assuming you have a getUserType() method

            // Perform deletions based on user type
            if (UserType.DISTRIBUTOR == userType) {
                // Remove associations in product_distributor
                productDistributorRepository.deleteByUserId(userId);
            } else if (UserType.USER == userType) {
                // Remove associated cart items
                cartItemRepository.deleteByUserId(userId);
            }

            // Delete the user
            userRepository.deleteById(userId);
        } else {
            throw new EntityNotFoundException("User not found with id: " + userId);
        }
    }

}
