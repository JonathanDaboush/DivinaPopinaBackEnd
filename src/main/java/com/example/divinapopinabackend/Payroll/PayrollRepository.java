package com.example.divinapopinabackend.Payroll;

import com.example.divinapopinabackend.Order.Order;
import com.example.divinapopinabackend.Reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PayrollRepository extends JpaRepository<Payroll, Long> {
    @Query(value =  " SELECT *  "
            +  " FROM payroll  "
            +  " WHERE date_of_pay = :key "
            , nativeQuery = true)
    List<Payroll> findPayrollByDate(Date key);
}
