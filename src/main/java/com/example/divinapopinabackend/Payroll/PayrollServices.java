package com.example.divinapopinabackend.Payroll;


import com.example.divinapopinabackend.Shift.Shift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class PayrollServices { @Autowired
PayrollRepository payrollRepository;

    public Payroll getpayrollById(long id){
        return payrollRepository.getReferenceById(id);
    }

    public List<Payroll> getPayrollByDate(Date date){
        return payrollRepository.findPayrollByDate(date);
    }

    public void savepayroll(Payroll payroll){
        payrollRepository.save(payroll);
    }
    public void removepayroll(long id){
        payrollRepository.deleteById(id);
    }
    public List<Payroll> getpayrolls(){
        return payrollRepository.findAll();
    }
}
