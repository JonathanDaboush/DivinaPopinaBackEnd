package com.example.divinapopinabackend.EmployeeTerm;

import com.example.divinapopinabackend.EmployeeTerm.EmployeeTerm;
import com.example.divinapopinabackend.EmployeeTerm.EmployeeTermRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class EmployeeTermServices { @Autowired
EmployeeTermRepository employeeTermRepository;

    public EmployeeTerm getemployeeTermById(long id){
        return employeeTermRepository.getReferenceById(id);
    }
    public void saveemployeeTerm(EmployeeTerm employeeTerm){
        employeeTermRepository.save(employeeTerm);
    }
    public void removeemployeeTerm(long id){
        employeeTermRepository.deleteById(id);
    }
    public List<EmployeeTerm> getemployeeTerms(){
        return employeeTermRepository.findAll();
    }
}