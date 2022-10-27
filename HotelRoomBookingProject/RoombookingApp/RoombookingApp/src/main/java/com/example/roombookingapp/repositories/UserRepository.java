package com.example.roombookingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.roombookingapp.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
}
