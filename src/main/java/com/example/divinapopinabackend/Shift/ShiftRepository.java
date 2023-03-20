package com.example.divinapopinabackend.Shift;

import com.example.divinapopinabackend.Order.Order;
import com.example.divinapopinabackend.Reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
    @Query(value =  " SELECT *  "
            +  " FROM shift  "
            +  " WHERE date_of_shift = :key "
            , nativeQuery = true)
    List<Shift> findShiftByDate(Date key);
}