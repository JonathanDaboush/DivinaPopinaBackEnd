package com.example.divinapopinabackend.Order;

import com.example.divinapopinabackend.Order.Order;
import com.example.divinapopinabackend.Order.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class OrderServices {
    @Autowired
    OrderRepository orderRepository;

    public Order getorderById(long id){
        return orderRepository.getReferenceById(id);
    }


    public void saveorder(Order order){
        orderRepository.save(order);
    }
    public void removeorder(long id){
        orderRepository.deleteById(id);
    }
    public List<Order> getorders(){
        return orderRepository.findAll();
    }
}
