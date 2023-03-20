package com.example.divinapopinabackend.Transaction;

import com.example.divinapopinabackend.Reservation.Reservation;
import com.example.divinapopinabackend.Transaction.Transaction;
import com.example.divinapopinabackend.Transaction.TransactionServices;
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
@RequestMapping("/transaction")
@CrossOrigin
public class TransactionController implements Serializable {
    private static final long serialVersionUID = 1L;
    @Autowired
    TransactionServices transactionServices;
    @Autowired JasyptConfig jasyptConfig;




    @GetMapping
    public List<Transaction> gettransactions() {


        List<Transaction>  transactions=transactionServices.gettransactions();
        for(int i=0;i< transactions.size();i++){
            Transaction transaction=transactions.get(i);

            transaction.setCreditCardNumber(jasyptConfig.encryptor().decrypt(transaction.getCreditCardNumber()));

            transactions.set(i,transaction);
        }
        return  transactions;
    }

    @GetMapping("/{id}")
    public Transaction gettransaction(@PathVariable Long id) throws MessagingException, IOException {
        Transaction transaction=transactionServices.gettransactionById(id);
        transaction.setCreditCardNumber(jasyptConfig.encryptor().decrypt(transaction.getCreditCardNumber()));
        return transaction;
    }
    @GetMapping("/byName/{name}")
    public Transaction gettransactionByName(@PathVariable String name) throws MessagingException, IOException {
        Transaction transaction=transactionServices.gettransactionByNumber(name);
        transaction.setCreditCardNumber(jasyptConfig.encryptor().decrypt(transaction.getCreditCardNumber()));
        return transaction;
    }
    @PostMapping
    public ResponseEntity createtransaction(@RequestParam String creditCardNumber,@RequestParam  float amount,@RequestParam  Reservation reservation) throws URISyntaxException {
        Transaction transaction=new Transaction(creditCardNumber,amount,reservation);
        transactionServices.savetransaction(transaction);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/exist")
    public ResponseEntity createtransaction(@RequestParam  long id,@RequestParam String creditCardNumber, @RequestParam float amount,@RequestParam  Reservation reservation) throws URISyntaxException {
        Transaction transaction=transactionServices.gettransactionById(id);
        transaction.setCreditCardNumber(jasyptConfig.encryptor().encrypt(creditCardNumber));
        transaction.setAmount(amount);
        transaction.setReservation(reservation);
        transactionServices.savetransaction(transaction);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletetransaction(@PathVariable Long id) {
        transactionServices.removetransaction(id);
        return ResponseEntity.ok().build();
    }
}
