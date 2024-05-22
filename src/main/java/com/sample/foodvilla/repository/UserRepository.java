package com.sample.foodvilla.repository;

import com.sample.foodvilla.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPasswordAndUserType(String username, String password, String userType);

}
