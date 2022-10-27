package com.example.roombookingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.roombookingapp.entities.Role;


public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByCode(String code);

}
