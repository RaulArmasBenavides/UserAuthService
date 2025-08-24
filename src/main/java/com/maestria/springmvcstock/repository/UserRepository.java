package com.maestria.springmvcstock.repository;

 
import org.springframework.data.jpa.repository.JpaRepository;

import com.maestria.springmvcstock.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}