package com.example.divinapopinabackend.Transaction;

import com.example.divinapopinabackend.Transaction.Transaction;
import com.example.divinapopinabackend.Transaction.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TransactionServices {
    @Autowired
    TransactionRepository transactionRepository;

    public Transaction gettransactionById(long id){
        return transactionRepository.getReferenceById(id);
    }


    public void savetransaction(Transaction transaction){
        transactionRepository.save(transaction);
    }
    public void removetransaction(long id){
        transactionRepository.deleteById(id);
    }
    public List<Transaction> gettransactions(){
        return transactionRepository.findAll();
    }
}
