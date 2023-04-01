package com.example.divinapopinabackend.Order;

import com.example.divinapopinabackend.Food.Food;
import com.example.divinapopinabackend.Food.FoodServices;
import com.example.divinapopinabackend.Order.Order;
import com.example.divinapopinabackend.Order.OrderServices;
import com.example.divinapopinabackend.Reservation.Reservation;
import com.example.divinapopinabackend.Reservation.ReservationServices;
import com.example.divinapopinabackend.Sercurity.Util.JasyptConfig;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController implements Serializable {
    private static final long serialVersionUID = 1L;
    @Autowired OrderServices orderServices;
    @Autowired
    JasyptConfig jasyptConfig;
    @Autowired
    ReservationServices reservationServices;

@Autowired
    FoodServices foodServices;

    public OrderController(OrderServices orderServices, JasyptConfig jasyptConfig) {
        this.orderServices = orderServices;
        this.jasyptConfig = jasyptConfig;
    }

    @GetMapping
    public List<Order> getorders() {
        return orderServices.getorders();
    }

    @GetMapping("/{id}")
    public Order getorder(@PathVariable Long id) throws MessagingException, IOException {
        Order order=orderServices.getorderById(id);
        return order;
    }

    @PostMapping
    public ResponseEntity createorder(@RequestBody Map<Object, Object> payLoad ) throws URISyntaxException {

        long id=(Integer)payLoad.get("resId");
        Reservation reservation=reservationServices.getreservationById(id);

        int qty=(Integer)payLoad.get("quantity");

        Order order=new Order(qty,
                (String)payLoad.get("note"),
                (String)payLoad.get("name"),
                reservation);
        orderServices.saveorder(order);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/exist")
    public ResponseEntity createorderExist(@RequestBody Map<Object, Object> payLoad ) throws URISyntaxException {
long idp=(Integer)payLoad.get("id");
        Order order=orderServices.getorderById(idp);

        long id=(Integer)payLoad.get("resId");
        Reservation reservation=reservationServices.getreservationById(id);
        order.setFood((String)payLoad.get("name"));
        int qty=(Integer)payLoad.get("quantity");

        order.setQuantity(qty);
        order.setNote((String)payLoad.get("note"));
        order.setReservation(reservation);
        orderServices.saveorder(order);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteorder(@PathVariable Long id) {
        orderServices.removeorder(id);
        return ResponseEntity.ok().build();
    }
}

