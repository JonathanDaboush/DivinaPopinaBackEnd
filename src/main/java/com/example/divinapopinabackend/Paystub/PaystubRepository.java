package com.example.divinapopinabackend.Paystub;

import com.example.divinapopinabackend.Employee.Employee;
import com.example.divinapopinabackend.Order.Order;
import com.example.divinapopinabackend.Reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PaystubRepository extends JpaRepository<Paystub, Long> {
    @Query(value =  " SELECT *  "
            +  " FROM paystub  "
            +  " WHERE email = :key "
            , nativeQuery = true)
    List<Paystub> findPaystubByEmail(String key);

    @Query(value =  " SELECT *  "
            +  " FROM paystub "
            +  " WHERE phone_number = :key "
            , nativeQuery = true)
    List<Paystub> findPaystubByPhoneNumber(String key);

    @Query(value =  " SELECT *  "
            +  " FROM paystub  "
            +  " WHERE date_of_paystub = :key "
            , nativeQuery = true)
    List<Paystub> findPaystubByDate(Date key);
}
