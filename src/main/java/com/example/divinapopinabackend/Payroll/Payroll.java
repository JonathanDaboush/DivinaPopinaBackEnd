package com.example.divinapopinabackend.Payroll;

import com.example.divinapopinabackend.Employee.Employee;
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
@Table(name = "payroll")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Payroll {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "date_of_pay",nullable = false)
    private Date dateOfPay;
    @Column(name = "amount",nullable = false)
    private float amount;
    @Column(name = "note",nullable = false)
    private String note;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    public Payroll(Date dateOfPay, float amount, String note, Employee employee) {
        this.dateOfPay = dateOfPay;
        this.amount = amount;
        this.note = note;
        this.employee = employee;
    }
}
