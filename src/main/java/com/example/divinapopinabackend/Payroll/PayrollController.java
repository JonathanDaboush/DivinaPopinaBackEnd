package com.example.divinapopinabackend.Payroll;


import com.example.divinapopinabackend.Employee.Employee;
import com.example.divinapopinabackend.Employee.EmployeeServices;
import com.example.divinapopinabackend.Sercurity.Util.JasyptConfig;
import com.example.divinapopinabackend.Payroll.Payroll;
import com.example.divinapopinabackend.Payroll.PayrollServices;
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
@RequestMapping("/payroll")
@CrossOrigin
public class PayrollController implements Serializable {
    private static final long serialVersionUID = 1L;
    @Autowired PayrollServices payrollServices;
    @Autowired JasyptConfig jasyptConfig;


    @Autowired
    EmployeeServices employeeServices;
    public PayrollController(PayrollServices payrollServices, JasyptConfig jasyptConfig,EmployeeServices employeeServices) {
        this.payrollServices = payrollServices;
        this.jasyptConfig = jasyptConfig;
        this.employeeServices = employeeServices;
    }

    @GetMapping
    public List<Payroll> getpayrolls() {


        List<Payroll>  payrolls=payrollServices.getpayrolls();

        return  payrolls;
    }

    @GetMapping("/{id}")
    public Payroll getpayroll(@PathVariable Long id) throws MessagingException, IOException {
        Payroll payroll=payrollServices.getpayrollById(id);
        return payroll;
    }



    @GetMapping("/byDate/{date}")
    public List<Payroll> getpayrollByDate(@PathVariable Date date) throws MessagingException, IOException {
        List<Payroll>  payrolls=payrollServices.getPayrollByDate(date);

        return  payrolls;
    }
    @PostMapping
    public ResponseEntity createpayroll(@RequestParam  Date dateOfPay,@RequestParam   float amount,@RequestParam   String note,@RequestParam  long employeeId) throws URISyntaxException {

       Employee employee=employeeServices.getemployeeById(employeeId);
        Payroll payroll=new Payroll(dateOfPay,amount,note,employee);

        payrollServices.savepayroll(payroll);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/exist")
    public ResponseEntity createpayroll(@RequestParam  long id,@RequestParam  Date dateOfPay,@RequestParam   float amount,@RequestParam   String note,@RequestParam  long employeeId) throws URISyntaxException {
        Employee employee=employeeServices.getemployeeById(employeeId);
        Payroll payroll=payrollServices.getpayrollById(id);
        payroll.setAmount(amount);
        payroll.setDateOfPay(dateOfPay);
        payroll.setNote(note);
        payroll.setEmployee(employee);

        payrollServices.savepayroll(payroll);
        return ResponseEntity.ok().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deletepayroll(@PathVariable Long id) {
        payrollServices.removepayroll(id);
        return ResponseEntity.ok().build();
    }
}
