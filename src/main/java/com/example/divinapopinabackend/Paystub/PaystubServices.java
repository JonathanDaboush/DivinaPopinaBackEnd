package com.example.divinapopinabackend.Paystub;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class PaystubServices { @Autowired
PaystubRepository paystubRepository;

    public Paystub getpaystubById(long id){
        return paystubRepository.getReferenceById(id);
    }

    List<Paystub> findpaystubByDate(Date key){
        return paystubRepository.findPaystubByDate(key);
    }

    List<Paystub> findpaystubByEmail(String key){
        return paystubRepository.findPaystubByEmail(key);
    }
    public void savepaystub(Paystub paystub){
        paystubRepository.save(paystub);
    }
    public void removepaystub(long id){
        paystubRepository.deleteById(id);
    }
    public List<Paystub> getpaystubs(){
        return paystubRepository.findAll();
    }
}
