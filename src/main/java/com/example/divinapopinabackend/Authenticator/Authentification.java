package com.example.divinapopinabackend.Authenticator;

import com.example.divinapopinabackend.Employee.Employee;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "authentification")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Authentification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String password;


    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee;

    public Authentification(String password, Employee employee) {
        this.password = password;
        this.employee = employee;
    }
}
