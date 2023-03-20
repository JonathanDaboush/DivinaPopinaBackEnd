package com.example.divinapopinabackend.Employee;


import com.example.divinapopinabackend.Paystub.Paystub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Component
public class EmployeeServices { @Autowired
EmployeeRepository employeeRepository;

    public Employee getemployeeById(long id){
        return employeeRepository.getReferenceById(id);
    }

    public Employee findEmployeeByEmail(String key){
        return employeeRepository.findEmployeeByEmail(key).get(0);
    }
    public void saveemployee(Employee employee){
        employeeRepository.save(employee);
    }
    public void removeemployee(long id){
        employeeRepository.deleteById(id);
    }
    public List<Employee> getemployees(){
        return employeeRepository.findAll();
    }
}
