package com.example.divinapopinabackend.Order;

import com.example.divinapopinabackend.Order.Order;
import com.example.divinapopinabackend.Order.OrderServices;
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

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController implements Serializable {
    private static final long serialVersionUID = 1L;
    @Autowired OrderServices orderServices;
    @Autowired
    JasyptConfig jasyptConfig;



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
    public ResponseEntity createorder(@RequestParam  int quantity, @RequestParam  String note) throws URISyntaxException {
        Order order=new Order(quantity,note);
        orderServices.saveorder(order);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/exist")
    public ResponseEntity createorder(@RequestParam  long id,@RequestParam  int quantity,@RequestParam  String note) throws URISyntaxException {
        Order order=orderServices.getorderById(id);
        order.setQuantity(quantity);
        order.setNote(note);
        orderServices.saveorder(order);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteorder(@PathVariable Long id) {
        orderServices.removeorder(id);
        return ResponseEntity.ok().build();
    }
}

