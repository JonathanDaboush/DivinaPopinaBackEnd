package com.example.divinapopinabackend.Reservation;

import com.example.divinapopinabackend.Order.Order;
import com.example.divinapopinabackend.Transaction.Transaction;
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
@Table(name = "reservation")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private float cost;
    @Column(nullable = false)
    private String note;
    @Column(name = "date_of_event",nullable = false)
    private Date dateOfEvent;

    @OneToMany(mappedBy="reservation", cascade = CascadeType.ALL)
    private Set<Order> orders;

    @OneToOne(mappedBy = "reservation",cascade = CascadeType.ALL)
    private Transaction transaction;
    public Reservation(String name, float cost, String note, Date dateOfEvent) {
        this.name = name;
        this.cost = cost;
        this.note = note;
        this.dateOfEvent = dateOfEvent;
    }
}
