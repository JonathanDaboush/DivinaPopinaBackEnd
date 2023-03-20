package com.example.divinapopinabackend.Employee;

import com.example.divinapopinabackend.Order.Order;
import com.example.divinapopinabackend.Reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value =  " SELECT *  "
            +  " FROM employee  "
            +  " WHERE email = :key "
            , nativeQuery = true)
    List<Employee> findEmployeeByEmail(String key);
}