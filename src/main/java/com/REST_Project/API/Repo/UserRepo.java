package com.REST_Project.API.Repo;

import com.REST_Project.API.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Long> {
}
