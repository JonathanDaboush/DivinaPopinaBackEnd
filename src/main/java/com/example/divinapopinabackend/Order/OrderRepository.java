package com.example.divinapopinabackend.Order;

import com.example.divinapopinabackend.Food.Food;
import com.example.divinapopinabackend.Reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface OrderRepository  extends JpaRepository<Order, Long> {

}
