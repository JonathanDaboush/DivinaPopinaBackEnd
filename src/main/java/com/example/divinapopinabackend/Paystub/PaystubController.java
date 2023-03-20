package com.example.divinapopinabackend.Paystub;

import com.example.divinapopinabackend.Reservation.Reservation;
import com.example.divinapopinabackend.Sercurity.Util.JasyptConfig;
import com.example.divinapopinabackend.Paystub.Paystub;
import com.example.divinapopinabackend.Paystub.PaystubServices;
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
@RequestMapping("/paystub")
@CrossOrigin
public class PaystubController implements Serializable {
    private static final long serialVersionUID = 1L;
    @Autowired
     PaystubServices paystubServices;
    @Autowired JasyptConfig jasyptConfig;



    public PaystubController(PaystubServices paystubServices, JasyptConfig jasyptConfig) {
        this.paystubServices = paystubServices;
        this.jasyptConfig = jasyptConfig;
    }

    @GetMapping
    public List<Paystub> getpaystubs() {


        List<Paystub>  paystubs=paystubServices.getpaystubs();
        for(int i=0;i< paystubs.size();i++){
            Paystub paystub=paystubs.get(i);

            paystub.setPhoneNumber(jasyptConfig.encryptor().decrypt(paystub.getPhoneNumber()));
            paystub.setEmail(jasyptConfig.encryptor().decrypt(paystub.getEmail()));

            paystubs.set(i,paystub);
        }
        return  paystubs;
    }

    @GetMapping("/{id}")
    public Paystub getpaystub(@PathVariable Long id) throws MessagingException, IOException {
        Paystub paystub=paystubServices.getpaystubById(id);
        paystub.setPhoneNumber(jasyptConfig.encryptor().decrypt(paystub.getPhoneNumber()));
        paystub.setEmail(jasyptConfig.encryptor().decrypt(paystub.getEmail()));
        return paystub;
    }


    @GetMapping("/byEmail/{email}")
    public Paystub getpaystubByEmail(@PathVariable String email) throws MessagingException, IOException {
        Paystub paystub=paystubServices.findpaystubByEmail(jasyptConfig.encryptor().decrypt(email)).get(0);
        paystub.setPhoneNumber(jasyptConfig.encryptor().decrypt(paystub.getPhoneNumber()));
        paystub.setEmail(jasyptConfig.encryptor().decrypt(paystub.getEmail()));
        return paystub;
    }
    @GetMapping("/byDate/{date}")
    public List<Paystub> getpaystubByDate(@PathVariable Date date) throws MessagingException, IOException {
        List<Paystub>  paystubs=paystubServices.findpaystubByDate(date);
        for(int i=0;i< paystubs.size();i++){
            Paystub paystub=paystubs.get(i);

            paystub.setPhoneNumber(jasyptConfig.encryptor().decrypt(paystub.getPhoneNumber()));
            paystub.setEmail(jasyptConfig.encryptor().decrypt(paystub.getEmail()));

            paystubs.set(i,paystub);
        }
        return  paystubs;
    }
    @PostMapping
    public ResponseEntity createpaystub(@RequestParam  String firstName,@RequestParam   String middleName,@RequestParam   String lastName,@RequestParam   float sum,@RequestParam   Date dateOfPaystub,@RequestParam   String phoneNumber,@RequestParam   String email) throws URISyntaxException {
        Paystub paystub=new Paystub();
        paystub.setFirstName(firstName);
        paystub.setMiddleName(middleName);
        paystub.setLastName(lastName);
        paystub.setDateOfPaystub(dateOfPaystub);
        paystub.setSum(sum);
        paystub.setPhoneNumber(jasyptConfig.encryptor().encrypt(phoneNumber));
        paystub.setEmail(jasyptConfig.encryptor().encrypt(email));
        paystubServices.savepaystub(paystub);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/exist")
    public ResponseEntity createpaystub(@RequestParam  long id,@RequestParam  String firstName,@RequestParam   String middleName,@RequestParam   String lastName,@RequestParam   float sum,@RequestParam   Date dateOfPaystub,@RequestParam   String phoneNumber,@RequestParam   String email) throws URISyntaxException {
        Paystub paystub=paystubServices.getpaystubById(id);
        paystub.setFirstName(firstName);
        paystub.setMiddleName(middleName);
        paystub.setLastName(lastName);
        paystub.setDateOfPaystub(dateOfPaystub);
        paystub.setSum(sum);
        paystub.setPhoneNumber(jasyptConfig.encryptor().encrypt(phoneNumber));
        paystub.setEmail(jasyptConfig.encryptor().encrypt(email));
        paystubServices.savepaystub(paystub);
        paystubServices.savepaystub(paystub);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletepaystub(@PathVariable Long id) {
        paystubServices.removepaystub(id);
        return ResponseEntity.ok().build();
    }
}
