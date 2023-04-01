package com.example.divinapopinabackend.Util;

import com.example.divinapopinabackend.Authenticator.AuthentificationServices;
import com.example.divinapopinabackend.Category.FoodCategoryServices;
import com.example.divinapopinabackend.Employee.EmployeeServices;
import com.example.divinapopinabackend.EmployeeTerm.EmployeeTermServices;
import com.example.divinapopinabackend.Food.FoodServices;
import com.example.divinapopinabackend.Order.OrderServices;
import com.example.divinapopinabackend.Payroll.PayrollServices;
import com.example.divinapopinabackend.Paystub.PaystubServices;
import com.example.divinapopinabackend.Reservation.ReservationServices;
import com.example.divinapopinabackend.Sercurity.Util.JasyptConfig;
import com.example.divinapopinabackend.Shift.ShiftServices;
import com.example.divinapopinabackend.Transaction.TransactionServices;
import lombok.Getter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Getter
@Service
public class BeanCollection {
    @Bean
    public AuthentificationServices authentificationServices() {
        return new AuthentificationServices();
    }
    @Bean
    public EmployeeServices employeeServices() {
        return new EmployeeServices();
    }
    @Bean
    public JasyptConfig jasyptConfig() {
        return new JasyptConfig();
    }
    @Bean
    public FoodServices foodServices() {
        return new FoodServices();
    }
    @Bean
    public OrderServices orderServices() {
        return new OrderServices();
    }
    @Bean
    public PayrollServices payrollServices() {
        return new PayrollServices();
    }
    @Bean
    public PaystubServices paystubServices() {
        return new PaystubServices();
    }
    @Bean
    public TransactionServices transactionServices() {
        return new TransactionServices();
    }
    @Bean
    public ShiftServices shiftServices() {
        return new ShiftServices();
    }
    @Bean
    public ReservationServices reservationServices() {
        return new ReservationServices();
    }
    @Bean
    public EmployeeTermServices employeeTermServices() {
        return new EmployeeTermServices();
    }
    @Bean
    public FoodCategoryServices foodCategoryServices() {
        return new FoodCategoryServices();
    }

}
