package com.example.divinapopinabackend.Authenticator;


import com.example.divinapopinabackend.Employee.Employee;
import com.example.divinapopinabackend.Employee.EmployeeServices;
import com.example.divinapopinabackend.Sercurity.Util.JasyptConfig;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/authentification")
@CrossOrigin
public class AuthentificationController implements Serializable {
    private static final long serialVersionUID = 1L;
    @Autowired
    AuthentificationServices authentificationServices;
    @Autowired
   JasyptConfig jasyptConfig;
    @Autowired
   EmployeeServices employeeServices;



    @GetMapping
    public List<Authentification> getauthentifications() {
        List<Authentification> authentifications= authentificationServices.getauthentifications();
        for(int i=0;i<authentifications.size();i++){
            Authentification authentification=authentifications.get(i);
            authentification.setPassword(jasyptConfig.encryptor().decrypt(authentification.getPassword()));
            authentifications.set(i,authentification);
        }
        return authentifications;
    }

    @GetMapping("/{id}")
    public Authentification getauthentification(@PathVariable Long id) throws MessagingException, IOException {
        Authentification authentification=authentificationServices.getauthentificationById(id);
        authentification.setPassword(jasyptConfig.encryptor().decrypt(authentification.getPassword()));
        return authentification;
    }
    @GetMapping("/{email}/{password}")
    public Employee getEmployee(@RequestParam  String email,@RequestParam  String password) throws MessagingException, IOException {
        Employee employee=employeeServices.findEmployeeByEmail(jasyptConfig.encryptor().encrypt(email));
        Authentification authentification=authentificationServices.getauthentificationByName(password);
        if(employee.getAuthentification().getPassword() == authentification.getPassword()){
            return employee;
        }

        return null;
    }
    @PostMapping
    public ResponseEntity createauthentification(@RequestParam  String email,@RequestParam  String password) throws URISyntaxException {

       Employee employee=employeeServices.findEmployeeByEmail(jasyptConfig.encryptor().encrypt(email));
        Authentification authentification=new Authentification(jasyptConfig.encryptor().encrypt(password),employee);
        authentificationServices.saveauthentification(authentification);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/exist")
    public ResponseEntity createauthentification(@RequestParam  long id,@RequestParam  String password, @RequestParam  long employeeId) throws URISyntaxException {
        Authentification authentification=authentificationServices.getauthentificationById(id);
        Employee employee=employeeServices.getemployeeById(employeeId);
        authentification.setPassword(password);
        authentification.setEmployee(employee);
        authentificationServices.saveauthentification(authentification);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteauthentification(@PathVariable Long id) {
        authentificationServices.removeauthentification(id);
        return ResponseEntity.ok().build();
    }
}