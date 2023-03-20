package com.example.divinapopinabackend.EmployeeTerm;

import com.example.divinapopinabackend.Employee.Employee;
import com.example.divinapopinabackend.Reservation.Reservation;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee_term")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class EmployeeTerm {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "credit_card_number",nullable = false)
    private String creditCardNumber;
    @Column(nullable = false)
    private String cvv;
    @Column(nullable = false)
    private float rate;
    @Column(name = "date_of_Employment",nullable = false)
    private Date dateOfEmployment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    public EmployeeTerm(String creditCardNumber, String cvv, float rate, Date dateOfEmployment, Employee employee) {
        this.creditCardNumber = creditCardNumber;
        this.cvv = cvv;
        this.rate = rate;
        this.dateOfEmployment = dateOfEmployment;
        this.employee = employee;
    }
}
