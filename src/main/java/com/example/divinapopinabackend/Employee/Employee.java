package com.example.divinapopinabackend.Employee;

import com.example.divinapopinabackend.Authenticator.Authentification;
import com.example.divinapopinabackend.EmployeeTerm.EmployeeTerm;
import com.example.divinapopinabackend.Payroll.Payroll;
import com.example.divinapopinabackend.Shift.Shift;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employee")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name",nullable = false)
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name",nullable = false)
    private String lastName;
    @Column(nullable = false)
    private String job;
    @Column(nullable = false)
    private String email;
    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;
    @Column(name = "date_of_birth",nullable = false)
    private Date dateOfBirth;
    @OneToOne(mappedBy = "employee",cascade = CascadeType.ALL)
    private EmployeeTerm employeeTerm;
    @OneToOne(mappedBy = "employee",cascade = CascadeType.ALL)
    private Authentification authentification;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<Shift> shifts;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private Set<Payroll> payrolls;

    public Employee(String firstName, String middleName, String lastName, String job, String email, String phoneNumber, Date dateOfBirth) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.job = job;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
    }
}
