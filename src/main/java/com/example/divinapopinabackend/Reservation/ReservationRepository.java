package com.example.divinapopinabackend.Reservation;

import com.example.divinapopinabackend.Food.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ReservationRepository  extends JpaRepository<Reservation, Long> {


    @Query(value =  " SELECT *  "
            +  " FROM reservation  "
            +  " WHERE date_of_event = :key "
            , nativeQuery = true)
    List<Reservation> findReservationByDate(Date key);

    @Query(value =  " SELECT *  "
            +  " FROM reservation  "
            +  " WHERE name = :key "
            , nativeQuery = true)
    List<Reservation> findReservationByName(String key);

    @Query(value =  " SELECT Distinct Name "
            +  " FROM reservation  "
            , nativeQuery = true)
    List<String> findReservationNames();
}