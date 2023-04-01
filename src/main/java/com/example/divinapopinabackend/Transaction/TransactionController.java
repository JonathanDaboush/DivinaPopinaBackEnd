package com.example.divinapopinabackend.Transaction;


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
import java.util.*;

@RestController
@RequestMapping("/transaction")
@CrossOrigin
public class TransactionController implements Serializable {
    private static final long serialVersionUID = 1L;
    @Autowired
    TransactionServices transactionServices;
    @Autowired
    JasyptConfig jasyptConfig;

    @Autowired
    ReservationServices reservationServices;


    @GetMapping
    public List<Transaction> gettransactions() {

        List<Transaction> transactions=transactionServices.gettransactions();

        for(int i=0;i< transactions.size();i++){
            Transaction transaction=transactions.get(i);
            transaction.setCreditCardNumber(jasyptConfig.encryptor().decrypt(transaction.getCreditCardNumber()));
            transactions.set(i,transaction);
        }

        return transactions;
    }

    @GetMapping("/{id}")
    public Transaction gettransaction(@PathVariable Long id) throws MessagingException, IOException {
        Transaction transaction=transactionServices.gettransactionById(id);
        transaction.setCreditCardNumber(jasyptConfig.encryptor().decrypt(transaction.getCreditCardNumber()));
        return transaction;
    }
    @PostMapping
    public ResponseEntity createtransaction(@RequestBody Map<Object, Object> payLoad) throws URISyntaxException {
        try{
            Transaction transaction=new Transaction();
            Reservation reservation=reservationServices.getreservationById((Integer)payLoad.get("reservationId"));
            double cost=0;
            

            if(payLoad.get("cost") instanceof String){
                cost=Double.parseDouble((String)payLoad.get("cost"));
            }

            else if(payLoad.get("cost") instanceof Integer){
                int number=(Integer)payLoad.get("cost");

                cost=number;
            }
            else{
                cost=(Double)payLoad.get("cost");
            }

            transaction.setCreditCardNumber(jasyptConfig.encryptor().encrypt((String)payLoad.get("creditCardNumber")));

            transaction.setAmount(cost);
           transaction.setReservation(reservation);
            transactionServices.savetransaction(transaction);}
        catch(Exception e){

        }
        return ResponseEntity.ok().build();
    }
    @PostMapping("/exist")
    public ResponseEntity createtransactionByExistence(@RequestBody Map<Object, Object> payLoad ) throws URISyntaxException {

        try{
            Transaction transaction=transactionServices.gettransactionById((Integer)payLoad.get("id"));
            Reservation reservation=reservationServices.getreservationById((Integer)payLoad.get("reservationId"));
            double cost=0;
            if(payLoad.get("cost") instanceof String){
                cost=Double.parseDouble((String)payLoad.get("cost"));
            }
            else{

                cost=Double.parseDouble((String)payLoad.get("cost"));
            }
            transaction.setCreditCardNumber(jasyptConfig.encryptor().encrypt((String)payLoad.get("creditCardNumber")));

            transaction.setAmount(cost);
            transaction.setReservation(reservation);
            transactionServices.savetransaction(transaction);}
        catch(Exception e){

        }
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletefood(@PathVariable Long id) {
        transactionServices.removetransaction(id);
        return ResponseEntity.ok().build();
    }
}

