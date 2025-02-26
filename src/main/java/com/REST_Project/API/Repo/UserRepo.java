package com.REST_Project.API.Repo;

import com.REST_Project.API.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;


public interface UserRepo extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email);
}
