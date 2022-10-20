package com.example.roombookingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.roombookingapp.entities.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
