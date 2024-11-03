package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;
@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    public Boolean existsByEmail(String email);

    public User findByEmail(String email);

}
