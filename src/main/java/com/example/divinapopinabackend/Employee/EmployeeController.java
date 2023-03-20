package com.example.divinapopinabackend.Employee;

import com.example.divinapopinabackend.Authenticator.Authentification;
import com.example.divinapopinabackend.Authenticator.AuthentificationServices;
import com.example.divinapopinabackend.EmployeeTerm.EmployeeTerm;
import com.example.divinapopinabackend.EmployeeTerm.EmployeeTermServices;
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
@RequestMapping("/employee")
@CrossOrigin
public class EmployeeController implements Serializable {
    private static final long serialVersionUID = 1L;
    @Autowired EmployeeServices employeeServices;
    @Autowired JasyptConfig jasyptConfig;
    @Autowired  AuthentificationServices authentificationServices;
    @Autowired
    EmployeeTermServices employeeTermServices;

    public EmployeeController(EmployeeServices employeeServices,JasyptConfig jasyptConfig,AuthentificationServices authentificationServices,EmployeeTermServices employeeTermServices) {
        this.employeeServices = employeeServices;
        this.jasyptConfig = jasyptConfig;
        this.authentificationServices = authentificationServices;
        this.employeeTermServices = employeeTermServices;
    }

    @GetMapping
    public List<Employee> getEmployees() {
        List<Employee> employees= employeeServices.getemployees();
        for(int i=0;i<employees.size();i++){
            Employee employee=employees.get(i);
            Authentification authentification=employee.getAuthentification();
            EmployeeTerm employeeTerm=employee.getEmployeeTerm();

            employee.setEmail(jasyptConfig.encryptor().decrypt(employee.getEmail()));
            employee.setPhoneNumber(jasyptConfig.encryptor().decrypt(employee.getPhoneNumber()));

            authentification.setPassword(jasyptConfig.encryptor().decrypt(authentification.getPassword()));

            employeeTerm.setCreditCardNumber(jasyptConfig.encryptor().decrypt(employeeTerm.getCreditCardNumber()));
            employeeTerm.setCvv(jasyptConfig.encryptor().decrypt(employeeTerm.getCvv()));

            employee.setAuthentification(authentification);
            employee.setEmployeeTerm(employeeTerm);
        }
        return employees;
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable Long id) throws MessagingException, IOException {
        Employee employee=employeeServices.getemployeeById(id);

        Authentification authentification=employee.getAuthentification();
        EmployeeTerm employeeTerm=employee.getEmployeeTerm();

        employee.setEmail(jasyptConfig.encryptor().decrypt(employee.getEmail()));
        employee.setPhoneNumber(jasyptConfig.encryptor().decrypt(employee.getPhoneNumber()));

        authentification.setPassword(jasyptConfig.encryptor().decrypt(authentification.getPassword()));

        employeeTerm.setCreditCardNumber(jasyptConfig.encryptor().decrypt(employeeTerm.getCreditCardNumber()));
        employeeTerm.setCvv(jasyptConfig.encryptor().decrypt(employeeTerm.getCvv()));

        employee.setAuthentification(authentification);
        employee.setEmployeeTerm(employeeTerm);
        return employee;
    }
    @GetMapping("/byEmail/{email}")
    public Employee getEmployeeByEmail(@PathVariable String email) throws MessagingException, IOException {

        Employee employee=employeeServices.findEmployeeByEmail(jasyptConfig.encryptor().encrypt(email));

        Authentification authentification=employee.getAuthentification();
        EmployeeTerm employeeTerm=employee.getEmployeeTerm();

        employee.setEmail(jasyptConfig.encryptor().decrypt(employee.getEmail()));
        employee.setPhoneNumber(jasyptConfig.encryptor().decrypt(employee.getPhoneNumber()));

        authentification.setPassword(jasyptConfig.encryptor().decrypt(authentification.getPassword()));

        employeeTerm.setCreditCardNumber(jasyptConfig.encryptor().decrypt(employeeTerm.getCreditCardNumber()));
        employeeTerm.setCvv(jasyptConfig.encryptor().decrypt(employeeTerm.getCvv()));

        employee.setAuthentification(authentification);
        employee.setEmployeeTerm(employeeTerm);
        return employee;
    }
    @PostMapping
    public ResponseEntity createEmployee(@RequestParam  String firstName, @RequestParam   String middleName, @RequestParam   String lastName,
                                                 @RequestParam  String job, @RequestParam   String email, @RequestParam   String phoneNumber,
                                                 @RequestParam Date dateOfBirth,@RequestParam String creditCardNumber,@RequestParam String cvv,@RequestParam float rate,
                                                 @RequestParam  Date dateOfEmployment,@RequestParam String password) throws URISyntaxException {
        Employee employee=new  Employee(firstName,middleName, lastName,  job, jasyptConfig.encryptor().encrypt(email), jasyptConfig.encryptor().encrypt(phoneNumber), dateOfBirth);
        Authentification authentification=new Authentification(jasyptConfig.encryptor().encrypt(password),employee);
        EmployeeTerm employeeTerm=new EmployeeTerm(jasyptConfig.encryptor().encrypt(creditCardNumber),jasyptConfig.encryptor().encrypt(cvv),rate,dateOfEmployment,employee);
        employee.setEmployeeTerm(employeeTerm);
        employee.setAuthentification(authentification);
        employeeServices.saveemployee(employee);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/exist")
    public ResponseEntity createauthentification(@RequestParam  long id,@RequestParam  String firstName, @RequestParam   String middleName, @RequestParam   String lastName,
                                                 @RequestParam  String job, @RequestParam   String email, @RequestParam   String phoneNumber,
                                                 @RequestParam Date dateOfBirth,@RequestParam String creditCardNumber,@RequestParam String cvv,@RequestParam float rate,
                                                 @RequestParam  Date dateOfEmployment,@RequestParam String password) throws URISyntaxException {

        Employee employee=employeeServices.getemployeeById(id);
        EmployeeTerm employeeTerm=employee.getEmployeeTerm();
        Authentification authentification=employee.getAuthentification();

        employee.setFirstName(firstName);
        employee.setMiddleName(middleName);
        employee.setLastName(lastName);
        employee.setJob(job);
        employee.setEmail(jasyptConfig.encryptor().encrypt(email));
        employee.setPhoneNumber(jasyptConfig.encryptor().encrypt(phoneNumber));
        employee.setDateOfBirth(dateOfBirth);

        employeeTerm.setCreditCardNumber(jasyptConfig.encryptor().encrypt(creditCardNumber));
        employeeTerm.setCvv(jasyptConfig.encryptor().encrypt(cvv));
        employeeTerm.setRate(rate);
        employeeTerm.setDateOfEmployment(dateOfEmployment);

        authentification.setPassword(jasyptConfig.encryptor().encrypt(password));

        employee.setEmployeeTerm(employeeTerm);
        employee.setAuthentification(authentification);
        employeeServices.saveemployee(employee);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteauthentification(@PathVariable Long id) {
        employeeServices.removeemployee(id);
        return ResponseEntity.ok().build();
    }
}
