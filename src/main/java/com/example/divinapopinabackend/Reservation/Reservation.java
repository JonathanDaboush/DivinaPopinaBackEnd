package com.example.divinapopinabackend.Reservation;

import com.example.divinapopinabackend.Order.Order;
import com.example.divinapopinabackend.Transaction.Transaction;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.*;
import com.voodoodyne.jackson.jsog.JSOGGenerator;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Set;
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIdentityInfo(generator= JSOGGenerator.class)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservation")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Reservation {
    /**
     * Transactions is the table holding reservation info.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column
    private String note;
    @Column(name = "date_of_event",nullable = false)
    private Date dateOfEvent;

    @OneToMany(mappedBy="reservation")
    private Set<Order> orders;

    @OneToOne(mappedBy = "reservation",cascade = CascadeType.ALL)
    private Transaction transaction;

    @Column(nullable = false)
    private String name;

    public Reservation(String note, Date dateOfEvent,  String name) {

        this.note = note;
        this.dateOfEvent = dateOfEvent;
        this.name = name;
    }
}
