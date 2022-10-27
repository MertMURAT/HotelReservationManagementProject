package com.example.roombookingapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.roombookingapp.entities.Booking;

import java.util.Date;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    List<Booking> findAllByDate(Date date);
    
    @Query(value = "from Booking b where date BETWEEN :startDate AND :endDate")
    public List<Booking> getAllBetweenDates(@Param("startDate")Date startDate,@Param("endDate")Date endDate);
    
    @Query(value = "from Booking b where ( b.date BETWEEN :startDate AND :endDate ) and b.user.email= :email")
    public List<Booking> getAllBetweenDatesAndUserEmail(@Param("startDate")Date startDate,@Param("endDate")Date endDate,@Param("email") String email);
    
    List<Booking> findAllByDateAndUserEmail(Date date,String email);
    List<Booking> findByUserEmail(String email);
}
