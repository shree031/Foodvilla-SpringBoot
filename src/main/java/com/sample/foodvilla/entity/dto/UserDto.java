package com.sample.foodvilla.entity.dto;


import com.sample.foodvilla.entity.User;
import com.sample.foodvilla.model.UserType;

import java.util.ArrayList;
import java.util.List;

public class UserDto {
    private Long id;
    private String username;
    private UserType userType;
    private String email;
    private String address;

    public UserDto(Long userId) {
        this.setId(userId);
    }

    public UserDto() {}

    public UserDto(Long id, String username, UserType userType, String email, String address) {
        this.id = id;
        this.username = username;
        this.userType = userType;
        this.email = email;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public static List<UserDto> toDtoList(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(toDto(user));
        }
        return userDtos;
    }

    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setAddress(user.getAddress());
        userDto.setUserType(user.getUserType());
        return userDto;
    }
}
