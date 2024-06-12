package com.sample.foodvilla.restController;

import com.sample.foodvilla.entity.User;
import com.sample.foodvilla.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody User userLoginRequest) {
        // Validate user credentials
        User user = userService.authenticateUser(userLoginRequest.getUsername(), userLoginRequest.getPassword(), userLoginRequest.getUserType());
        if (user != null) {
            // Return user details if login is successful
            return ResponseEntity.ok(user);
        } else {
            // Return 401 Unauthorized if login fails
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User userRegistrationRequest) {

        // Save user to database
        User savedUser = userService.registerUser(userRegistrationRequest);

        return ResponseEntity.ok(savedUser);
    }
}
