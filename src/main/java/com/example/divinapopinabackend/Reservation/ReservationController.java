package com.example.divinapopinabackend.Reservation;

import com.example.divinapopinabackend.Food.Food;
import com.example.divinapopinabackend.Food.FoodServices;
import com.example.divinapopinabackend.Order.Order;
import com.example.divinapopinabackend.Order.OrderServices;
import com.example.divinapopinabackend.Sercurity.Util.JasyptConfig;
import com.example.divinapopinabackend.Transaction.Transaction;
import com.example.divinapopinabackend.Transaction.TransactionServices;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/reservation")
@CrossOrigin
public class ReservationController implements Serializable {
    private static final long serialVersionUID = 1L;
    @Autowired ReservationServices reservationServices;
    @Autowired
    JasyptConfig jasyptConfig;
@Autowired
    OrderServices orderServices;
    @Autowired
    TransactionServices transactionServices;
    @Autowired
    FoodServices foodServices;


    public ReservationController(ReservationServices reservationServices, JasyptConfig jasyptConfig) {
        this.reservationServices = reservationServices;
        this.jasyptConfig = jasyptConfig;
    }

    @GetMapping
    public List<Reservation> getreservations() {
        return reservationServices.getReservations();
    }

    @GetMapping("/{id}")
    public Reservation getreservation(@PathVariable Long id) throws MessagingException, IOException {
        Reservation reservation=reservationServices.getreservationById(id);
        return reservation;
    }

    @GetMapping("/byDate/{date}")
    public List<Reservation> getreservationByName(@PathVariable Date date) throws MessagingException, IOException {
        Date thisDate=date;
        List<Reservation> reservations=reservationServices.getReservationByDate(date);
        return reservations;
    }

    @GetMapping("/names")
    public List<String> getreservationNames() throws MessagingException, IOException {
        List<String> reservations=reservationServices.getReservationNames();
        return reservations;
    }
    @PostMapping
    public ResponseEntity createreservation(@RequestBody Map<Object, Object> payLoad ) throws URISyntaxException, ParseException {
String date=(String)payLoad.get("dateOfEvent");


        DateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
       java.util.Date utilDate=simpleDateFormat.parse(date);
       Date ofDate=new java.sql.Date(utilDate.getTime());
        Reservation reservation=new Reservation(
                (String)payLoad.get("note"),
                ofDate,
                (String)payLoad.get("name"));
        reservationServices.saveReservation(reservation);

        return ResponseEntity.ok().build();
    }
    @PostMapping("/exist")
    public ResponseEntity createreservationExist(@RequestBody Map<Object, Object> payLoad ) throws URISyntaxException, ParseException {
        long id=(Integer)payLoad.get("id");
        Reservation reservation=reservationServices.getreservationById(id);

String creditCardNumber="";
creditCardNumber=(String)payLoad.get("creditCardNumber");

        reservation.setNote((String)payLoad.get("note"));

        DateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate=simpleDateFormat.parse((String)payLoad.get("dateOfEvent"));
        Date ofDate=new java.sql.Date(utilDate.getTime());
        reservation.setName((String)payLoad.get("name"));
        reservation.setDateOfEvent(ofDate);
       ArrayList<Order> orders=new ArrayList<>(reservation.getOrders());
        double total=0;
        double tax=0.15;
        for(int i=0;i<orders.size();i++){
                total+=foodServices.getFoodByName(orders.get(i).getFood()).getPrice()*orders.get(i).getQuantity();
        }

            total=total+(total*tax);
            total=(total * 100) / 100.0;
        Transaction transaction=new Transaction(creditCardNumber,total,reservation);
        try{
        transactionServices.removetransaction(reservation.getTransaction().getId());}
        catch(Exception e){

        }
        reservation.setTransaction(transaction);

        reservationServices.saveReservation(reservation);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteReservation(@PathVariable Long id) {
        reservationServices.removeReservation(id);
        return ResponseEntity.ok().build();
    }
}

