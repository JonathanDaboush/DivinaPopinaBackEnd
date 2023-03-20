package com.example.divinapopinabackend.Paystub;

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
@Table(name = "paystub")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Paystub {
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
    private float sum;
    @Column(name = "date_of_paystub",nullable = false)
    private Date dateOfPaystub;
    @Column(name = "phone_number",nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String email;

    public Paystub(String firstName, String middleName, String lastName, float sum, Date dateOfPaystub, String phoneNumber, String email) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.sum = sum;
        this.dateOfPaystub = dateOfPaystub;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }
}
